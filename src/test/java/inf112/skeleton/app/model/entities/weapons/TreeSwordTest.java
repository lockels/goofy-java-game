package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TreeSwordTest {
    private Weapon weapon;

    @BeforeEach
    public void setUp() {
        weapon = new TreeSword(new World(new Vector2(), true));
    }

    //Getter tests
    @Test
    void testGetDmg() { assertEquals(5, weapon.getDmg()); }

    @Test
    void testGetStun() { assertEquals(1, weapon.getStun()); }

    @Test
    void testGeKnockback() { assertEquals(500, weapon.getKnockback()); }

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
        assertNotEquals(1.5, weapon.getCooldownTimer());
        weapon.startCooldownTimer();
        assertEquals(1.5, weapon.getCooldownTimer());
    }
}
