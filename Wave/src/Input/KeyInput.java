package Input;

import Objects.GameObject;
import Objects.ID;
import Wave.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        movePlayer(5, key);
        if (key == KeyEvent.VK_ESCAPE) System.exit(1); // Exit game on ESC
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        movePlayer(0, key);
    }

    private void movePlayer(int velocity, int key) {
        // Loop through objects list to move object with ID Player()
        for(int i = 0; i < handler.objects.size(); ++i) {
            GameObject tempGameObject = handler.objects.get(i);
            if(tempGameObject.getID() == ID.Player) {
                // Key Events for Player
                if(key == KeyEvent.VK_W) { tempGameObject.setVelY(-velocity); }
                if(key == KeyEvent.VK_S) { tempGameObject.setVelY(velocity); }
                if(key == KeyEvent.VK_A) { tempGameObject.setVelX(-velocity); }
                if(key == KeyEvent.VK_D) { tempGameObject.setVelX(velocity); }
            }
        }
    }

}
