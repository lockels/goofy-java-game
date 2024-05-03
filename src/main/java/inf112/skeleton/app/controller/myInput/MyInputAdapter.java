package inf112.skeleton.app.controller.myInput;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.entities.Player;

import static inf112.skeleton.app.model.Direction.*;
import static inf112.skeleton.app.model.GameState.*;

public class MyInputAdapter extends InputAdapter {
    private final Player player;
    private GameLogic gameLogic;
    private static boolean keyPressed = false;

    public MyInputAdapter(Player player, GameLogic gameLogic) {
        System.out.println("MyInputAdapter: Created");
        this.player = player;
        this.gameLogic = gameLogic;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        Direction direction = getKeyDirection(keycode);
        keyPressed = true;
        if (direction != null) {
            player.setMovement(direction, true);
            return true;
        }
        GameState gameState = getGameState(keycode, gameLogic.getGameState());
        if (gameState != null) {
            gameLogic.setGameState(gameState);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Direction direction = getKeyDirection(keycode);
        keyPressed = false;
        if (direction != null) {
            player.setMovement(direction, false);
            return true;
        }
        return false;
    }

    public static boolean keyPressed()    {
        return keyPressed;
    }

    private Direction getKeyDirection(int keycode) {
        return switch (keycode) {
            case Keys.LEFT -> LEFT;
            case Keys.A -> LEFT;
            case Keys.RIGHT -> RIGHT;
            case Keys.D -> RIGHT;
            case Keys.UP -> UP;
            case Keys.W -> UP;
            case Keys.DOWN -> DOWN;
            case Keys.S -> DOWN;
            default -> null;
        };
    }

    private GameState getGameState(int keycode, GameState gameState) {
        return switch (gameState) {
            case GAME_ACTIVE -> switch (keycode) {
                case Keys.P -> GAME_PAUSED;
                default -> null;
            };
            case GAME_TITLE -> switch (keycode) {
                case Keys.SPACE -> GAME_ACTIVE;
                default -> null;
            };
            case GAME_PAUSED -> switch (keycode) {
                case Keys.P -> GAME_ACTIVE;
                default -> null;
            };
            case GAME_OVER -> switch (keycode) {
                case Keys.ENTER -> GAME_TITLE;
                default -> null;
            };
            default -> null;
        };
    }
}