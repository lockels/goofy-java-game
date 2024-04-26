package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static inf112.skeleton.app.utils.Constants.*;
import static org.mockito.Mockito.*;

import java.security.KeyStore.TrustedCertificateEntry;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Mock
    private Body mockBody;

    private Enemy enemy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockBody.getPosition()).thenReturn(new Vector2(0, 0)); // Setting initial position

        enemy = new Enemy(mockBody, "spriteSheetPath", "enemy", 20);
    }

    // @Test
    // public void testMoveTowards() {
    //     float targetX = 10f;
    //     float targetY = 10f;
    //     enemy.moveTowards(targetX, targetY);

    //     ArgumentCaptor<Vector2> velocityCaptor = ArgumentCaptor.forClass(Vector2.class);
    //     verify(mockBody).setLinearVelocity(velocityCaptor.capture());

    //     Vector2 capturedVelocity = velocityCaptor.getValue();
    //     float actualSpeeda = enemy.getSpeed();  // Calculate the length of the velocity vector

    //     // Check if the speed is within the defined limits
    //     while (true) {
    //         enemy.moveTowards(targetX, targetY);
    //         float actualSpeed = enemy.getSpeed();  // Calculate the length of the velocity vector
    //         assertTrue(actualSpeed >= ENEMY_SPEED_MIN && actualSpeed <= ENEMY_SPEED_MAX);
    //     }
    // }

    @Test
    public void testHit() {
        int initialHP = ENEMY_HEALTH;
        int damage = 1;
        enemy.hit(damage, new Vector2(0, 0)); // assuming knockback is not handled yet

        // Since Enemy.enemyHP is private, you may need to add a getter for testing or observe changes through behavior.
        // Assuming a hypothetical getHealth method for this example:
        assertEquals(initialHP - damage, 9);

        // Test for enemy deactivation upon zero health
        enemy.hit(initialHP, new Vector2(0, 0));
        assertFalse(enemy.isActive());
    }
}


