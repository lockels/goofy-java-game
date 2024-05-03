package inf112.skeleton.app.controller.myInput;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.Input.Keys;

import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.entities.Player;

public class MyInputAdapterTest {

    @Mock
    private Player player;
    @Mock
    private GameLogic gameLogic;
    private MyInputAdapter inputAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inputAdapter = new MyInputAdapter(player, gameLogic);
    }

    @Test
    void testKeyDown() {
        when(gameLogic.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        assertTrue(inputAdapter.keyDown(Keys.LEFT));
        verify(player).setMovement(Direction.LEFT, true);
        verify(gameLogic, never()).setGameState(any(GameState.class));
    }

    @Test
    void testKeyUp() {
        inputAdapter.keyDown(Keys.RIGHT);  // Assume keyDown must be tested for the keyUp effect
        assertTrue(inputAdapter.keyUp(Keys.RIGHT));
        verify(player).setMovement(Direction.RIGHT, false);
    }

    @Test
    void testGameStateChangeOnKeyDown() {
        when(gameLogic.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        assertTrue(inputAdapter.keyDown(Keys.P));
        verify(gameLogic).setGameState(GameState.GAME_PAUSED);
    }
}
