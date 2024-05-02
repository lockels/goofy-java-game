package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoinTest {
    private Coin coin;
    private Body mockBody;
    private final int initialValue = 10;
    private final String textureId = "coin_texture";
    private final String tag = "coin";

    @BeforeEach
    public void setUp() {
        // Create a mock Body to pass to the Coin constructor
        mockBody = mock(Body.class);
        // Instantiate the Coin with a mock body, a texture identifier, a value, and a tag
        coin = new Coin(mockBody, textureId, initialValue, tag);
    }

    @Test
    public void testConstructor() {
        // Assert that the coin was created and the values are correctly assigned
        assertNotNull(coin);
        assertEquals(mockBody, coin.getBody()); 
        assertEquals(textureId, coin.getTextureId()); 
        assertEquals(tag, coin.getTag()); 
        assertEquals(initialValue, coin.getValue());
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

