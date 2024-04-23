package inf112.skeleton.app.model.entities;

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

    public void setPos(float x, float y) {this.body.setTransform(x, y, getAngle());}
    public void setAngle(float angle) {this.body.setTransform(getX(),getY(), (float) -Math.toRadians(angle));}

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
