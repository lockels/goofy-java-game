package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeaponTest {
    private Weapon weapon;
    private Body body; // Mocking the Body as it's part of libGDX
    private Sprite sprite;
    private SpriteBatch spriteBatch;

    @BeforeEach
    void setUp() {
        // Mock the Body object
        body = mock(Body.class);

        // Create and configure a mock Fixture
        Fixture mockFixture = mock(Fixture.class);

        // Create a non-empty Array of Fixtures and add the mock fixture to this list
        Array<Fixture> fixtures = new Array<>();
        fixtures.add(mockFixture);

        // Set the mock to return the fixtures when getFixtureList is called
        when(body.getFixtureList()).thenReturn(fixtures);

        // Create a new weapon with mock values
        weapon = new Weapon(body, "testTexture", 10, 10, 15, 2.0f, 1.5f, 0.5f) {};

        sprite = mock(Sprite.class);
        spriteBatch = mock(SpriteBatch.class);
    }

    @Test
    void testConstructorInitialization() {
        int dmg = 15;
        float knockback = 2.0f;
        float cooldown = 1.5f;
        float stun = 0.5f;
        String textureId = "testTexture";
        int spriteHeight = 10;
        int spriteWidth = 10;

        weapon = new Weapon(body, textureId, spriteHeight, spriteWidth, dmg, knockback, cooldown, stun) {};

        // Assertions to check if the constructor properly initialized the fields
        assertEquals(dmg, weapon.getDmg(), "Damage not initialized correctly");
        assertEquals(knockback, weapon.getKnockback(), "Knockback not initialized correctly");
        assertEquals(cooldown, weapon.getCooldown(), "Cooldown not initialized correctly");
        assertEquals(stun, weapon.getStun(), "Stun not initialized correctly");
        assertEquals(body, weapon.getBody(), "Body not associated correctly");
        assertEquals(textureId, weapon.getTextureId(), "Texture ID not set correctly");
        assertEquals(spriteHeight, weapon.getSpriteHeight(), "Sprite height not set correctly");
        assertEquals(spriteWidth, weapon.getSpriteWidth(), "Sprite width not set correctly");    
    }

    @Test
    void testGetDmg() {
        assertEquals(15, weapon.getDmg());
    }

    @Test
    void testGetKnockback() {
        assertEquals(2.0f, weapon.getKnockback());
    }

    @Test
    void testGetCooldown() {
        assertEquals(1.5f, weapon.getCooldown());
    }

    @Test
    void testGetStun() {
        assertEquals(0.5f, weapon.getStun());
    }

    @Test
    void testGetCooldownTimer() {
        assertEquals(0.0f, weapon.getCooldownTimer());
    }

    @Test
    void testSetDmg() {
        weapon.setDmg(20);
        assertEquals(20, weapon.getDmg());
    }

    @Test
    void testSetKnockback() {
        weapon.setKnockback(3.0f);
        assertEquals(3.0f, weapon.getKnockback());
    }

    @Test
    void testSetCooldown() {
        weapon.setCooldown(2.0f);
        assertEquals(2.0f, weapon.getCooldown());
    }

    @Test
    void testSetStun() {
        weapon.setStun(1.0f);
        assertEquals(1.0f, weapon.getStun());
    }

    @Test
    void testSetCooldownTimer() {
        weapon.setCooldownTimer(1.0f);
        assertEquals(1.0f, weapon.getCooldownTimer());
    }

    @Test
    void testStartCooldownTimer() {
        weapon.startCooldownTimer();
        assertEquals(weapon.getCooldown(), weapon.getCooldownTimer());
    }
}
