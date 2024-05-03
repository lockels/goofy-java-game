// package inf112.skeleton.app.model.entities.weapons;

// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.World;
// import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

// import static inf112.skeleton.app.utils.Constants.*;

// public class TreeSword extends Weapon {
//     public TreeSword(World world) {
//         super(PhysicsFactory.createEntityBody(world,
//                 new Vector2(0, 0),
//                 new Vector2(TREE_X_OFFSET, TREE_Y_OFFSET),
//                 TREE_WIDTH,
//                 TREE_HEIGHT,
//                 false
//                 ),
//                 TREE_SPRITE,
//                 TREE_HEIGHT,
//                 TREE_WIDTH,
//                 TREE_DMG,
//                 TREE_KNOCKBACK,
//                 TREE_COOLDOWN,
//                 TREE_STUN);
//         this.setOffset(new Vector2(TREE_X_OFFSET, TREE_Y_OFFSET));
//     }
// }

package inf112.skeleton.app.model.entities.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class TreeSword extends Weapon {
    private boolean isActive = false; // Initially inactive

    public TreeSword(World world) {
        super(PhysicsFactory.createEntityBody(world,
                new Vector2(0, 0),
                new Vector2(TREE_X_OFFSET, TREE_Y_OFFSET),
                TREE_WIDTH,
                TREE_HEIGHT,
                false
                ),
                SWORD_ONE,
                TREE_HEIGHT,
                TREE_WIDTH,
                TREE_DMG,
                TREE_KNOCKBACK,
                TREE_COOLDOWN,
                TREE_STUN);
        this.setOffset(new Vector2(TREE_X_OFFSET, TREE_Y_OFFSET));
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
