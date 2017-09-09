package com.BrickBreaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {


    private boolean play = false; // play var is false: Game shouldn't play by itself
    private int score = 0; // Starting score should be 0
    private int totalBricks = 21; // Number of bricks to be broken in game, can be changed
    private Timer time; // Timer class for setting the time of the ball, determining ball speed
    private int delay = 8; // Speed of timer
    private int playerX = 310; // Start x pos of slider
    private int ballPosX = 120; // Start x pos of ball
    private int ballPosY = 350; // start y pos of ball
    private int ballXDir = -1; // start ball x direction
    private int ballYDir = -2; // start ball y direction


    // Abstract ActionListener Method
    public void actionPerformed(ActionEvent e) {

    }
    // Abstract KeyListener Method
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }
}
