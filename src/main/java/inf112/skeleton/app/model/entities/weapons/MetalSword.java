package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class MetalSword extends Weapon {
    // private boolean isActive = true; // Initially inactive

    public MetalSword(World world) {
        super(PhysicsFactory.createEntityBody(world,
                new Vector2(0, 0),
                new Vector2(METAL_X_OFFSET, METAL_Y_OFFSET),
                METAL_WIDTH,
                METAL_HEIGHT,
                false),
                METAL_SWORD_IMG,
                METAL_HEIGHT,
                METAL_WIDTH,
                METAL_DMG,
                METAL_KNOCKBACK,
                METAL_COOLDOWN,
                METAL_STUN);
        this.setOffset(new Vector2(METAL_X_OFFSET, METAL_Y_OFFSET));
    }

    // // Method to toggle visibility/activation
    // public void toggleActive() {
    //     isActive = !isActive;
    // }

    // public boolean isActive() {
    //     return isActive;
    // }

    @Override
    public void render(SpriteBatch batch) {
        // if (isActive) {
            super.render(batch);
        // }
    }
}
