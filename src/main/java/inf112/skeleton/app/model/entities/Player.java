package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.Direction;

import java.util.EnumMap;
import java.util.Map;

import static inf112.skeleton.app.model.Constants.*;

/**
 * Represents a player entity in the game.
 */
public class Player extends Entity {
    private Vector2 velocity;
    private Vector2 direction;
    private Vector2 pos;
    private Map<Direction, Boolean> moveDirections;
    private int health;

    /**
     * Constructs a new Player with the specified parameters.
     *
     * @param hitBox       the hitbox rectangle defining the bounds of the player
     * @param spriteSheet  the file path to the sprite sheet for the player's appearance
     * @param spriteSheetX the x-coordinate of the sprite on the sprite sheet
     * @param spriteSheetY the y-coordinate of the sprite on the sprite sheet
     * @param spriteHeight the height of the sprite
     * @param spriteWidth  the width of the sprite
     */
    public Player(Rectangle hitBox, String spriteSheet, int spriteSheetX, int spriteSheetY, int spriteHeight, int spriteWidth,
                  int originX, int originY, float baseAngle, String type) {
        super(hitBox, spriteSheet, spriteSheetX, spriteSheetY, spriteWidth, spriteHeight,
                originX, originY, baseAngle, type);
        pos = new Vector2(hitBox.x, hitBox.y);
        velocity = new Vector2();
        direction = new Vector2();
        health = PLAYER_HEALTH;

        // Movement Directions
        moveDirections = new EnumMap<>(Direction.class);
        for (Direction dir : Direction.values()) {
            moveDirections.put(dir, false);
        }
    }

    /**
     * Gets the health of the player.
     *
     * @return the player's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the player's health by the specified amount.
     *
     * @param damage the amount of damage to take
     */
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    /**
     * Gets the map of movement directions for the player.
     *
     * @return the map of movement directions
     */
    public Map<Direction, Boolean> getMovementDirections() {
        return moveDirections;
    }

    /**
     * Sets the movement state for the specified direction.
     *
     * @param direction the direction to set movement for
     * @param isActive  true if the movement is active, false otherwise
     */
    public void setMovement(Direction direction, boolean isActive) {
        moveDirections.put(direction, isActive);
    }

    private void calculateMovementDirection() {
        // Vertical
        if ((moveDirections.get(Direction.UP)) == (moveDirections.get(Direction.DOWN))) {
            direction.y = 0;
        } else if (moveDirections.get(Direction.UP)) {
            direction.y = 1;
        } else if (moveDirections.get(Direction.DOWN)) {
            direction.y = -1;
        }

        // Horizontal
        if ((moveDirections.get(Direction.LEFT)) == (moveDirections.get(Direction.RIGHT))) {
            direction.x = 0;
        } else if (moveDirections.get(Direction.LEFT)) {
            direction.x = -1;
        } else if (moveDirections.get(Direction.RIGHT)) {
            direction.x = 1;
        }
    }

    /**
     * Moves the player based on current movement direction and velocity.
     */
    public void move() {
        calculateMovementDirection();
        velocity.x += PLAYER_ACCELERATION * direction.x;
        velocity.y += PLAYER_ACCELERATION * direction.y;

        velocity.x = MathUtils.clamp(velocity.x, -MAX_PLAYER_VELOCITY, MAX_PLAYER_VELOCITY);
        velocity.y = MathUtils.clamp(velocity.y, -MAX_PLAYER_VELOCITY, MAX_PLAYER_VELOCITY);

        this.applyFriction();

        // Keep player within the bounds of the screen
        this.bounds();

        // Update the position based on the velocity
        pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
        getHitbox().setPosition(pos);
    }

    private void applyFriction() {
        if (direction.x == 0) {
            velocity.x = approachZero(velocity.x);
        }
        if (direction.y == 0) {
            velocity.y = approachZero(velocity.y);
        }
    }

    private float approachZero(float value) {
        return (value > 0) ? Math.max(0, value - PLAYER_FRICTION) : Math.min(0, value + PLAYER_FRICTION);
    }

    private void bounds() {
        pos.x = Math.max(0, Math.min(pos.x, WINDOW_WIDTH - getHitbox().width));
        pos.y = Math.max(0, Math.min(pos.y, WINDOW_HEIGHT - getHitbox().height));
    }

    public void setHealth(int playerHealth) {
        this.health = playerHealth;
    }
}
