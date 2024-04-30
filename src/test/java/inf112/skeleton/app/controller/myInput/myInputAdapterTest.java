package inf112.skeleton.app.controller.myInput;

import inf112.skeleton.app.model.Direction;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.entities.PhysicsFactory;
import inf112.skeleton.app.model.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static org.junit.jupiter.api.Assertions.*;
import static inf112.skeleton.app.utils.Constants.*;


/**
 * Tests for MyInputAdapter
 * 
 * Not ready yet
 */
class MyInputAdapterTest {
    private MyInputAdapter myInputAdapter;
    private Player player;
    private GameLogic gameLogic;
    private World world;

    @BeforeEach
    void setUp() {
        Body body = PhysicsFactory.createEntityBody(world,
                new Vector2(PLAYER_SPAWN_X, PLAYER_SPAWN_Y),
                new Vector2(),
                PLAYER_WIDTH,
                PLAYER_HEIGHT,
                true);
        player = new Player(body, "playerSprite", "player");
        myInputAdapter = new MyInputAdapter(player, gameLogic);
    }

    @Test
    void keyDown() {
        assertTrue(myInputAdapter.keyDown(21));
        assertTrue(player.getMovementDirections().get(Direction.LEFT));

        assertTrue(myInputAdapter.keyDown(19));
        assertTrue(player.getMovementDirections().get(Direction.UP));
    }

    @Test
    void keyUp() {
        player.setMovement(Direction.LEFT, true);
        assertTrue(myInputAdapter.keyUp(21));
        assertFalse(player.getMovementDirections().get(Direction.LEFT));

        player.setMovement(Direction.UP, true);
        assertTrue(myInputAdapter.keyUp(19));
        assertFalse(player.getMovementDirections().get(Direction.UP));
    }
}
