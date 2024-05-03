package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;
import inf112.skeleton.app.model.entities.weapons.Weapon;

import static inf112.skeleton.app.utils.Constants.*;

public class RedSword extends Weapon {
    private boolean isActive = false; // Initially inactive

    public RedSword(World world) {
        super(PhysicsFactory.createEntityBody(world,
                new Vector2(0, 0),
                new Vector2(RED_SWORD_X_OFFSET, RED_SWORD_Y_OFFSET),
                RED_SWORD_WIDTH,
                RED_SWORD_HEIGHT,
                false
                ),
                RED_SWORD_SPRITE,
                RED_SWORD_HEIGHT,
                RED_SWORD_WIDTH,
                RED_SWORD_DMG,
                RED_SWORD_KNOCKBACK,
                RED_SWORD_COOLDOWN,
                RED_SWORD_STUN);
        this.setOffset(new Vector2(RED_SWORD_X_OFFSET, RED_SWORD_Y_OFFSET));

    }


    // Method to toggle visibility/activation
    public void toggleActive() {
        isActive = !isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isActive) {
            super.render(batch);
        }
    }
}
