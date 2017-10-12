package Game;

import Input.Controller;
import Input.InputHandler;
import Objects.Enemy;
import Objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Timer gameLoopTimer;
    public Player player;
    public Controller controller;

    public Game() {
        setFocusable(true); // Auto focus onto frame
        gameLoopTimer = new Timer(10, this); // Create loop timer, every 10ms we run through timer
        gameLoopTimer.start();
        player = new Player(100, 100); // Instantiate Player with position
        controller = new Controller(); // Instantiate the enemy
        addKeyListener(new InputHandler(player)); // Add key listener to player
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g; // Convert graphics into 2D graphics

        drawMap(g2d); // Draw the map
        player.draw(g2d); // Draw the player
        controller.draw(g2d);
    }

    private void drawMap(Graphics2D g2d) {
        // Create Map Background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(1, 1, 640, 480);
        // TODO: Create borders
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Re-paint the screen
        player.update(); // Update player location
        controller.update();
    }
}
