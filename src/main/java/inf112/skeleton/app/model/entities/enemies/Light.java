package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

/**
 * Represents a light enemy in the game.
 * Extends the {@link Enemy} class.
 */
public class Light extends Enemy{

    /**
     * Constructs a new light enemy with default parameters.
     *
     * @param world The Box2D {@link World} in which the light enemy resides.
     */
    public Light(World world) {
        super(PhysicsFactory.createDynamicEntityBody(world,
                new Vector2(),
                LIGHT_ENEMY_WIDTH,
                LIGHT_ENEMY_HEIGHT,
                true),
                LIGHT_ENEMY_SPRITE,
                LIGHT_ENEMY_HEIGHT,
                LIGHT_ENEMY_WIDTH,
                LIGHT_ENEMY_SPEED,
                LIGHT_ENEMY_HEALTH);
    }
}
