package inf112.skeleton.app.view;

import inf112.skeleton.app.model.GameLogic;

import com.badlogic.gdx.Game;
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

    public GameRenderer(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        setScreen(new GameTitleScreen(this, gameLogic, batch));
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }

    
}
