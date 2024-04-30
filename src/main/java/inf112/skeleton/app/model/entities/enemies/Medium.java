package inf112.skeleton.app.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.entities.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

public class Medium extends Enemy{
    public Medium(World world) {
        super(PhysicsFactory.createEntityBody(
                world,
                new Vector2(),
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
