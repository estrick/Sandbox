package Objects;

import Wave.Game;

import java.awt.*;

public class BasicEnemy extends GameObject {
    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 35) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
