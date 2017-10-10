package com.BrickBreaker;

import javax.swing.*;

public class Main extends Thread {

    public static void main(String[] args) {
        // Create the JFrame
        createJFrame();
    }


    private static void createJFrame() {
        // Create JFrame (Main Frame with minimize/close buttons)
        JFrame jframe = new JFrame();
        Gameplay gameplay = new Gameplay();
        // Set properties for JFrame
        jframe.setBounds(10, 10, 700, 600);
        jframe.setTitle("Brick Breaker");
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gameplay);
        jframe.setVisible(true);
    }

}


