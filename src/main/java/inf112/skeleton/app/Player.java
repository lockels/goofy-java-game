package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static inf112.skeleton.app.Constants.*;

public class Player {

    private Vector2 pos;
    private Vector2 velocity;
    private Vector2 direction;
    private Rectangle hitbox;
    private String spriteStr;

    public Player() {
        pos = new Vector2(WINDOW_WIDTH / 2f - PLAYER_WIDTH / 2f, 20);
        velocity = new Vector2();
        direction = new Vector2();
        hitbox = new Rectangle(pos.x, pos.y, PLAYER_WIDTH, PLAYER_HEIGHT);
        spriteStr = "playerSprite.png"; 
    }

    public float getX() { return this.pos.x; }

    public float getY() { return this.pos.y; }
    
    public String getSprite() { return this.spriteStr; }


    public void setMovement(Direction direction, boolean move) {
        switch (direction) {
            case LEFT:
                this.direction.x = move ? -1 : (this.direction.x == -1 ? 0 : this.direction.x);
                break;
            case RIGHT:
                this.direction.x = move ? 1 : (this.direction.x == 1 ? 0 : this.direction.x);
                break;
            case UP:
                this.direction.y = move ? 1 : (this.direction.y == 1 ? 0 : this.direction.y);
                break;
            case DOWN:
                this.direction.y = move ? -1 : (this.direction.y == -1 ? 0 : this.direction.y);
                break;
        }
    }

    public void move() {
        velocity.x += PLAYER_ACCELERATION * direction.x;
        velocity.y += PLAYER_ACCELERATION * direction.y;

        velocity.x = MathUtils.clamp(velocity.x, -MAX_PLAYER_VELOCITY, MAX_PLAYER_VELOCITY);
        velocity.y = MathUtils.clamp(velocity.y, -MAX_PLAYER_VELOCITY, MAX_PLAYER_VELOCITY);

        applyFriction();
      
        // Update the position based on the velocity
        pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
        hitbox.setPosition(pos);
        
        // Keep player within the bounds of the screen
        bounds();
    }

    private void applyFriction() {
          if (direction.x == 0) {
              velocity.x = approachZero(velocity.x, PLAYER_FRICTION);
          }
          if (direction.y == 0) {
              velocity.y = approachZero(velocity.y, PLAYER_FRICTION);
          }
    }

    private float approachZero(float value, float amount) {
        return (value > 0) ? Math.max(0, value - amount) : Math.min(0, value + amount);
    }

    private void bounds() {
        pos.x = Math.max(0, Math.min(pos.x, WINDOW_WIDTH - PLAYER_WIDTH));
        pos.y = Math.max(0, Math.min(pos.y, WINDOW_WIDTH - PLAYER_WIDTH));
    }
}
