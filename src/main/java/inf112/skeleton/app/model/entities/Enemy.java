package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static inf112.skeleton.app.utils.Constants.*;

public class Enemy extends Entity {
    private float speed;
    private int enemyHP = ENEMY_HEALTH;

    /**
     * Constructs a new Enemy with the specified parameters.
     *
     * @param hitBox        the hitbox rectangle defining the bounds of the enemy
     * @param dungeon_sheet the file path to the sprite sheet for the enemy's appearance
     * @param spriteSheetX  the x-coordinate of the sprite on the sprite sheet
     * @param spriteSheetY  the y-coordinate of the sprite on the sprite sheet
     * @param spriteWidth   the width of the sprite
     * @param spriteHeight  the height of the sprite
     * @param speed         the movement speed of the enemy
     */
    public Enemy(Body body, String textureId, String tag, float speed) {
        super(body, textureId, tag);
        this.speed = speed;
    }

    public void moveTowards(float targetX, float targetY) {
        float diffX = targetX - getX();
        float diffY = targetY - getY();
        float angle = MathUtils.atan2(diffY, diffX);
        Vector2 velocity = new Vector2(MathUtils.cos(angle) * speed, MathUtils.sin(angle) * speed);

        body.setLinearVelocity(velocity);
    }

    public float getSpeed() {
        return this.speed;
    }

    public void hit(int dmg, Vector2 knockback) { takeDmg(dmg); }

    private void applyKnockback(Vector2 knockback) {
    }
    private void takeDmg(int dmg) {
        enemyHP -= dmg;
        System.out.println(enemyHP);
        if (enemyHP <= 0) {isActive = false; }
    }
}
