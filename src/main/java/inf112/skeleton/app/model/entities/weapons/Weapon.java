package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.model.entities.Entity;

public abstract class Weapon extends Entity {
    protected Sprite sprite; // Ensure the sprite or similar graphic representation exists
    int dmg;
    float knockback;
    float cooldown;
    float cooldownTimer;
    float stun;

    public Weapon(Body body, String textureId, int spriteHeight, int SpriteWidth, int dmg, float knockback,
            float cooldown, float stun) {
        super(body, textureId, "weapon", spriteHeight, SpriteWidth);
        this.dmg = dmg;
        this.knockback = knockback;
        this.cooldown = cooldown;
        this.stun = stun;
    }

    //Getters
    public int getDmg() {
        return dmg;
    }

    public float getKnockback() {
        return knockback;
    }

    public float getStun() {
        return stun;
    }

    public float getCooldownTimer() {
        return cooldownTimer;
    }

    //Setters
    public void setCooldownTimer(float cooldownTimer) {
        this.cooldownTimer = cooldownTimer;
    }

    //Methods
    public void startCooldownTimer() {
        this.cooldownTimer = cooldown;
    }
}
