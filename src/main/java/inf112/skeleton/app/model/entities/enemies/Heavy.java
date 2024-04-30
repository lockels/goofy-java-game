package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.entities.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class Heavy extends Enemy{
    public Heavy(World world) {
        super(PhysicsFactory.createEntityBody(
                world,
                new Vector2(),
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
