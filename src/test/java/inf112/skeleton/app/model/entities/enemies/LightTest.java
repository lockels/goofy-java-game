//Tests written by Fredrik

package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightTest {
    private Enemy enemy;
    private World world;

    @BeforeEach
    public void setUp() {
        world = new World(new Vector2(), true);
        enemy = new Light(world);
    }

    //Getter tests
    @Test
    void testGetHP() {assertEquals(3, enemy.getHP()); }

    @Test
    void testGetSpeed() { assertEquals(250f, enemy.getSpeed()); }

    //Setter tests
    @Test
    void testSetHP() {
        enemy.setHP(8);
        assertEquals(8, enemy.getHP());
    }

    @Test
    void testSetStunTimer() {
        enemy.setStunTimer(5.0f);
        assertEquals(5.0f, enemy.getStunTimer());
    }

    //Method tests
    @Test
    public void testMoveTowards() {
         //Move enemy from 0,0 towards 0,50 (Right)
         enemy.moveTowards(0, 50);
         Vector2 velocity = enemy.getBody().getLinearVelocity();
         assertEquals(0, velocity.x, "Velocity along x-axis should be 0");
         assertEquals(2.5, velocity.y, "Velocity along y-axis should be 2.5");

         //Reset enemy velocity
         enemy.getBody().setLinearVelocity(new Vector2());

         //Move enemy from 0,0 towards 0,50 (Up)
         enemy.moveTowards(50, 0);
         velocity = enemy.getBody().getLinearVelocity();
         assertEquals(2.5, velocity.x, "Velocity along x-axis should be 2.5");
         assertEquals(0, velocity.y, "Velocity along y-axis should be 0");

         //Reset enemy velocity
         enemy.getBody().setLinearVelocity(new Vector2());

         //Move enemy from 0,0 towards 0,50 (Up and right)
         enemy.moveTowards(50, 50);
         velocity = enemy.getBody().getLinearVelocity();
         assertEquals(1.767,
                 (double) Math.round(velocity.x * 1000) / 1000, //Rounded to 3 decimal places
                 "Velocity along x-axis should be about 1.767");
         assertEquals(1.768,
                 (double) Math.round(velocity.y * 1000) / 1000, //Rounded to 3 decimal places
                 "Velocity along y-axis should be about 1.768");
     }

    @Test
    public void testHit() {
        enemy.hit(1, 5, 90, 2.0f);
        assertEquals(2, enemy.getHP(), "Health should decrease by 1");
        assertEquals(2.0f, enemy.getStunTimer(), 0.01, "Stun timer should be set to 2.0");
    }

    @Test
    public void testSetHealth() {
        enemy.setHP(8);
        assertEquals(8, enemy.getHP(), "Health should be set to 8");
    }
}

