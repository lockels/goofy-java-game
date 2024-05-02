package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        body = mock(Body.class); // Ensure the body is mocked if not already

        // Create and configure a mock Fixture and a non-empty Array of Fixtures
        Fixture mockFixture = mock(Fixture.class);
        Array<Fixture> fixtures = new Array<>();
        fixtures.add(mockFixture);

        // Set the mock to return the fixtures when getFixtureList is called
        when(body.getFixtureList()).thenReturn(fixtures);

        // Set up the Coin with a real body
        coin = new Coin(body, textureId, initialValue, "coin");
    }

    @AfterEach
    public void tearDown() {
        world.dispose();
    }

    @Test
    public void testConstructor() {
        //coin instance should not be null 
        assertNotNull(coin);
        //Body should match the one passed in the constructo
        assertEquals(body, coin.getBody());
        //Texture ID should match the one passed in the constructor
        assertEquals(textureId, coin.getTextureId());
        //Tag should match the one passed in the constructor
        assertEquals("coin", coin.getTag());
        //Initial value should match the one passed in the constructor
        assertEquals(initialValue, coin.getValue());
        //Coin should not be collected initially
        assertFalse(coin.isCollected());
    }


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

