package Objects;

import Game.GlobalPosition;
import Input.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Player extends GlobalPosition {

    private int velX = 0, velY = 0; // Velocity vars
    private final int MAX_SPEED = 10;
    private LinkedList<Enemy> enemies = Controller.getEnemyBounds();

    public Player(int x, int y) {
        super(x, y);
    }

    public void update() {
        x+=velX;
        y+=velY;

        // Border Collision
        if(x < 2) {
            x = 2;
        } else if(x > 620) {
            x = 620;
        }
        if(y < 2) {
            y = 2;
        } else if(y > 418) {
            y = 418;
        }

        detectCollision();
    }
    private void detectCollision() {
        for(int i = 0; i < enemies.size(); i++) {
            if(getBounds().intersects(enemies.get(i).getBounds())) {
                System.out.println("Collision");
            }
        }
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            if(velY <= 0) {
                if (velY != -MAX_SPEED) {
                    velY += -2;
                }
                velX = 0;
            }
        } else if(key == KeyEvent.VK_LEFT) {
            if(velX <= 0){
                if(velX != -MAX_SPEED) {
                    velX += -2;
                }
                velY = 0;
            }
        } else if(key == KeyEvent.VK_DOWN) {
            if(velY >= 0) {
                if (velY != MAX_SPEED) {
                    velY += 2;
                }
                velX = 0;
            }
        } else if(key == KeyEvent.VK_RIGHT) {
            if(velX >= 0) {
                if(velX != MAX_SPEED){
                    velX += 2;
                }
                velY = 0;
            }
        }
    }
    public void draw(Graphics2D g2d) {
        // Create Player LightCycle
       g2d.setColor(Color.blue);
        g2d.fillRect(x, y, 12, 35); // X pos is player X variable
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 12, 35);
    }

}
