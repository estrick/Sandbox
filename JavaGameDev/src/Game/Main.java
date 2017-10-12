package Game;

import javax.swing.JFrame;

public class Main {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static void main(String[] args) {
        createJFrame();
    }

    public static void createJFrame() {
        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set location to centre of screen
        frame.setResizable(false);
        frame.add(new Game());
        frame.setVisible(true);
    }



}
