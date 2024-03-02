package inf112.skeleton.app.myInput;

import com.badlogic.gdx.Input.Keys;
import inf112.skeleton.app.Player;
import inf112.skeleton.app.Direction;

public class MyInputAdapter extends MyInputProcessor {
    Player player;

    public MyInputAdapter(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                player.setMovement(Direction.LEFT, true);
                break;
            case Keys.RIGHT:
                player.setMovement(Direction.RIGHT, true);
                break;
            case Keys.UP:
                player.setMovement(Direction.UP, true);
                break;
            case Keys.DOWN:
                player.setMovement(Direction.DOWN, true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                player.setMovement(Direction.LEFT, false);
                break;
            case Keys.RIGHT:
                player.setMovement(Direction.RIGHT, false);
                break;
            case Keys.UP:
                player.setMovement(Direction.UP, false);
                break;
            case Keys.DOWN:
                player.setMovement(Direction.DOWN, false);
                break;
        }
        return true;
    }
}
