package com.BrickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {


    private boolean play = false; // play var is false: Game shouldn't play by itself
    private int score = 0; // Starting score should be 0
    private int totalBricks = 21; // Number of bricks to be broken in game, can be changed
    private Timer timer; // Timer class for setting the time of the ball, determining ball speed
    private int delay = 8; // Speed of timer
    private int playerX = 310; // Start x pos of slider
    private int ballPosX = 120; // Start x pos of ball
    private int ballPosY = 350; // start y pos of ball
    private int ballXDir = -1; // start ball x direction
    private int ballYDir = -2; // start ball y direction
    private final int LEFT_LIMIT = 10;
    private final int RIGHT_LIMIT = 600;

    // Constructor
    public Gameplay() {
        addKeyListener(this); // Add key listener in order to work with KL
        setFocusable(true); //
        setFocusTraversalKeysEnabled(false);
        // Need java.swing.Timer for below
        timer = new Timer(delay, this); // Timer object, speed = delay, context = this
        timer.start();
    }

    // Draw shapes method, takes graphics object
    public void paint(Graphics g) {
        // Create background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592); // (x, y, width, height)

        // Create borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 0, 3, 592);

        // Create paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8); // X pos is player X variable

        // Create ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20); // X & Y pos is ball X,Y pos

        g.dispose();
    }
    public void moveRight() {
        play = true; // Ensure we are playing
        playerX += 20; // Move right
    }
    public void moveLeft() {
        play = true; // Ensure we are playing
        playerX -= 20; // Move left
    }

    // Abstract ActionListener Method
    public void actionPerformed(ActionEvent e) {

    }
    // Abstract KeyListener Method
    public void keyPressed(KeyEvent e) {
        // Check if the key pressed is right key
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Check if the paddle is at the right side limit
            if(playerX >= RIGHT_LIMIT) {
                playerX = RIGHT_LIMIT;
            } else {
                moveRight();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Check if the key pressed is left key
            // Check if the paddle is at the left side limit
            if(playerX < LEFT_LIMIT) {
                playerX = LEFT_LIMIT;
            } else {
                moveLeft();
            }
        }
    }
    // Do not need below methods for this game
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
