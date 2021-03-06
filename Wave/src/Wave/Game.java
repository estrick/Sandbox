package Wave;

import Input.KeyInput;
import Objects.BasicEnemy;
import Objects.HUD;
import Objects.ID;
import Objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH*9/12;
    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;

    public Game() {
        setFocusable(true); // Focused on game on start-up
        handler = new Handler();
        r = new Random();
        hud = new HUD();
        new Window(WIDTH, HEIGHT, "Wave", this);

        handler.addGameObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player));
        for(int i = 0; i < 20; i++) {
            handler.addGameObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy));
        }
        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join(); // Kill the thread
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus(); // Auto focused for keyboard input
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if(running) {
                render();
            }
            frames++;
            if((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g); // HUD after handler so that it is top layer

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String[] args) {
        new Game();
    }

}
