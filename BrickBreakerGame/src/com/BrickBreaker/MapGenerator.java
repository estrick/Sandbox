package com.BrickBreaker;

import java.awt.*;

public class MapGenerator {

    public int map[][]; // Brick array
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col) {
        // Receives the number of rows and columns for the bricks to be generated
        map = new int[row][col]; // New matrix with rows and columns
        for (int i = 0; i < map.length; i++) { // loop through rows
            for (int j = 0; j < map[0].length; j++) { // loop through cols
                map[i][j] = 1; // 1 indicates that the brick has not been broken by the ball and is shown on panel
                // If you do not want a particular brick to be drawn (maybe different level)
                //      Simply set the value of that brick to be equal to 0
            }
        }
        // Set width and height of bricks depending on size of map array
        // Less bricks in map = larger bricks
        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    // Map building method (draw bricks)
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] > 0) { // If brick exists (==1) draw it
                    g.setColor(Color.white);
                    g.fillRect((j * brickWidth)+80, (i*brickHeight)+50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3)); // Create borders around bricks
                    g.setColor(Color.black);
                    g.drawRect((j * brickWidth)+80, (i*brickHeight)+50, brickWidth, brickHeight);
                }
            }
        }
    }
    // Method to set value of brick (0 or 1) denoting if brick is drawn or not
    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }


}