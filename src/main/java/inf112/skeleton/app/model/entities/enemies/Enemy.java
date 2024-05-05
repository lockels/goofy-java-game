package inf112.skeleton.app.model.entities.enemies;

import static inf112.skeleton.app.utils.Constants.*;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.Entity;

/**
 * Represents an enemy in the game.
 * Extends the {@link Entity} class.
 * This class provides methods for enemy behavior, including movement, 
 * damage handling, and knockback.
 */
public class Enemy extends Entity {

    private float speed;
    private int hp;
    private float stunTimer = 0;

    /**
     * Constructs a new Enemy with the specified parameters.
     *
     * @param body         The Box2D body representing the enemy.
     * @param textureId    The identifier for the enemy's texture.
     * @param spriteHeight The height of the enemy's sprite.
     * @param spriteWidth  The width of the enemy's sprite.
     * @param speed        The movement speed of the enemy.
     * @param hp           The health points of the enemy.
     */
    public Enemy(Body body, String textureId, int spriteHeight, int spriteWidth, float speed, int hp) {
        super(body, textureId, "enemy", spriteHeight, spriteWidth);
        this.speed = speed;
        this.hp = hp;
    }

    /**
     * Moves the enemy towards a specified target location.
     *
     * @param targetX The x-coordinate of the target location.
     * @param targetY The y-coordinate of the target location.
     */
    public void moveTowards(float targetX, float targetY) {
        float diffX = targetX - getX();
        float diffY = targetY - getY();
        float angle = MathUtils.atan2(diffY, diffX);
        float speed = this.speed / 100;
        Vector2 velocity = new Vector2(MathUtils.cos(angle) * speed, MathUtils.sin(angle) * speed);
        body.applyLinearImpulse(velocity, body.getWorldCenter(), true);
    }

    /**
     * Processes a hit on the enemy, applying damage, knockback, and stun.
     *
     * @param dmg       The amount of damage to apply.
     * @param knockback The knockback strength to apply.
     * @param angle     The angle at which the knockback should be applied.
     * @param stun      The duration of the stun to apply.
     */
    public void hit(int dmg, float knockback, float angle, float stun) {
        takeDmg(dmg); 
        applyKnockback(knockback, angle); 
        setStunTimer(stun);
    }

    /**
     * Applies knockback to the enemy in a specified direction.
     *
     * @param knockback The knockback strength to apply.
     * @param angle     The angle at which the knockback should be applied.
     */
    private void applyKnockback(float knockback, float angle) {
        Vector2 trigCoords = trigVector(knockback, angle);
        body.setLinearVelocity(trigCoords.x, trigCoords.y);
        body.setLinearDamping(ENEMY_KNOCKBACK_DAMPING);
        body.applyForceToCenter(new Vector2().scl(PLAYER_SPEED), true);
    }

    private void takeDmg(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            isActive = false;
        }
    }

    /**
     * Gets the remaining stun time for the enemy.
     *
     * @return The remaining stun time for the enemy.
     */
    public float getStunTimer() {
        return stunTimer;
    }

    /**
     * Sets the remaining stun time for the enemy.
     *
     * @param stunTimer The remaining stun time for the enemy.
     */
    public void setStunTimer(float stunTimer) {
        this.stunTimer = stunTimer;
    }

    /**
     * Gets the movement speed of the enemy.
     *
     * @return The movement speed of the enemy.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Gets the health points of the enemy.
     *
     * @return The health points of the enemy.
     */
    public int getHP() {
        return hp;
    }

    /**
     * Sets the health points of the enemy.
     *
     * @param hp The health points of the enemy.
     */
    public void setHP(int hp) {
        this.hp = hp;
    }
}

