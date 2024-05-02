package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for Spike entity.
 */
public class SpikeTest {

    private Spike spike;
    private Body mockBody;

    @BeforeEach
    public void setUp() {
        mockBody = Mockito.mock(Body.class);

        // Create and configure a mock Fixture
        Fixture mockFixture = Mockito.mock(Fixture.class);
        Array<Fixture> fixtures = new Array<>();
        fixtures.add(mockFixture);  // Add the mock fixture to the list

        // Set the mock to return the fixtures when getFixtureList is called
        when(mockBody.getFixtureList()).thenReturn(fixtures);

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

