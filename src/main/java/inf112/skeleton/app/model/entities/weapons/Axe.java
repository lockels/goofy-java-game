package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class Axe extends Weapon {
    public Axe(World world) {
        super(PhysicsFactory.createStaticEntityBody(world,
                new Vector2(0, 0),
                AXE_WIDTH,
                AXE_HEIGHT),
                AXE_SPRITE,
                AXE_HEIGHT,
                AXE_WIDTH,
                AXE_DMG,
                AXE_KNOCKBACK,
                AXE_COOLDOWN,
                AXE_STUN);
        this.setOffset(new Vector2(AXE_X_OFFSET, AXE_Y_OFFSET));
    }
}
