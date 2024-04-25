package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.model.Direction;

import java.util.EnumMap;
import java.util.Map;

import static inf112.skeleton.app.model.Direction.*;
import static inf112.skeleton.app.utils.Constants.*;

public class Player extends Entity {
    private Map<Direction, Boolean> moveDirections;
    private int health;
    private int coinCount;

    public Player(Body body, String textureId, String tag) {
        super(body, textureId, tag);
        health = PLAYER_HEALTH;
        coinCount = 0;

        System.out.println("Player: Created");

        // Movement Directions
        moveDirections = new EnumMap<>(Direction.class);
        for (Direction dir : Direction.values()) {
            moveDirections.put(dir, false);
        }
    }

    public int getHealth() {
        return health;
    }

    
    /**
     * This method simulates the coin being collected by the player.
     * Once collected, the coin is marked and deactivated in the physics simulation.
     *
     * @param player the player collecting the coin
     */
    public void collect(Coin coin) {
        if (!coin.isCollected()) {
            this.addCoins(coin.getValue());
            coin.setCollected();
            body.setActive(false); // Deactivate the physics body so it no longer interacts in the world
        }
    }

    private void addCoins(int value) {
        this.coinCount += value;
	}

	public void takeDamage(int damage) {
        this.health -= damage;
    }

    public Map<Direction, Boolean> getMovementDirections() {
        return moveDirections;
    }

    public void setMovement(Direction direction, boolean isActive) {
        moveDirections.put(direction, isActive);
    }

    public void move() {
        // Calculate movement direction
        Vector2 direction = new Vector2();
        // System.out.println("Player: Moving");
        for (Map.Entry<Direction, Boolean> entry : moveDirections.entrySet()) {
            // System.out.println("Player: Direction: " + entry.getKey() + " " + entry.getValue());
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
                }
            }
        }
        direction.nor(); // Normalize to get unit vector

        // Apply force based on direction
        body.applyForceToCenter(direction.scl(PLAYER_ACCELERATION), true);
    }

    public void setHealth(int playerHealth) {
        this.health = playerHealth;
    }

    public boolean collidesWith(Enemy enemy) {
        return body.getPosition().dst(enemy.getBody().getPosition()) < PLAYER_COLLISION_RADIUS;
    }
}
