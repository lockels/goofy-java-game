package inf112.skeleton.app.view.HUD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;

import java.util.List;

public class HUDTest {
    private HUD hud;
    private SpriteBatch spriteBatch;
    private Texture heartTexture;
    private float screenX = 100;
    private float screenY = 100;
    private int maxHearts = 5;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        spriteBatch = mock(SpriteBatch.class);
        heartTexture = mock(Texture.class);

        // Instantiate HUD
        hud = new HUD(heartTexture, maxHearts, screenX, screenY);
    }

    @Test
    void testDraw() {
        // Execute the method to test
        hud.draw(spriteBatch);

        // Verify that draw is called on the spriteBatch for each filled heart
        // Assuming Heart.draw() calls SpriteBatch.draw() with explicit float values
        verify(spriteBatch, times(5)).draw(
            any(Texture.class), 
            anyFloat(), 
            anyFloat(), 
            anyFloat(), 
            anyFloat()
        );
    }


    @Test
    void testUpdateHearts() {
        // Setup initial conditions
        int currentHealth = 3; // Assume the player has lost two hearts

        // Execute the method to test
        hud.updateHearts(currentHealth, screenX, screenY);

        // Verify that the first three hearts are filled and the last two are not
        List<Heart> hearts = hud.getHearts();
        for (int i = 0; i < maxHearts; i++) {
            if (i < currentHealth) {
                assertTrue(hearts.get(i).isFilled());
            } else {
                assertFalse(hearts.get(i).isFilled());
            }
        }
    }

    @Test
    void testGetHearts() {
        hud.updateHearts(3, screenX, screenY); // Assume this updates filled states correctly

        List<Heart> hearts = hud.getHearts();

        assertEquals(maxHearts, hearts.size());

        for (int i = 0; i < maxHearts; i++) {
            if (i < 3) {
                assertTrue(hearts.get(i).isFilled());
            } else {
                assertFalse(hearts.get(i).isFilled());
            }
        }
    }
}
