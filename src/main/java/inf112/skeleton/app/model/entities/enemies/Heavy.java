package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

/**
 * Represents a heavy enemy in the game.
 * Extends the {@link Enemy} class.
 */
public class Heavy extends Enemy {

    /**
     * Constructs a new Heavy enemy with default parameters.
     *
     * @param world The Box2D {@link World} in which the heavy enemy resides.
     */
    public Heavy(World world) {
        super(PhysicsFactory.createDynamicEntityBody(world,
                new Vector2(),
                HEAVY_ENEMY_WIDTH,
                HEAVY_ENEMY_HEIGHT,
                true),
                HEAVY_ENEMY_SPRITE,
                HEAVY_ENEMY_HEIGHT,
                HEAVY_ENEMY_WIDTH,
                HEAVY_ENEMY_SPEED,
                HEAVY_ENEMY_HEALTH);
    }
}
