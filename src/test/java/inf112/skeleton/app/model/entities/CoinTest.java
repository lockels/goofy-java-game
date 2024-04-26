// package inf112.skeleton.app.model.entities;

// import com.badlogic.gdx.physics.box2d.Body;
// import org.junit.Before;
// import org.junit.Test;
// import static org.mockito.Mockito.mock;
// import static org.junit.Assert.*;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// public class CoinTest {
//     private Coin coin;
//     private Body mockBody;
//     private final int initialValue = 10;
//     private final String textureId = "coin_texture";
//     private final String tag = "coin_tag";

//     @Before
//     public void setUp() {
//         // Create a mock Body to pass to the Coin constructor
//         mockBody = mock(Body.class);
//         // Instantiate the Coin with a mock body, a texture identifier, a value, and a tag
//         coin = new Coin(mockBody, textureId, initialValue, tag);
//     }

//     @Test
//     public void testConstructor() {
//         // Assert that the coin was created and the values are correctly assigned
//         assertNotNull(coin);
//         assertEquals(mockBody, coin.getBody()); // Assuming getter for body exists in superclass
//         assertEquals(textureId, coin.getTextureId()); // Assuming getter for textureId exists in superclass
//         assertEquals(tag, coin.getTag()); // Assuming getter for tag exists in superclass
//         assertEquals(initialValue, coin.getValue());
//         assertFalse(coin.isCollected()); // Initially, the coin should not be collected
//     }

//     @Test
//     public void testValueProperty() {
//         // Test the getValue method
//         assertEquals(initialValue, coin.getValue());
//     }

//     @Test
//     public void testSetCollected() {
//         // Initially, the coin should not be collected
//         assertFalse(coin.isCollected());

//         // Set the coin as collected and test the state change
//         coin.setCollected();
//         assertTrue(coin.isCollected());
//     }
// }

