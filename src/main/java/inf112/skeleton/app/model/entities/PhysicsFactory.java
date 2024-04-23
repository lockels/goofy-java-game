package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.utils.Constants;

public class PhysicsFactory {
    public static Body createEntityBody(World world, Vector2 position, Vector2 offset, float width, float height, boolean collisionEnabled) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x / Constants.PPM, position.y / Constants.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM, offset, 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = !collisionEnabled; //Inverted for ease of use
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        shape.dispose();

        return body;
    }
}
