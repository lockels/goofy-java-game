package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Represents a spike in the game.
 * Extends the {@link Entity} class.
 */
public class Spike extends Entity {
    private int damage;

    /**
     * Constructs a new Spike with the specified parameters.
     *
     * @param body            The Box2D body representing the spike.
     * @param textureIdentifier The identifier for the spike's texture.
     * @param damage          The amount of damage the spike inflicts.
     * @param spriteHeight    The height of the spike's sprite.
     * @param spriteWidth     The width of the spike's sprite.
     */
    public Spike(Body body, String textureIdentifier, int damage, float spriteHeight, float spriteWidth) {
        super(body, textureIdentifier, "Spike", spriteHeight, spriteWidth);
        this.damage = damage;
    }

    /**
     * Gets the amount of damage the spike inflicts.
     *
     * @return The amount of damage the spike inflicts.
     */
    public int getDamage() {
        return damage;
    }
}
