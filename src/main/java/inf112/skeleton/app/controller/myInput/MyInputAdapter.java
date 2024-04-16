package inf112.skeleton.app.controller.myInput;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.entities.Player; 

import static inf112.skeleton.app.model.Direction.*;

public class MyInputAdapter extends InputAdapter {
    private final Player player;

    public MyInputAdapter(Player player) {
        System.out.println("MyInputAdapter: Created");
        this.player = player;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        System.out.println("MyInputAdapter: keyDown " + keycode);
        Direction direction = getKeyDirection(keycode);
        if (direction != null) {
            player.setMovement(direction, true);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("MyInputAdapter: keyUp " + keycode);
        Direction direction = getKeyDirection(keycode);
        if (direction != null) {
            player.setMovement(direction, false);
            return true;
        }
        return false;
    }

    private Direction getKeyDirection(int keycode) {
        return switch (keycode) {
            case Keys.LEFT -> LEFT;
            case Keys.RIGHT -> RIGHT;
            case Keys.UP -> UP;
            case Keys.DOWN -> DOWN;
            default -> null;
        };
    }
}