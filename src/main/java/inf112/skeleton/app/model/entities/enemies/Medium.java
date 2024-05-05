package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

/**
 * Represents a medium enemy in the game.
 * Extends the {@link Enemy} class.
 */
public class Medium extends Enemy{

    /**
     * Constructs a new medium enemy with default parameters.
     *
     * @param world The Box2D {@link World} in which the medium enemy resides.
     */
    public Medium(World world) {
        super(PhysicsFactory.createDynamicEntityBody(world,
                new Vector2(),
                MEDIUM_ENEMY_WIDTH,
                MEDIUM_ENEMY_HEIGHT,
                true),
                MEDIUM_ENEMY_SPRITE,
                MEDIUM_ENEMY_HEIGHT,
                MEDIUM_ENEMY_WIDTH,
                MEDIUM_ENEMY_SPEED,
                MEDIUM_ENEMY_HEALTH);
    }
}
