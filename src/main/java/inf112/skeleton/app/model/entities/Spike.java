package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Spike extends Entity {
    private int damage;

    public Spike(Body body, String textureIdentifier, int damage, float spriteHeight, float spriteWidth) {
        super(body, textureIdentifier, "Spike", spriteHeight, spriteWidth);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
