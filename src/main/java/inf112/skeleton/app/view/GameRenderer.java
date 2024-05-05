package inf112.skeleton.app.view;

import inf112.skeleton.app.controller.myInput.MyInputAdapter;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.utils.B2DPhysics.TiledObjectUtil;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import static inf112.skeleton.app.utils.Constants.*;

/**
 * The GameRenderer class is responsible for rendering the game.
 * Extends {@link Game}.
 * This class manages the rendering of entities, HUD, and game UI elements.
 */
public class GameRenderer extends Game {
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    GameLogic gameLogic;
    private TiledMap map;
    OrthographicCamera cam;
    private MyInputAdapter inputAdapter;

    /**
     * Constructs a GameRenderer with the specified game logic.
     *
     * @param gameLogic The GameLogic instance to render.
     */
    public GameRenderer(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    /**
     * Gets the camera used to view the game world.
     *
     * @return The camera.
     */
    public OrthographicCamera getCamera() {
        return cam;
    }

    /**
     * Gets the game logic controlling the game's state and flow.
     *
     * @return The game logic.
     */
    public GameLogic getGameLogic() {
        return gameLogic;
    }

    /**
     * Gets the sprite batch used for rendering.
     *
     * @return The sprite batch.
     */
    public SpriteBatch getBatch() {
        return batch;
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
        TiledObjectUtil.createSpikes(gameLogic.world,
                map.getLayers().get("object-damage-layer").getObjects());
    }
}
