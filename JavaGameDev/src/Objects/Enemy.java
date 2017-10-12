package Objects;

import Game.GlobalPosition;
import Game.Main;

import java.awt.*;

public class Enemy extends GlobalPosition {

    private int speed = 5;

    public Enemy(int x, int y) {
        super(x,y);
    }
    public void draw(Graphics2D g2d) {
        // Create Enemy LightCycle
        g2d.setColor(Color.red);
        g2d.fillRect(x, y, 12, 35); // X pos is player X variable
    }
    public void update() {
        x+=speed;
        if(x > Main.WIDTH-12) {
            speed = -5;
        }
        if(x < 0) {
            speed = 5;
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 12, 35);
    }


}
