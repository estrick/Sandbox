package Input;

import Objects.Player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

    public Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }


}
