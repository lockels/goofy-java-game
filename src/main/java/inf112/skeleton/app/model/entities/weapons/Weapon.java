package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.model.entities.Entity;

public abstract class Weapon extends Entity {
    int dmg;
    int knockback;
    int cooldown;
    public Weapon(Body body, String textureId, int dmg, int knockback, int cooldown) {
       super(body, textureId, "weapon");
       this.dmg = dmg;
       this.knockback = knockback;
       this.cooldown = cooldown;
   }

    public int getDmg() { return dmg; }

    public int getKnockback() { return knockback; }

    public int getCooldown() { return cooldown; }

    public void setDmg(int dmg) { this.dmg = dmg; }

    public void setKnockback(int knockback) { this.knockback = knockback; }

    public void setCooldown(int cooldown) { this.cooldown = cooldown; }
}
