package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;
import inf112.skeleton.app.model.entities.weapons.Weapon;

import static inf112.skeleton.app.utils.Constants.*;

public class Sword extends Weapon {
    public Sword(World world) {
        // super(PhysicsFactory.createStaticEntityBody(world,
        //         new Vector2(0, 0),
        //         SWORD_WIDTH ,
        //         SWORD_HEIGHT),
        //         SWORD_SPRITE,
        //         SWORD_HEIGHT,
        //         SWORD_WIDTH,
        //         SWORD_DMG,
        //         SWORD_KNOCKBACK,
        //         SWORD_COOLDOWN,
        //         SWORD_STUN);
        // this.setOffset(new Vector2(SWORD_X_OFFSET, SWORD_Y_OFFSET));
        super(PhysicsFactory.createEntityBody(world,
                new Vector2(0, 0),
                new Vector2(SWORD_X_OFFSET, SWORD_Y_OFFSET),
                SWORD_WIDTH,
                SWORD_HEIGHT,
                false
                ),
                SWORD_SPRITE,
                SWORD_HEIGHT,
                SWORD_WIDTH,
                SWORD_DMG,
                SWORD_KNOCKBACK,
                SWORD_COOLDOWN,
                SWORD_STUN);
        this.setOffset(new Vector2(SWORD_X_OFFSET, SWORD_Y_OFFSET));

    }
}
