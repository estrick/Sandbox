package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import Entities.Apple;
import Entities.BodyPart;
import Input.InputHandler;

public class Screen extends JPanel implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private Thread thread;
    private boolean running = false;

    private BodyPart b;
    private ArrayList<BodyPart> snake;

    private Apple apple;
    private ArrayList<Apple> apples;

    private int x = 1, y = 10;
    private int size = 5;

    public static boolean right = true, left = false, up = false, down = false;
    private int ticks = 0;

    private InputHandler inputHandler;

    private Random r;

    public Screen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        inputHandler = new InputHandler();
        addKeyListener(inputHandler);

        r = new Random();

        snake = new ArrayList<>();
        apples = new ArrayList<>();

        start();
    }

    public void tick() {
        if(snake.size() == 0) {
            b = new BodyPart(x, y, 10);
            snake.add(b);
        }
        if(apples.size() == 0) {
            int x = r.nextInt(79);
            int y = r.nextInt(79);
            apple = new Apple(x, y, 10);
            apples.add(apple);
        }

        for (int i = 0; i < apples.size(); ++i) {
            if(x == apples.get(i).x && y == apples.get(i).y) {
                size++;
                apples.remove(i);
                i--;
            }
        }

        for(int i = 0; i < snake.size(); ++i) {
            if(x == snake.get(i).x  && y == snake.get(i).y) {
                if(i != snake.size() - 1) {
                    stop();
                }
            }
        }

        if(x < 0 || x > 79 || y < 0 || y >79) {
            stop();
        }

        ticks++;
        if(ticks > 250000) {
            if(right) x++;
            if(left) x--;
            if(up) y--;
            if(down) y++;
            ticks = 0;

            b = new BodyPart(x, y, 10);
            snake.add(b);

            if(snake.size() > size) {
                snake.remove(0);
            }

        }

    }

    public void paint(Graphics g) {

        g.setColor(new Color(10, 50, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        for(int i = 0; i < WIDTH/10; ++i) {
            g.drawLine(i*10, 0, i*10, HEIGHT);
        }
        for(int i = 0; i < HEIGHT/10; ++i) {
            g.drawLine(0, i*10, WIDTH, i*10);
        }

        for(int i = 0; i < snake.size(); ++i) {
            snake.get(i).draw(g);
        }

        for (int i = 0; i < apples.size(); ++i) {
            apples.get(i).draw(g);
        }

    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game Loop");
        thread.start();
    }
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

}
