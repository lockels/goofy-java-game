package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static inf112.skeleton.app.utils.Constants.*;

public abstract class PhysicsFactory {
    public static Body createEntityBody(World world, Vector2 position, Vector2 offset, float width, float height, boolean collisionEnabled) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x / PPM, position.y / PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM, offset, 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = !collisionEnabled; 
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        shape.dispose();
        return body;
    }
}
