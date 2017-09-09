package com.BrickBreaker;

import com.sun.org.apache.regexp.internal.RE;

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
    private MapGenerator map;
    // Maybe replace new rectangles in ball movement function with below
    //Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
    //Rectangle paddleRect = new Rectangle(playerX, 550, 100, 8);

    // Constructor
    public Gameplay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this); // Add key listener in order to work with KL
        setFocusable(true); //
        setFocusTraversalKeysEnabled(false);
        // Need java.swing.Timer for below
        timer = new Timer(delay, this); // Timer object, speed = delay, context = this
        timer.start();
    }

    // Declare starting values for game restarts
    public void startingValues() {
        play = true;
        ballPosX = 120;
        ballPosY = 350;
        ballXDir = -1;
        ballYDir = -2;
        playerX = 310;
        score = 0;
        totalBricks = 21;
        map = new MapGenerator(3, 7);
    }
    public void endGameValues() {
        play = false;
        ballXDir = 0;
        ballYDir = 0;
    }
    // Draw shapes method, takes graphics object
    public void paint(Graphics g) {
        // Create background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592); // (x, y, width, height)

        // Call draw function in map generator to draw the bricks/map
        map.draw((Graphics2D)g); // Cast to 2D graphics

        // Create borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 0, 3, 592);

        // Display score
        g.setColor(Color.white);
        g.setFont(new Font("Open Sans", Font.BOLD, 25));
        g.drawString("" + score, 592, 30);

        // Create paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8); // X pos is player X variable

        // Create ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20); // X & Y pos is ball X,Y pos

        // Check if there are any bricks left, if none, game is won.
        if(totalBricks <= 0) {
            endGameValues();
            // Display game complete message
            g.setColor(Color.green);
            g.setFont(new Font("Open Sans", Font.BOLD, 30));
            g.drawString("Onya Mate, Score: " + score, 180, 320);
            // Restart message
            g.setFont(new Font("Open Sans", Font.BOLD, 20));
            g.drawString("Press Enter to restart ", 230, 350);
        }

        // Check if ball has gone out of bounds
        if(ballPosY > 570) {
            // Play is false, ball Dir is stopped
            endGameValues();
            // Display game over message
            g.setColor(Color.red);
            g.setFont(new Font("Open Sans", Font.BOLD, 30));
            g.drawString("Sorry m8, Score: " + score, 180, 320);
            // Restart message
            g.setFont(new Font("Open Sans", Font.BOLD, 20));
            g.drawString("Press Enter to restart ", 230, 350);
        }

        g.dispose();
    }
    // Paddle movement
    private void moveRight() {
        play = true; // Ensure we are playing
        playerX += 20; // Move right
    }
    private void moveLeft() {
        play = true; // Ensure we are playing
        playerX -= 20; // Move left
    }
    // Ball movement
    private void ballMovement() {
        if(play) { // If we have pressed left/right

            // Detect intersection between ball and paddle
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYDir = -ballYDir; // Reverse direction after rebound
            }

            // Detect collison with bricks, map(obj).map(array)
           A: for (int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++) {
                    if(map.map[i][j] > 0) {
                        // If brick exists detect intersection
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        // Create rectangle around brick & ball for collision detection
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rect;
                        // If ball intersects, set brick value to 0 (broken)
                        if(ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            // Below enables ball to rebound from collisions with bricks, instead of moving through
                            // and hitting more bricks
                            if((ballPosX + 19) <= brickRect.x || (ballPosX + 1) >= (brickRect.x + brickRect.width)) {
                                ballXDir = - ballXDir;
                            } else {
                                ballYDir = - ballYDir;
                            }
                            // Escape both loops with break label
                            break A;
                        }
                    }
                }
            }

            // Detect if the ball if touching the top/left/right
            ballPosX += ballXDir; // Move the ball in X
            ballPosY += ballYDir; // Move the ball in y
            if (ballPosX < 0) { // Left boundary
                ballXDir = -ballXDir; // Reverse direction if hitting left
            }
            if (ballPosY < 0) { // Top boundary
                ballYDir = -ballYDir; // Reverse direction if hitting top
            }
            if (ballPosX > 670) { // Right boundary
                ballXDir = -ballXDir; // Reverse direction if hitting right
            }
        }
    }

    // Abstract ActionListener Method
    public void actionPerformed(ActionEvent e) {
        timer.start();
        ballMovement(); // Move the ball
        repaint(); // Redraw everything to show movements of paddle
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
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            // If play is false, restart it using 'startingValues()' method, then repaint
            if(!play) {
                startingValues();
                repaint();
            }
        }
    }
    // Do not need below methods for this game
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
