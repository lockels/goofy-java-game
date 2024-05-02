package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyTest {
    private Enemy enemy;
    private Body body;

    @BeforeEach
    public void setUp() {
        body = mock(Body.class);

        // Mocking fixture list to return a non-null empty array
        Array<Fixture> fixtures = new Array<>();
        when(body.getFixtureList()).thenReturn(fixtures);

        // Initialize the Enemy object
        enemy = new Enemy(body, "test_texture", 32, 32, 10.0f, 100);
    }

    // @Test
    // public void testMoveTowards() {
    //     enemy.moveTowards(50, 50);
    //     // Verify that applyLinearImpulse was called on the body
    //     verify(body, times(1)).applyLinearImpulse(any(Vector2.class), any(Vector2.class), eq(true));
    // }

    @Test
    public void testHit() {
        enemy.hit(10, 5, 90, 2.0f);
        assertEquals(90, enemy.getEnemyHP(), "Health should decrease by 10");
        assertEquals(2.0f, enemy.getStunTimer(), 0.01, "Stun timer should be set to 2.0");
    }

    @Test
    public void testSetHealth() {
        enemy.setHealth(80);
        assertEquals(80, enemy.getEnemyHP(), "Health should be set to 80");
    }
}
