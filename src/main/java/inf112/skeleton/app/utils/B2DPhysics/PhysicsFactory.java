package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static inf112.skeleton.app.utils.Constants.PPM;

public abstract class PhysicsFactory {

    private static Body createBody(World world, Vector2 position, BodyDef.BodyType bodyType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position.x / PPM, position.y / PPM);

        return world.createBody(bodyDef);
    }

    private static Fixture createFixture(Body body, float width, float height, boolean isSensor) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = isSensor;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();

        return fixture;
    }

    public static Body createDynamicEntityBody(World world, Vector2 position, float width, float height, boolean collisionEnabled) {
        Body body = createBody(world, position, BodyDef.BodyType.DynamicBody);
        createFixture(body, width, height, !collisionEnabled);
        return body;
    }

    public static Body createStaticEntityBody(World world, Vector2 position, float width, float height) {
        Body body = createBody(world, position, BodyDef.BodyType.StaticBody);
        Fixture fixture = createFixture(body, width, height, true);
        fixture.setUserData(body);
        return body;
    }
}
