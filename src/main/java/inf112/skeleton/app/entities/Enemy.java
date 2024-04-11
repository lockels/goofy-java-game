package inf112.skeleton.app.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

public class Enemy extends Entity {
    private Vector2 velocity;
    private Vector2 pos;
    private float speed;
    private Vector2 direction;

    public Enemy(Rectangle hitBox, String dungeon_sheet, int spriteSheetX, int spriteSheetY,
            int spriteWidth, int spriteHeight, float baseAngle, int originX, int originY, String type, float speed) {
        super(hitBox, dungeon_sheet, spriteSheetX, spriteSheetY, spriteWidth, spriteHeight, originX, originY, baseAngle, type);
        this.speed = speed;
        pos = new Vector2(hitBox.x, hitBox.y);
        velocity = new Vector2();
        direction = new Vector2();
    }

    public void moveTowards(float targetX, float targetY) {
        float diffX = targetX - hitbox.x;
        float diffY = targetY - hitbox.y;
        float angle = MathUtils.atan2(diffY, diffX);
        velocity.set(MathUtils.cos(angle) * speed, MathUtils.sin(angle) * speed);

        // Update the position based on the velocity
        pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
        hitbox.setPosition(pos);
    }

    public void moveAway(float targetX, float targetY) {
        float diffX = hitbox.x - targetX;  
        float diffY = hitbox.y - targetY; 
        float distance = (float) Math.sqrt(diffX * diffX + diffY * diffY);  // Calculate the distance between the target and the entity

        // Normalize the difference and multiply by the desired speed to move away
        if (distance > 0) {
            diffX /= distance;
            diffY /= distance;
        }

        // Calculate the new position away from the target
        float newX = hitbox.x + diffX * speed * Gdx.graphics.getDeltaTime();
        float newY = hitbox.y + diffY * speed * Gdx.graphics.getDeltaTime();

        hitbox.setPosition(newX, newY);  // Update the position to move away from the target
    }
}
