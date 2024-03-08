package inf112.skeleton.app.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static inf112.skeleton.app.Constants.PLAYER_WIDTH;
import static inf112.skeleton.app.Constants.WINDOW_WIDTH;

public class Entity {
    //Field vars
    Rectangle hitbox;
    String spriteSheetPath;
    protected int spriteSheetX;
    protected int spriteSheetY;
    protected int spriteHeight;
    protected int spriteWidth;

    //Methods
    public float getX() { return hitbox.x; }

    public float getY() { return hitbox.y; }

    public Rectangle getHitbox() { return hitbox; }

    public int getSpriteSheetX() { return spriteSheetX; }

    public int getSpriteSheetY() { return spriteSheetY; }

    public int getSpriteHeight() { return spriteHeight; }

    public int getSpriteWidth() { return spriteWidth; }

    public String getSpriteSheetPath() { return this.spriteSheetPath; }

    public void setPos(float x, float y){
        hitbox.x = x;
        hitbox.y = y;
    }

    public void moveTowards(float tX, float tY, float speed){
        float diffX = tX - hitbox.x;
        float diffY = tY - hitbox.y;
        hitbox.x += diffX / 100;
        hitbox.y += diffY / 100;
    }
}
