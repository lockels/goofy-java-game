package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class Dagger extends Weapon {
    public Dagger(World world) {
        super(PhysicsFactory.createStaticEntityBody(world,
                new Vector2(0, 0),
                DAGGER_WIDTH,
                DAGGER_HEIGHT),
            DAGGER_SPRITE, DAGGER_HEIGHT,
            DAGGER_WIDTH, DAGGER_DMG,
            DAGGER_KNOCKBACK, DAGGER_COOLDOWN, DAGGER_STUN);
        this.setOffset(new Vector2(DAGGER_X_OFFSET, DAGGER_Y_OFFSET));
    }
}
