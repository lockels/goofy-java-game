package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.entities.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class Light extends Enemy{
    public Light(World world) {
        super(PhysicsFactory.createEntityBody(
                world,
                new Vector2(),
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
