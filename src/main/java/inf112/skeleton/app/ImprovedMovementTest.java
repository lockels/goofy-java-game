package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.app.myInput.MyInputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.Map;

public class ImprovedMovementTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Player player;
    private Texture playerSprite;
    private MyInputAdapter inputAdapter;
    private BitmapFont font;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

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

        show();
    }

    @Override
    public void render () {
        ScreenUtils.clear(0, 1, 0, 1);
        tick();
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        mapRenderer.setView(cam);
        mapRenderer.render();
        batch.draw(playerSprite, player.getX(), player.getY(), 64, 64);
        //Debug
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        font.draw(batch, activePlayerDirections(), 10, 40);
        
        batch.end();
    
    }

    private String activePlayerDirections () {
        StringBuilder result = new StringBuilder();
        Map<Direction, Boolean> dict = player.getMovementDirections();
        for (Direction dir : player.getMovementDirections().keySet()){
            if (dict.get(dir)){ result.append(dir.name()).append("/"); }
        }
        return result.toString();
    }

    private void tick() {
        player.move();
    }

    public void resize(int height, int width){
        cam.viewportHeight = height;
        cam.viewportWidth = width;
        cam.update();
    }

    private void show(){
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/map1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    public void dispose(){
        map.dispose();
        mapRenderer.dispose();
    }

}
