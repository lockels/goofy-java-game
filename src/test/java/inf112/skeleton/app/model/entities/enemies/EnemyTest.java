package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyTest {
    private Enemy enemy;
    private Body body;

    @BeforeEach
    public void setUp() {
        // Mock the Body object
        body = mock(Body.class);

        // Create and configure a mock Fixture
        Fixture mockFixture = mock(Fixture.class);

        // Create a non-empty Array of Fixtures and add the mock fixture to this list
        Array<Fixture> fixtures = new Array<>();
        fixtures.add(mockFixture);

        // Set the mock to return the fixtures when getFixtureList is called
        when(body.getFixtureList()).thenReturn(fixtures);

        // Initialize the Enemy object with mocked body and fixture
        enemy = new Enemy(body, "test_texture", 32, 32, 10.0f, 100);
    }


    // @Test
    // public void testMoveTowards() {
    //     enemy.moveTowards(50, 50);
    //     verify(body, times(1)).applyLinearImpulse(any(Vector2.class), any(Vector2.class), eq(true));
    // }


    @Test
    public void testHit() {
        enemy.hit(10, 5, 90, 2.0f);
        assertEquals(90, enemy.getHealth(), "Health should decrease by 10");
        assertEquals(2.0f, enemy.getStunTimer(), 0.01, "Stun timer should be set to 2.0");
    }

    @Test
    void testSetStunTimer() {
        enemy.setStunTimer(5.0f);
        assertEquals(5.0f, enemy.getStunTimer());
    }

    @Test
    public void testSetHealth() {
        enemy.setHealth(80);
        assertEquals(80, enemy.getHealth(), "Health should be set to 80");
    }

    @Test
    void testGetSpeed() {
        assertEquals(10.0f, enemy.getSpeed());
    }

    @Test
    void testGetEnemyHP() {
        assertEquals(100, enemy.getHealth());
    }

}
