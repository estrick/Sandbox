package Wave;

import Objects.GameObject;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<>();

    public void addGameObject(GameObject gameObject) {
        this.objects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        this.objects.remove(gameObject);
    }

    public void tick() {
        for(int i = 0; i < objects.size(); ++i) {
            GameObject tempGameObject = objects.get(i);
            tempGameObject.tick();
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < objects.size(); ++i) {
            GameObject tempGameObject = objects.get(i);
            tempGameObject.render(g);
        }
    }

}
