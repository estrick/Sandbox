package Objects;

import Wave.Game;

import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 3, Game.WIDTH-40);
        y = Game.clamp(y, 3, Game.HEIGHT-64);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
