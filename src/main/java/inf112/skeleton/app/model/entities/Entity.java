package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Represents a generic entity in the game.
 * This abstract class provides a common structure for all game entities, 
 * including attributes for position, size, texture, and behavior.
 */
public abstract class Entity {

    protected Body body;
    protected String textureIdentifer;
    protected String tag;
    protected Vector2 offset = new Vector2();
    protected boolean isActive = true;
    protected boolean isDestroyed = false;
    protected float opacity = 1f;
    protected float spriteHeight;
    protected float spriteWidth;

    /**
     * Constructs a new Entity with the specified parameters.
     *
     * @param body            The Box2D body representing the entity.
     * @param textureIdentifier The identifier for the entity's texture.
     * @param tag             The tag or identifier of the entity.
     * @param spriteHeight    The height of the entity's sprite.
     * @param spriteWidth     The width of the entity's sprite.
     */
    public Entity(Body body, String textureIdentifier, String tag, float spriteHeight, float spriteWidth) {
        this.body = body;
        this.textureIdentifer = textureIdentifier;
        this.tag = tag;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        this.body.getFixtureList().get(0).setUserData(this);
    }

    /**
     * Gets whether the entity has been destroyed.
     *
     * @return True if the entity has been destroyed, otherwise false.
     */
    public boolean getIsDestroyed() {
        return isDestroyed;
    }

    /**
     * Sets whether the entity has been destroyed.
     *
     * @param isDestroyed True if the entity has been destroyed, otherwise false.
     */
    public void setIsDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    /**
     * Gets the offset of the entity.
     *
     * @return The offset of the entity.
     */
    public Vector2 getOffset() {
        return offset;
    }

    /**
     * Sets the offset of the entity.
     *
     * @param offset The offset of the entity.
     */
    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    /**
     * Gets the position of the entity.
     *
     * @return The position of the entity as a {@link Vector2}.
     */
    public Vector2 getPosition() {
        return body.getPosition();
    }

    /**
     * Gets the x-coordinate of the entity's position.
     *
     * @return The x-coordinate of the entity's position.
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Gets the y-coordinate of the entity's position.
     *
     * @return The y-coordinate of the entity's position.
     */
    public float getY() {
        return body.getPosition().y;
    }

    /**
     * Sets the position of the entity.
     *
     * @param x The x-coordinate of the new position.
     * @param y The y-coordinate of the new position.
     */
    public void setPos(float x, float y) {
        this.body.setTransform(x, y, getAngle());
    }

    /**
     * Sets the angle of the entity.
     *
     * @param angle The new angle in degrees.
     */
    public void setAngle(float angle) {
        this.body.setTransform(getX(), getY(), (float) -Math.toRadians(angle)); // Degrees -> Radians
    }

    /**
     * Gets the Box2D body of the entity.
     *
     * @return The Box2D body of the entity.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Gets the tag or identifier of the entity.
     *
     * @return The tag or identifier of the entity.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Gets whether the entity is active.
     *
     * @return True if the entity is active, otherwise false.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Gets the identifier for the entity's texture.
     *
     * @return The identifier for the entity's texture.
     */
    public String getTextureId() {
        return textureIdentifer;
    }

    /**
     * Gets the angle of the entity in degrees.
     *
     * @return The angle of the entity in degrees.
     */
    public float getAngle() {
        return (float) Math.toDegrees(body.getAngle()); // Radians -> Degrees
    }

    /**
     * Triggers the entity, logging a message indicating the entity has been triggered.
     */
    public void trigger() {
        System.out.println(tag + " has been triggered!");
    }

    /**
     * Computes the x and y coordinates from the hypotenuse and angle using trigonometry.
     *
     * @param hyp   The length of the hypotenuse.
     * @param angle The angle in degrees.
     * @return The computed vector as a {@link Vector2}.
     */
    public Vector2 trigVector(float hyp, float angle) {
        float x = (float) Math.sin(Math.toRadians(angle)) * hyp;
        float y = (float) Math.cos(Math.toRadians(angle)) * hyp;
        if ((angle <= 90) || (angle >= 270)) {
            x *= -1;
        }
        if (angle >= 180) {
            y *= -1;
        }
        return new Vector2(x, y);
    }

    /**
     * Gets the opacity of the entity.
     *
     * @return The opacity of the entity.
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * Sets the opacity of the entity.
     *
     * @param opacity The new opacity of the entity.
     */
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    /**
     * Gets the height of the entity's sprite.
     *
     * @return The height of the entity's sprite.
     */
    public float getSpriteHeight() {
        return spriteHeight;
    }

    /**
     * Gets the width of the entity's sprite.
     *
     * @return The width of the entity's sprite.
     */
    public float getSpriteWidth() {
        return spriteWidth;
    }
}

