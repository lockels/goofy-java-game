package inf112.skeleton.app.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

public class Enemy extends Entity {
    private Vector2 velocity;
    private Vector2 direction;
    private Vector2 pos;
    private float speed;

    public Enemy(Rectangle hitBox, String dungeon_sheet, int spriteSheetX, int spriteSheetY,
            int spriteWidth, int spriteHeight, float speed) {
        super(hitBox, dungeon_sheet, spriteSheetX, spriteSheetY, spriteWidth, spriteHeight);
        this.speed = speed;
        pos = new Vector2(hitBox.x, hitBox.y);
        velocity = new Vector2();
        direction = new Vector2();
    }

    public void moveTowards(float tX, float tY) {
        float diffX = tX - hitbox.x;
        float diffY = tY - hitbox.y;
        float angle = MathUtils.atan2(diffY, diffX);
        velocity.set(MathUtils.cos(angle) * speed, MathUtils.sin(angle) * speed);

        // Update the position based on the velocity
        pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
        hitbox.setPosition(pos);
    }
}
