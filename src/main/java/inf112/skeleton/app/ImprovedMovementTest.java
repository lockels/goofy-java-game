package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.app.myInput.MyInputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ImprovedMovementTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Player player;
    private Texture playerSprite;
    private MyInputAdapter inputAdapter;
    private BitmapFont font;

    public ImprovedMovementTest() {
        player = new Player();
        inputAdapter = new MyInputAdapter(player);
    }

    @Override
    public void create () {
        //Batch
        batch = new SpriteBatch();

        //Camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 800);

        //Player
        playerSprite = new Texture(Gdx.files.internal(player.getSprite()));

        //Font
        font = new BitmapFont();

        //Input
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void render () {
        ScreenUtils.clear(0, 1, 0, 1);
        tick();
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(playerSprite, player.getX(), player.getY(), 64, 64);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();
    }

    private void tick() {
        player.move();
    }
}
