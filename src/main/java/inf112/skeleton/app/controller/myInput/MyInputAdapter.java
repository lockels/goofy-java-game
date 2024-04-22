package inf112.skeleton.app.controller.myInput;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.view.GameActiveScreen;

import static inf112.skeleton.app.model.Direction.*;
import static inf112.skeleton.app.model.GameState.*;

public class MyInputAdapter extends InputAdapter {
    private final Player player;
    private GameLogic gameLogic;

    public MyInputAdapter(Player player, GameLogic gameLogic) {
        System.out.println("MyInputAdapter: Created");
        this.player = player;
        this.gameLogic = gameLogic;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        Direction direction = getKeyDirection(keycode);
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