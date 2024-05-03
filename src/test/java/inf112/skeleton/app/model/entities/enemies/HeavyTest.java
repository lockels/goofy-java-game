package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static inf112.skeleton.app.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class HeavyTest {
    private World mockWorld;
    private Heavy heavy;
    private Body mockBody;

    @BeforeEach
    void setUp() {
        mockWorld = mock(World.class);
        mockBody = mock(Body.class);

        // Configure the PhysicsFactory to return a mocked Body
        Mockito.mockStatic(PhysicsFactory.class);
        when(PhysicsFactory.createDynamicEntityBody(eq(mockWorld), any(Vector2.class), 
            eq(HEAVY_ENEMY_WIDTH), eq(HEAVY_ENEMY_HEIGHT), eq(true))).thenReturn(mockBody);

        heavy = new Heavy(mockWorld);
    }

    @Test
    void testHeavyInitialization() {
        // Verify that createDynamicEntityBody was called with correct parameters
        //verifyStatic(PhysicsFactory.class);
        // PhysicsFactory.createDynamicEntityBody(eq(mockWorld), any(Vector2.class), 
        //     eq(HEAVY_ENEMY_WIDTH), eq(HEAVY_ENEMY_HEIGHT), eq(true));

        // Assert that the heavy enemy uses the correct constants for its properties
        assertEquals(HEAVY_ENEMY_HEIGHT, heavy.getSpriteHeight());
        assertEquals(HEAVY_ENEMY_WIDTH, heavy.getSpriteWidth());
        assertEquals(HEAVY_ENEMY_SPEED, heavy.getSpeed());
        assertEquals(HEAVY_ENEMY_HEALTH, heavy.getEnemyHP());
    }

    
}

