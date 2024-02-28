package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import static inf112.skeleton.app.Constants.*;

public class Player {
    int x;
    int y;
    float velocityX = 0;
    float velocityY = 0;
    boolean leftMove = false;
    boolean rightMove = false;
    boolean upMove = false;
    boolean downMove = false;
    Rectangle hitBox;
    String spriteStr;
    public Player() {
        x = Constants.WINDOW_WIDTH/2 - 64/2;
        y = 20;
        hitBox = new Rectangle(x, y, 64, 64);
        spriteStr = "playerSprite.png";
    }

    public void setLeftMove(boolean bool) {
        leftMove = bool;
    }
    public void setRightMove(boolean bool) {
        rightMove = bool;
    }

    public void setUpMove(boolean bool) {
        upMove = bool;
    }
    public void setDownMove(boolean bool) {
        downMove = bool;
    }

    private void updateVelocity () {
        //Friction
        if (leftMove == rightMove) {
            velocityX = applyFriction(velocityX);
        }
        if (upMove == downMove) {
            velocityY = applyFriction(velocityY);
        }

        if (leftMove && (velocityX > -MAX_PLAYER_VELOCITY)) {
            velocityX -= PLAYER_ACCELERATION;
        }
        if (rightMove && (velocityX < MAX_PLAYER_VELOCITY)) {
            velocityX += PLAYER_ACCELERATION;
        }
        if (upMove && (velocityY < MAX_PLAYER_VELOCITY)) {
            velocityY += PLAYER_ACCELERATION;
        }
        if (downMove && (velocityY > -MAX_PLAYER_VELOCITY)) {
            velocityY -= PLAYER_ACCELERATION;
        }
    }

    private float applyFriction (float axisVelocity){
        if (axisVelocity >= PLAYER_FRICTION){
            axisVelocity -= PLAYER_FRICTION;
        } else if (axisVelocity <= -PLAYER_FRICTION) {
            axisVelocity += PLAYER_FRICTION;
        } else {
            axisVelocity = 0;
        }
        return axisVelocity;
    }

    public void move() {
        updateVelocity();
        x += velocityX * Gdx.graphics.getDeltaTime();
        y += velocityY * Gdx.graphics.getDeltaTime();
        bounds();
    }

    private void bounds (){
        int playerPosWidth = Constants.WINDOW_WIDTH-Constants.PLAYER_WIDTH;
        int playerPosHeight = Constants.WINDOW_HEIGHT-Constants.PLAYER_HEIGHT;
        if (x < 0) { x = 0; }
        if (y < 0) { y = 0; }
        if (x > playerPosWidth) { x = playerPosWidth; }
        if (y > playerPosHeight) { y = playerPosHeight; }
    }
}
