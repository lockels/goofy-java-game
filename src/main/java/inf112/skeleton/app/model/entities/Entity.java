package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
    protected Body body; // Box2D body
    protected String textureIdentifer;

    public Entity(Body body, String textureIdentifer) {
        this.body = body;
        this.textureIdentifer = textureIdentifer;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public Body getBody() {
        return body;
    }

    public String getTextureId() {
        return textureIdentifer;
    }

    public float getAngle() {
        return body.getAngle();
    }
}
