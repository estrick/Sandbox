package Snake;

import Graphics.Screen;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Screen s;

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);
        init();
    }

    public void init() {
        setLayout(new GridLayout(1, 1, 0, 0));

        s = new Screen();
        add(s);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Frame();
    }

}
