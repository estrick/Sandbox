package Input;

import Graphics.Screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A && !Screen.right) {
            Screen.up = false;
            Screen.down = false;
            Screen.left = true;
        }
        if(key == KeyEvent.VK_D && !Screen.left) {
            Screen.up = false;
            Screen.down = false;
            Screen.right = true;
        }
        if(key == KeyEvent.VK_W && !Screen.down) {
            Screen.right = false;
            Screen.left = false;
            Screen.up = true;
        }
        if(key == KeyEvent.VK_S && !Screen.up) {
            Screen.right = false;
            Screen.left = false;
            Screen.down = true;
        }
        if(key == KeyEvent.VK_ESCAPE ){
            System.exit(1);
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

}
