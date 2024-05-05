package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.model.entities.Entity;

/**
 * Abstract class representing a weapon in the game.
 * Extends the {@link Entity} class.
 * This class provides a common structure for all weapons.
 */
public abstract class Weapon extends Entity {
    
    protected Sprite sprite;
    int dmg;
    float knockback;
    float cooldown;
    float cooldownTimer;
    float stun;

    /**
     * Constructs a new Weapon.
     *
     * @param body The physical body of the weapon.
     * @param textureId The identifier for the weapon's texture.
     * @param spriteHeight The height of the weapon's sprite.
     * @param spriteWidth The width of the weapon's sprite.
     * @param dmg The damage dealt by the weapon.
     * @param knockback The knockback effect of the weapon.
     * @param cooldown The cooldown duration of the weapon.
     * @param stun The stun duration inflicted by the weapon.
     */
    public Weapon(Body body, String textureId, int spriteHeight, int spriteWidth, int dmg, float knockback,
                  float cooldown, float stun) {
        super(body, textureId, "weapon", spriteHeight, spriteWidth);
        this.dmg = dmg;
        this.knockback = knockback;
        this.cooldown = cooldown;
        this.stun = stun;
    }

    /**
     * Gets the damage dealt by the weapon.
     *
     * @return The damage dealt by the weapon.
     */
    public int getDmg() {
        return dmg;
    }

    /**
     * Gets the knockback effect of the weapon.
     *
     * @return The knockback effect of the weapon.
     */
    public float getKnockback() {
        return knockback;
    }

    /**
     * Gets the stun duration inflicted by the weapon.
     *
     * @return The stun duration inflicted by the weapon.
     */
    public float getStun() {
        return stun;
    }

    /**
     * Gets the remaining cooldown time for the weapon.
     *
     * @return The remaining cooldown time for the weapon.
     */
    public float getCooldownTimer() {
        return cooldownTimer;
    }

    /**
     * Sets the remaining cooldown time for the weapon.
     *
     * @param cooldownTimer The remaining cooldown time for the weapon.
     */
    public void setCooldownTimer(float cooldownTimer) {
        this.cooldownTimer = cooldownTimer;
    }

    /**
     * Starts the cooldown timer for the weapon.
     */
    public void startCooldownTimer() {
        this.cooldownTimer = cooldown;
    }
}
