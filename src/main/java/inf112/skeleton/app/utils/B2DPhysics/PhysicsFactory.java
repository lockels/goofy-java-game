package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import inf112.skeleton.app.utils.Constants;

import static inf112.skeleton.app.utils.Constants.PPM;

/**
 * Utility class for creating Box2D physics bodies and fixtures.
 * Provides methods for creating various types of bodies and fixtures for game entities.
 */
public abstract class PhysicsFactory {

    /**
     * Creates a Box2D body with the specified parameters.
     *
     * @param world     The Box2D world in which to create the body.
     * @param position  The position of the body.
     * @param bodyType  The type of the body (dynamic, static, etc.).
     * @return The created Box2D body.
     */
    private static Body createBody(World world, Vector2 position, BodyDef.BodyType bodyType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position.x / PPM, position.y / PPM);

        return world.createBody(bodyDef);
    }

    /**
     * Creates a Box2D fixture with the specified parameters.
     *
     * @param body      The body to which the fixture should be added.
     * @param width     The width of the fixture.
     * @param height    The height of the fixture.
     * @param isSensor  Indicates if the fixture should be a sensor.
     * @return The created Box2D fixture.
     */
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

    /**
     * Creates a dynamic Box2D body with the specified parameters.
     *
     * @param world            The Box2D world in which to create the body.
     * @param position         The position of the body.
     * @param width            The width of the body.
     * @param height           The height of the body.
     * @param collisionEnabled Indicates if the body should have collision enabled.
     * @return The created Box2D body.
     */
    public static Body createDynamicEntityBody(World world, Vector2 position, float width, float height, boolean collisionEnabled) {
        Body body = createBody(world, position, BodyDef.BodyType.DynamicBody);
        createFixture(body, width, height, !collisionEnabled);
        return body;
    }

    /**
     * Creates a static Box2D body with the specified parameters.
     *
     * @param world     The Box2D world in which to create the body.
     * @param position  The position of the body.
     * @param width     The width of the body.
     * @param height    The height of the body.
     * @return The created Box2D body.
     */
    public static Body createStaticEntityBody(World world, Vector2 position, float width, float height) {
        Body body = createBody(world, position, BodyDef.BodyType.StaticBody);
        Fixture fixture = createFixture(body, width, height, true);
        fixture.setUserData(body);
        return body;
    }

    /**
     * Creates a dynamic Box2D body with an offset and the specified parameters.
     *
     * @param world            The Box2D world in which to create the body.
     * @param position         The position of the body.
     * @param offset           The offset of the body.
     * @param width            The width of the body.
     * @param height           The height of the body.
     * @param collisionEnabled Indicates if the body should have collision enabled.
     * @return The created Box2D body.
     */
    public static Body createEntityBody(World world, Vector2 position, Vector2 offset, float width, float height, boolean collisionEnabled) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x / Constants.PPM, position.y / Constants.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 * Constants.PPM, height / 2 * Constants.PPM, offset, 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = !collisionEnabled;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

}
