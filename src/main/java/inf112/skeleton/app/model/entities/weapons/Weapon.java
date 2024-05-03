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
    public Weapon(Body body, String textureId, int spriteHeight, int SpriteWidth, int dmg, float knockback, float cooldown, float stun) {
       super(body, textureId, "weapon", spriteHeight, SpriteWidth);
       this.dmg = dmg;
       this.knockback = knockback;
       this.cooldown = cooldown;
       this.stun = stun;
   }

    public int getDmg() { return dmg; }

    public float getKnockback() { return knockback; }

    public float getCooldown() { return cooldown; }

    public float getStun() { return stun; }

    public float getCooldownTimer() { return cooldownTimer; }

    public void setDmg(int dmg) { this.dmg = dmg; }

    public void setKnockback(float knockback) { this.knockback = knockback; }

    public void setCooldown(float cooldown) { this.cooldown = cooldown; }

    public void setStun(float stun) { this.stun = stun; }

    public void setCooldownTimer(float cooldownTimer) { this.cooldownTimer = cooldownTimer; }

    public void startCooldownTimer() { this.cooldownTimer = cooldown; }

     // Base render method
     public void render(SpriteBatch batch) {
        if (sprite != null) {
            sprite.draw(batch);
        }
    }
}
