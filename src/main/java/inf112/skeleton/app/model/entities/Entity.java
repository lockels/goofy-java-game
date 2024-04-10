package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents a game entity with a hitbox and sprite information.
 */
public abstract class Entity {
    // Field vars
    Rectangle hitbox;
    String spriteSheetPath;
    protected int spriteSheetX;
    protected int spriteSheetY;
    protected int spriteHeight;
    protected int spriteWidth;

    /**
     * Constructs a new Entity with the specified parameters.
     *
     * @param hitbox       the hitbox rectangle defining the bounds of the entity
     * @param spriteSheet  the file path to the sprite sheet for the entity's appearance
     * @param spriteSheetX the x-coordinate of the sprite on the sprite sheet
     * @param spriteSheetY the y-coordinate of the sprite on the sprite sheet
     * @param spriteWidth  the width of the sprite
     * @param spriteHeight the height of the sprite
     */
    public Entity(Rectangle hitbox, String spriteSheet, int spriteSheetX, int spriteSheetY, int spriteWidth,
                  int spriteHeight) {
        this.hitbox = hitbox;
        this.spriteSheetPath = spriteSheet;
        this.spriteSheetX = spriteSheetX;
        this.spriteSheetY = spriteSheetY;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
    }

    /**
     * Gets the x-coordinate of the entity's position.
     *
     * @return the x-coordinate
     */
    public float getX() {
        return hitbox.x;
    }

    /**
     * Gets the y-coordinate of the entity's position.
     *
     * @return the y-coordinate
     */
    public float getY() {
        return hitbox.y;
    }

    /**
     * Gets the hitbox rectangle defining the bounds of the entity.
     *
     * @return the hitbox rectangle
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Gets the x-coordinate of the sprite on the sprite sheet.
     *
     * @return the x-coordinate of the sprite
     */
    public int getSpriteSheetX() {
        return spriteSheetX;
    }

    /**
     * Gets the y-coordinate of the sprite on the sprite sheet.
     *
     * @return the y-coordinate of the sprite
     */
    public int getSpriteSheetY() {
        return spriteSheetY;
    }

    /**
     * Gets the height of the sprite.
     *
     * @return the height of the sprite
     */
    public int getSpriteHeight() {
        return spriteHeight;
    }

    /**
     * Gets the width of the sprite.
     *
     * @return the width of the sprite
     */
    public int getSpriteWidth() {
        return spriteWidth;
    }

    /**
     * Gets the file path to the sprite sheet for the entity's appearance.
     *
     * @return the sprite sheet path
     */
    public String getSpriteSheetPath() {
        return this.spriteSheetPath;
    }

    /**
     * Checks if this entity collides with another entity.
     *
     * @param other the other entity to check collision with
     * @return true if this entity collides with the other, false otherwise
     */
    public boolean collidesWith(Entity other) {
        return hitbox.overlaps(other.getHitbox());
    }

    /**
     * Moves the entity to the specified position.
     *
     * @param x the new x-coordinate of the entity
     * @param y the new y-coordinate of the entity
     */
    public void move(float x, float y) {
        hitbox.setPosition(x, y);
    }
}
