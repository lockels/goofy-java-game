package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.controller.myInput.MyInputAdapter;
import inf112.skeleton.app.model.Direction;
import java.util.EnumMap;
import java.util.Map;
import static inf112.skeleton.app.utils.Constants.*;

/**
 * Represents a player in the game.
 * Extends the {@link Entity} class.
 * This class provides attributes and behavior for a player character.
 */
public class Player extends Entity {
    private Map<Direction, Boolean> moveDirections;
    private int health;
    private boolean inContactWithSpike = false;

    /**
     * Constructs a new Player with the specified parameters.
     *
     * @param body       The Box2D body representing the player.
     * @param textureId  The identifier for the player's texture.
     * @param tag        The tag or identifier of the entity.
     */
    public Player(Body body, String textureId, String tag) {
        super(body, textureId, tag, PLAYER_HEIGHT, PLAYER_WIDTH);
        health = PLAYER_HEALTH;
        moveDirections = new EnumMap<>(Direction.class);
        for (Direction dir : Direction.values()) {
            moveDirections.put(dir, false);
        }
    }

    @Override
    public Vector2 getPosition() {
        return this.body.getPosition();
    }

    /**
     * Gets the health of the player.
     *
     * @return The health of the player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the player.
     *
     * @param playerHealth The health of the player.
     */
    public void setHealth(int playerHealth) {
        this.health = playerHealth;
    }

    /**
     * Sets whether the player is in contact with a spike.
     *
     * @param inContact True if the player is in contact with a spike, otherwise false.
     */
    public void setInContactWithSpike(boolean inContact) {
        this.inContactWithSpike = inContact;
    }

    /**
     * Checks if the player is in contact with a spike.
     *
     * @return True if the player is in contact with a spike, otherwise false.
     */
    public boolean isInContactWithSpike() {
        return inContactWithSpike;
    }

    /**
     * Reduces the player's health by the specified amount of damage.
     *
     * @param damage The amount of damage to inflict.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    /**
     * Gets the movement directions of the player.
     *
     * @return A map representing the movement directions of the player.
     */
    public Map<Direction, Boolean> getMovementDirections() {
        return moveDirections;
    }

    /**
     * Sets the movement status for a specified direction.
     *
     * @param direction The direction to set.
     * @param isActive  True if the player should move in the specified direction, otherwise false.
     */
    public void setMovement(Direction direction, boolean isActive) {
        moveDirections.put(direction, isActive);
    }

    /**
     * Moves the player according to the active movement directions.
     * The player's speed is determined by the constant {@link Constants#PLAYER_SPEED}.
     */
    public void move() {
        // Calculate movement direction
        Vector2 direction = new Vector2();
        for (Map.Entry<Direction, Boolean> entry : moveDirections.entrySet()) {
            if (entry.getValue()) {
                switch (entry.getKey()) {
                    case UP:
                        direction.y += 1;
                        break;
                    case DOWN:
                        direction.y -= 1;
                        break;
                    case LEFT:
                        direction.x -= 1;
                        break;
                    case RIGHT:
                        direction.x += 1;
                        break;
                    case NONE:
                        direction.set(0, 0);
                        break;
                }
            }
        }
        direction.nor(); // Normalize to get unit vector
        body.setLinearVelocity(direction.scl(PLAYER_SPEED));
        if (MyInputAdapter.keyPressed()) {
            body.applyForceToCenter(direction.scl(PLAYER_SPEED), true);
        }
        body.applyForceToCenter(direction.scl(0), true);
    }

    /**
     * Stops the player's movement.
     */
    public void stopMovement() {
        body.setLinearVelocity(0, 0);
    }

    /**
     * Checks if the player collides with the specified entity.
     *
     * @param entity The entity to check collision with.
     * @return True if the player collides with the specified entity, otherwise false.
     */
    public boolean collidesWith(Entity entity)  {
        return body.getPosition().dst(entity.getBody().getPosition()) < Math.max(entity.getSpriteWidth(), entity.getSpriteHeight()) + PLAYER_COLLISION_RADIUS;
    }
}
