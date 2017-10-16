package Entities;

import java.awt.*;

public class BodyPart {

    public int x, y, width, height;
    public BodyPart(int x, int y, int tileSize) {
        this.x = x;
        this.y = y;
        width = tileSize;
        height = tileSize;
    }

    public void tick() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x*width, y*height, width, height);
        g.setColor(Color.green);
        g.fillRect(x*width + 2, y*height + 2, width - 4, height - 4);
    }
}
