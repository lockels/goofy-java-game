package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.app.myInput.MyInputAdapter;

public class ImprovedMovementTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Player player;
    private Texture playerSprite;
    private MyInputAdapter inputAdapter;

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
        playerSprite = new Texture(Gdx.files.internal(player.spriteStr));

        //Input
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void render () {
        ScreenUtils.clear(0, 1, 0, 1);
        updateMove();
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(playerSprite, player.x, player.y, 64, 64);
        batch.end();
    }

    private void updateMove () {
        player.move();
    }
}
