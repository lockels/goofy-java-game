package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public Player(Body body, String textureId) {
        super(body, textureId);
        health = PLAYER_HEALTH;

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

}
