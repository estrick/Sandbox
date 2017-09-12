package com.StandingMan;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12*9; // 12*9 is resolution
    public static final int SCALE = 3;
    public static final String NAME = "Standing Man";
    public boolean running = false;
    public int tickCount = 0;

    private JFrame frame;

    public Game() {
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); // Keeps Canvas at one size
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Something for borders/ title bar

        frame.add(this, BorderLayout.CENTER); // Add the game to the JFrame and centre it
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack(); // Sets sizes to above or at preferred size (Keeps everything sized correctly)

    }

    // Image overlay for canvas




    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    public void run() {
        long lastTime = System.nanoTime(); // Gets time in nanoseconds
        double nsPerTick = 1000000000D/60D; // How many nanoseconds per tick
        int ticks = 0;
        int frames = 0;
        long lastTimer = System.currentTimeMillis();
        double delta = 0; // How many nanoseconds have gone by so far, once we hit 1 we will minus 1 from it
        while (running) {
            long now = System.nanoTime();
            delta += (now-lastTime)/nsPerTick; // Current time minus Last time, divided by nspertick
            lastTime = now; // Set lastTime to now, so that we update each time
            boolean shouldRender = true;

            while (delta >= 1) {
                // This loop will run until we hit 60 then continue
                ticks++;
                tick();
                delta--;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (shouldRender) {
                frames++;
                render();
            }

            // To reset ticks/frames once per second
            if((System.currentTimeMillis()-lastTimer) >= 1000) {
                lastTimer += 1000;
                System.out.println(" Ticks: " + ticks + "Frames: " + frames);
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick() {
        tickCount++;
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy(); // Object to organise data on canvas
        if (bs == null) {
            createBufferStrategy(3); // Triple buffering, higher number = less tearing but also more processing power
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());

        g.dispose();
        bs.show();

    }


    public static void main(String[] args) {
        new Game().start();
    }


}
