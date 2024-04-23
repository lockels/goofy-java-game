package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.utils.Constants;

public class PhysicsFactory {
    public static Body createEntityBody(World world, Vector2 position, float width, float height, String bodyType, boolean collisionEnabled) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = switch (bodyType){
            case "dynamic"   -> BodyDef.BodyType.DynamicBody;
            case "static"    -> BodyDef.BodyType.StaticBody;
            case "kinematic" -> BodyDef.BodyType.KinematicBody;
            default          -> throw new IllegalStateException("Unexpected value: " + bodyType);
        };
        bodyDef.position.set(position.x / Constants.PPM, position.y / Constants.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = !collisionEnabled; //Inverted for ease of use
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        shape.dispose();

        return body;
    }
}
