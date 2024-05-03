package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MetalSwordTest {
    private Weapon weapon;

    @BeforeEach
    public void setUp() {
        weapon = new MetalSword(new World(new Vector2(), true));
    }

    //Getter tests
    @Test
    void testGetDmg() { assertEquals(3, weapon.getDmg()); }

    @Test
    void testGetStun() { assertEquals(0.8f, weapon.getStun()); }

    @Test
    void testGeKnockback() { assertEquals(100, weapon.getKnockback()); }

    @Test
    void testGetCooldownTimer() { assertEquals(0, weapon.getCooldownTimer()); }

    //Setter tests
    @Test
    void testSetCooldownTimer() {
        assertNotEquals(10, weapon.getCooldownTimer());
        weapon.setCooldownTimer(10);
        assertEquals(10, weapon.getCooldownTimer());
    }

    //Methods
    @Test
    void testStartCooldownTimer() {
        assertNotEquals(0.5, weapon.getCooldownTimer());
        weapon.startCooldownTimer();
        assertEquals(0.5, weapon.getCooldownTimer());
    }
}
