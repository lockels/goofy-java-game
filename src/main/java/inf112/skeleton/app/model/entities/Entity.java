package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
    protected Body body; // Box2D body
    protected String textureIdentifer;
    protected String tag;
    protected float baseAngle = 0;
    protected boolean isActive = true;
    protected boolean isDestroyed = false;

    public Entity(Body body, String textureIdentifier, String tag) {
        this.body = body;
        this.textureIdentifer = textureIdentifier;
        this.tag = tag;
    }

    public boolean getIsDestroyed() { return isDestroyed; }
    public void setIsDestroyed(boolean isDestroyed) { this.isDestroyed = isDestroyed; }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setPos(float x, float y) {this.body.setTransform(x, y, getAngle());}
    public void setAngle(float angle) {
        this.body.setTransform(getX(),getY(), (float) -Math.toRadians(baseAngle + angle)); // Degrees -> Radians
    }

    public Body getBody() {
        return body;
    }
    public String getTag() { return tag; }
    public boolean isActive() { return isActive; }

    public String getTextureId() {
        return textureIdentifer;
    }

    public float getAngle() {
        return (float) Math.toDegrees(body.getAngle());
    } // Radians -> Degrees

    public float getBaseAngle() { return this.baseAngle; }
    public void setBaseAngle(float baseAngle) { this.baseAngle = baseAngle; }

    public void trigger() {
        System.out.println(tag + " has been triggered!");
    }
}
