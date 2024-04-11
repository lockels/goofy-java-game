package inf112.skeleton.app.controller.myInput;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.entities.Player;

public class MyInputAdapter extends InputAdapter {
    private final Player player;

    public MyInputAdapter(Player player) {
        this.player = player;
    }

    
    @Override
    public boolean keyDown(int keycode) {
        Direction direction = getKeyDirection(keycode);
        if (direction != null) {
            player.setMovement(direction, true);
            return true;
        }
        return false;
    }

    
    @Override
    public boolean keyUp(int keycode) {
        Direction direction = getKeyDirection(keycode);
        if (direction != null) {
            player.setMovement(direction, false);
            return true;
        }
        return false;
    }

    private Direction getKeyDirection(int keycode) {
        return switch (keycode) {
            case Keys.LEFT -> Direction.LEFT;
            case Keys.RIGHT -> Direction.RIGHT;
            case Keys.UP -> Direction.UP;
            case Keys.DOWN -> Direction.DOWN;
            default -> null;
        };
    }
}