package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Coin extends Entity {
    private int value;
    private boolean collected;
    
    /**
     * Constructs a new Coin with the specified parameters.
     *
     * @param body        the physics body associated with this coin
     * @param textureId   the identifier for the texture to render this coin
     * @param value       the monetary value of the coin
     * @param tag         the tag or indentifier of the entity
     */

    public Coin(Body body, String textureId, int value, String tag) {
        super(body, textureId, tag);
        this.value = value;
        this.collected = false;
    }

    public int getValue() {
        return value;
    }
    
    public void setCollected() {
        collected = true;
    }

    public boolean isCollected() {
        return collected;
    }
}
