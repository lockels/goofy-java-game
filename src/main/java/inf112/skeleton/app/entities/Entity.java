package inf112.skeleton.app.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    // Field vars
    Rectangle hitbox;
    String spriteSheetPath;
    protected int spriteSheetX;
    protected int spriteSheetY;
    protected int spriteHeight;
    protected int spriteWidth;

    public Entity(Rectangle hitbox, String spriteSheet, int spriteSheetX, int spriteSheetY, int spriteWidth, int spriteHeight) {
        this.hitbox = hitbox;
        this.spriteSheetPath = spriteSheet;
        this.spriteSheetX = spriteSheetX;
        this.spriteSheetY = spriteSheetY;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
    }

    public float getX() {
        return hitbox.x;
    }

    public float getY() {
        return hitbox.y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getSpriteSheetX() {
        return spriteSheetX;
    }

    public int getSpriteSheetY() {
        return spriteSheetY;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public String getSpriteSheetPath() {
        return this.spriteSheetPath;
    }

    public boolean collidesWith(Entity other) {
        return hitbox.overlaps(other.getHitbox());
    }

    public void move(float x, float y) {
        hitbox.setPosition(x, y);
    }
}
