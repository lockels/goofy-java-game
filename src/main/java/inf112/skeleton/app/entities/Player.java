package inf112.skeleton.app.entities;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

import java.util.HashMap;
import java.util.Map;

import static inf112.skeleton.app.Constants.*;

public class Player extends Entity{
    private Vector2 velocity;
    private Vector2 direction;
    private Vector2 pos;
    private Map<Direction, Boolean> moveDirections;

    public Player(Rectangle hitBox, String spriteSheetPath, int spriteSheetX, int spriteSheetY, int spriteHeight, int spriteWidth) {
        this.hitbox = hitBox;
        pos = new Vector2(hitBox.x, hitBox.y);
        velocity = new Vector2();
        direction = new Vector2();

        //Sprite
        this.spriteSheetPath = spriteSheetPath;
        this.spriteSheetX = spriteSheetX;
        this.spriteSheetY = spriteSheetY;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;

        //Movement Directions
        moveDirections = new HashMap<>();
        for (Direction dir : Direction.values()){
            moveDirections.put(dir, false);
        }
    }


    public Map<Direction, Boolean> getMovementDirections() { return moveDirections; }


    public void setMovement(Direction direction, boolean isActive) {
        moveDirections.put(direction, isActive);
    }

    private void calculateMovementDirection(){
        //Vertical
        if ((moveDirections.get(Direction.UP)) == (moveDirections.get(Direction.DOWN))){
            direction.y = 0;
        } else if (moveDirections.get(Direction.UP)) {
            direction.y = 1;
        } else if (moveDirections.get(Direction.DOWN)) {
            direction.y = -1;
        }

        //Horizontal
        if ((moveDirections.get(Direction.LEFT)) == (moveDirections.get(Direction.RIGHT))){
            direction.x = 0;
        } else if (moveDirections.get(Direction.LEFT)) {
            direction.x = -1;
        } else if (moveDirections.get(Direction.RIGHT)) {
            direction.x = 1;
        }
    }


    public void move() {
        calculateMovementDirection();
        velocity.x += PLAYER_ACCELERATION * direction.x;
        velocity.y += PLAYER_ACCELERATION * direction.y;

        velocity.x = MathUtils.clamp(velocity.x, -MAX_PLAYER_VELOCITY, MAX_PLAYER_VELOCITY);
        velocity.y = MathUtils.clamp(velocity.y, -MAX_PLAYER_VELOCITY, MAX_PLAYER_VELOCITY);

        applyFriction();

        // Keep player within the bounds of the screen
        bounds();

        // Update the position based on the velocity
        pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
        hitbox.setPosition(pos);
    }

    private void applyFriction() {
        if (direction.x == 0) {
            velocity.x = approachZero(velocity.x);
        }
        if (direction.y == 0) {
            velocity.y = approachZero(velocity.y);
        }
    }

    private float approachZero(float value) {
        return (value > 0) ? Math.max(0, value - PLAYER_FRICTION) : Math.min(0, value + PLAYER_FRICTION);
    }

    private void bounds() {
        pos.x = Math.max(0, Math.min(pos.x, WINDOW_WIDTH - PLAYER_WIDTH));
        pos.y = Math.max(0, Math.min(pos.y, WINDOW_HEIGHT - PLAYER_HEIGHT));
    }
}
