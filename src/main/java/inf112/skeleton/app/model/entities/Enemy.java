package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

/**
 * Represents an enemy entity in the game.
 */
public class Enemy extends Entity {
    private Vector2 velocity;
    private Vector2 pos;
    private float speed;
    private Vector2 direction;

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
    public Enemy(Rectangle hitBox, String dungeon_sheet, int spriteSheetX, int spriteSheetY,
                 int spriteWidth, int spriteHeight, float speed) {
        super(hitBox, dungeon_sheet, spriteSheetX, spriteSheetY, spriteWidth, spriteHeight);
        this.speed = speed;
        pos = new Vector2(hitBox.x, hitBox.y);
        velocity = new Vector2();
        direction = new Vector2();
    }

    /**
     * Moves the enemy towards the specified target position.
     *
     * @param targetX the x-coordinate of the target position
     * @param targetY the y-coordinate of the target position
     */
    public void moveTowards(float targetX, float targetY) {
        float diffX = targetX - hitbox.x;
        float diffY = targetY - hitbox.y;
        float angle = MathUtils.atan2(diffY, diffX);
        velocity.set(MathUtils.cos(angle) * speed, MathUtils.sin(angle) * speed);

        // Update the position based on the velocity
        pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
        hitbox.setPosition(pos);
    }
}

