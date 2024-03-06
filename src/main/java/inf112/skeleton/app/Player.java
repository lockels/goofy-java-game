package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Dictionary;
import java.util.Hashtable;

import static inf112.skeleton.app.Constants.*;

public class Player {

    private Vector2 pos;
    private Vector2 velocity;
    private Vector2 direction;
    private Rectangle hitbox;
    private String spriteStr;
    private Dictionary<Direction, Boolean> moveDirections;

    public Player() {
        pos = new Vector2(WINDOW_WIDTH / 2f - PLAYER_WIDTH / 2f, 20);
        velocity = new Vector2();
        direction = new Vector2();
        hitbox = new Rectangle(pos.x, pos.y, PLAYER_WIDTH, PLAYER_HEIGHT);
        spriteStr = "playerSprite.png";
        //Movement Directions
        moveDirections = new Hashtable<>();
        moveDirections.put(Direction.UP, false);
        moveDirections.put(Direction.DOWN, false);
        moveDirections.put(Direction.LEFT, false);
        moveDirections.put(Direction.RIGHT, false);
    }

    public float getX() { return this.pos.x; }

    public float getY() { return this.pos.y; }
    
    public String getSprite() { return this.spriteStr; }


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
      
        // Update the position based on the velocity
        pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
        hitbox.setPosition(pos);
        
        // Keep player within the bounds of the screen
        bounds();
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
        pos.y = Math.max(0, Math.min(pos.y, WINDOW_WIDTH - PLAYER_WIDTH));
    }
}
