package inf112.skeleton.app.entities;

import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Entity{
    public Enemy(Rectangle hitbox, String spriteSheetPath, int spriteSheetX, int spriteSheetY, int spriteHeight, int spriteWidth) {
        this.hitbox = hitbox;
        this.spriteSheetPath = spriteSheetPath;
        this.spriteSheetX = spriteSheetX;
        this.spriteSheetY = spriteSheetY;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
    }
}
