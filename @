package inf112.skeleton.app.view;

import inf112.skeleton.app.controller.myInput.MyInputAdapter;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.utils.TiledObjectUtil;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import static inf112.skeleton.app.utils.Constants.*;
import static inf112.skeleton.app.model.GameState.*;

/**
 * The GameRenderer class is responsible for rendering the game.
 * It manages the rendering of entities, HUD, and game UI elements.
 */
public class GameRenderer extends Game {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    GameLogic gameLogic;
    private TiledMap map;
    OrthographicCamera cam;
    
    private MyInputAdapter inputAdapter;

    public GameRenderer(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void create() {
        System.out.println("GameRenderer: Created");
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        map = new TmxMapLoader().load(MAP_IMG);
        parseObjectLayers();

        gameLogic.setMap(map);
        gameLogic.setGameState(GameState.GAME_TITLE);
        setScreen(new GameTitleScreen(this, gameLogic));

        // Create and set the input adapter
        inputAdapter = new MyInputAdapter(gameLogic.getPlayer(), gameLogic);
        Gdx.input.setInputProcessor(inputAdapter);

        cam = new OrthographicCamera(); 
        cam.setToOrtho(false, 800, 800); 

        setScreen(new GameTitleScreen(this, gameLogic)); 
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }

    private void parseObjectLayers() {
        TiledObjectUtil.parseTiledObjectLayer(gameLogic.world,
                map.getLayers().get("collision-layer").getObjects());
    }
}
