package inf112.skeleton.app.model.entities;

import static inf112.skeleton.app.utils.Constants.COIN_HEIGHT;
import static inf112.skeleton.app.utils.Constants.COIN_WIDTH;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Represents a coin in the game.
 * Extends the {@link Entity} class.
 * This class provides attributes and behavior for a collectible coin.
 */
public class Coin extends Entity {
    private int value;
    private boolean collected;
    
    
    /**
     * Constructs a new Coin with the specified parameters.
     *
     * @param body        The physics body associated with this coin.
     * @param textureId   The identifier for the texture to render this coin.
     * @param value       The monetary value of the coin.
     * @param tag         The tag or identifier of the entity.
     */
    public Coin(Body body, String textureId, int value, String tag) {
        super(body, textureId, tag, COIN_HEIGHT, COIN_WIDTH);
        this.value = value;
        this.collected = false;
    }
 
    /**
     * Gets the value of the coin.
     *
     * @return The value of the coin.
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Marks the coin as collected.
     */
    public void setCollected() {
        super.setIsDestroyed(true);
        collected = true;
        
    }

    /**
     * Checks if the coin has been collected.
     *
     * @return True if the coin has been collected, otherwise false.
     */
    public boolean isCollected() {
        return collected;
        
        
    }
}

