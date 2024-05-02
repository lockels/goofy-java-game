package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Spike entity.
 */
public class SpikeTest {

    private Spike spike;
    private Body mockBody;

    @BeforeEach
    public void setUp() {
        // Mock the Body object using Mockito
        mockBody = Mockito.mock(Body.class);
        // Instantiate a Spike object
        spike = new Spike(mockBody, "spikeTest", 10, 0, 0);
    }

    /**
     * Test that the Spike constructor correctly assigns the damage value.
     */
    @Test
    public void testSpikeConstructor() {
        assertEquals(10, spike.getDamage());
    }

    /**
     * Test the getDamage method returns the correct value.
     */
    @Test
    public void testGetDamage() {
        assertEquals(10, spike.getDamage());
    }
}

