package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoinTest {
    private World world;
    private Coin coin;
    private Body body;
    private final int initialValue = 10;
    private final String textureId = "coin_texture";

    @BeforeEach
    public void setUp() {
        // Set up the Box2D world
        world = new World(new Vector2(0, 0), true);

        // Define body definition for testing
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);
        body = world.createBody(bodyDef);

        // Set up the Coin with a real body
        coin = new Coin(body, textureId, initialValue, "coin");
    }

    @AfterEach
    public void tearDown() {
        world.dispose();
    }

    // @Test
    // public void testConstructor() {
    //     Assertions.assertNotNull(coin);
    //     Assertions.assertEquals(body, coin.getBody());
    //     Assertions.assertEquals(textureId, coin.getTextureId());
    //     Assertions.assertEquals("coin", coin.getTag());
    //     Assertions.assertEquals(initialValue, coin.getValue());
    //     Assertions.assertFalse(coin.isCollected());
    // }

    @Test
    public void testGetValue() {
        assertEquals(initialValue, coin.getValue());
    }

    @Test
    public void testSetCollected() {
        // Ensure the coin is not collected initially
        assertFalse(coin.isCollected());

        // Call setCollected to change the state of the coin
        coin.setCollected();
 
        // Verify that the coin's state has been changed to collected
        assertTrue(coin.isCollected(), "Coin should be collected after setCollected is called");
    }

    @Test
    public void testIsCollected(){
        assertFalse(coin.isCollected());
        coin.setCollected();
        assertTrue(coin.isCollected());
    }
}

