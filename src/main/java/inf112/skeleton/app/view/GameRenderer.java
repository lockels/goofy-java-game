package inf112.skeleton.app.view;

import inf112.skeleton.app.controller.myInput.MyInputAdapter;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * The GameRenderer class is responsible for rendering the game.
 * It manages the rendering of entities, HUD, and game UI elements.
 */
public class GameRenderer extends Game {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    GameLogic gameLogic;
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
        gameLogic.setGameState(GameState.GAME_TITLE);
        setScreen(new GameTitleScreen(this, gameLogic, batch, cam));

        // Create and set the input adapter
        inputAdapter = new MyInputAdapter(gameLogic.getPlayer(), gameLogic);
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
