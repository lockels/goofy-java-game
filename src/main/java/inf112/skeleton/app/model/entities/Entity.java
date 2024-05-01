package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
    protected Body body; // Box2D body
    protected String textureIdentifer;
    protected String tag;
    protected Vector2 offset = new Vector2();
    protected boolean isActive = true;
    protected boolean isDestroyed = false;
    protected float opacity = 1f;
    protected float spriteHeight;
    protected float spriteWidth;

    public Entity(Body body, String textureIdentifier, String tag, float spriteHeight, float spriteWidth) {
        this.body = body;
        this.textureIdentifer = textureIdentifier;
        this.tag = tag;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        this.body.getFixtureList().get(0).setUserData(this);
    }

    public boolean getIsDestroyed() { return isDestroyed; }
    public void setIsDestroyed(boolean isDestroyed) { this.isDestroyed = isDestroyed; }

    public Vector2 getOffset() { return offset; }

    public void setOffset(Vector2 offset) { this.offset = offset; }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setPos(float x, float y) {this.body.setTransform(x, y, getAngle());}

    public void setAngle(float angle) {
        this.body.setTransform(getX(),getY(), (float) -Math.toRadians(angle)); // Degrees -> Radians
    }

    public Body getBody() {
        return body;
    }

    public String getTag() { return tag; }

    public boolean isActive() { return isActive; }

    public String getTextureId() {
        return textureIdentifer;
    }

    public float getAngle() { return (float) Math.toDegrees(body.getAngle()); } // Radians -> Degrees

    public void trigger() {
        System.out.println(tag + " has been triggered!");
    }

    public Vector2 trigVector(float hyp, float angle) { //Gets x,y coords from hypotenuse and angle using trigonometry
        float x = (float) Math.sin(Math.toRadians(angle)) * hyp;
        float y = (float) Math.cos(Math.toRadians(angle)) * hyp;
        if ((angle <= 90)||(angle >= 270)) {x *= -1; }
        if (angle >= 180) { y *= -1; }
        return new Vector2(x, y);
    }

    public float getOpacity() { return opacity; }

    public void setOpacity(float opacity) { this.opacity = opacity; }

    public float getSpriteHeight() {
        return spriteHeight;
    }

    public float getSpriteWidth() {
        return spriteWidth;
    }

}

