package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;


public class MovementTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Rectangle player;
    private Texture playerImg;
    private BitmapFont font;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;
    private float velocityX = 0;
    private float velocityY = 0;

    @Override
    public void create () {
        //Batch
        batch = new SpriteBatch();

        //Camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 800);

        //player
        player = new Rectangle(800/2 - 64/2, 20, 64, 64);
        playerImg = new Texture(Gdx.files.internal("playerSprite.png"));

        //Font
        font = new BitmapFont();
    }

    @Override
    public void render () {
        ScreenUtils.clear(0, 1, 0, 1);
        cam.update();
        getMovementInput();
        updateVelocity(moveLeft, moveRight, moveUp, moveDown);
        move(velocityX, velocityY);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        if (moveUp) font.draw(batch, "N", 20, 30);
        if (moveRight) font.draw(batch, "E", 30, 20);
        if (moveDown) font.draw(batch, "S", 20, 10);
        if (moveLeft) font.draw(batch, "W", 10, 20);
        batch.draw(playerImg, player.x, player.y, 64, 64);
        batch.end();
    }

    private void getMovementInput (){
        moveUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        moveDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        moveLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        moveRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    private void updateVelocity (boolean moveLeft, boolean moveRight, boolean moveUp, boolean moveDown) {
        float velocity = 100;
        float maxVelocity = 500;
        float friction = 25;
        //Friction
        if (moveLeft == moveRight) {
            velocityX = applyFriction(velocityX, friction);
        }
        if (moveUp == moveDown) {
            velocityY = applyFriction(velocityY, friction);
        }

        if (moveLeft && (velocityX > -maxVelocity)) {
            velocityX -= velocity;
        }
        if (moveRight && (velocityX < maxVelocity)) {
            velocityX += velocity;
        }
        if (moveUp && (velocityY < maxVelocity)) {
            velocityY += velocity;
        }
        if (moveDown && (velocityY > -maxVelocity)) {
            velocityY -= velocity;
        }
    }

    private float applyFriction (float axisVelocity, float friction){
        if (axisVelocity >= friction){
            axisVelocity -= friction;
        } else if (axisVelocity <= -friction) {
            axisVelocity += friction;
        } else {
            axisVelocity = 0;
        }
        return axisVelocity;
    }

    private void move (float velocityX, float velocityY){
        player.x += velocityX * Gdx.graphics.getDeltaTime();
        player.y += velocityY * Gdx.graphics.getDeltaTime();
        bounds();
    }

    private void bounds (){
        if (player.x < 0) player.x = 0;
        if (player.y < 0) player.y = 0;
        if (player.x > 800-64) player.x = 800-64;
        if (player.y > 800-64) player.y = 800-64;
    }

    @Override
    public void dispose () {
        batch.dispose();
        playerImg.dispose();
    }
}
