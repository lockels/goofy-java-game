package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class GreenSword extends Weapon {
    private boolean isActive = false; // Initially inactive

    public GreenSword(World world) {
        super(PhysicsFactory.createEntityBody(world,
        new Vector2(0, 0),
        new Vector2(GREEN_SWORD_X_OFFSET, GREEN_SWORD_Y_OFFSET),
        GREEN_SWORD_WIDTH,
        GREEN_SWORD_HEIGHT,
        false
        ),
        GREEN_SWORD_SPRITE,
        GREEN_SWORD_HEIGHT,
        GREEN_SWORD_WIDTH,
        GREEN_SWORD_DMG,
        GREEN_SWORD_KNOCKBACK,
        GREEN_SWORD_COOLDOWN,
        GREEN_SWORD_STUN);
this.setOffset(new Vector2(GREEN_SWORD_X_OFFSET, GREEN_SWORD_Y_OFFSET));

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

