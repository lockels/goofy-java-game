package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Rectangle;

public class Sword extends Entity {
    public Sword(Rectangle hitbox, String spriteSheet, int spriteSheetX, int spriteSheetY, int spriteWidth, int spriteHeight, int originX, int originY, float baseAngle, String type) {
        super(hitbox, spriteSheet, spriteSheetX, spriteSheetY, spriteWidth, spriteHeight, originX, originY, baseAngle, type);
    }

}
