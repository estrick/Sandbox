package Input;

import Objects.Enemy;

import java.awt.*;
import java.util.LinkedList;

public class Controller {

    static LinkedList<Enemy> enemies = new LinkedList<>();
    private Enemy tempEnemy;

    public Controller() {
        addEnemy(new Enemy(200,0));
        addEnemy(new Enemy(200,100));
        addEnemy(new Enemy(200,200));
        addEnemy(new Enemy(200,300));
        addEnemy(new Enemy(200,400));
        addEnemy(new Enemy(200,500));
    }
    public void draw(Graphics2D g2d) {
        for(int i = 0; i < enemies.size(); i++) {
            tempEnemy = enemies.get(i);
            tempEnemy.draw(g2d);
        }
    }
    public void update() {
        for(int i = 0; i < enemies.size(); i++) {
            tempEnemy = enemies.get(i);
            tempEnemy.update();
        }
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
    public static LinkedList<Enemy> getEnemyBounds() {
        return enemies;
    }

}

