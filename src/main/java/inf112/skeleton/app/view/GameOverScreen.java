package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.model.GameLogic;
import static inf112.skeleton.app.model.GameState.*;

public class GameOverScreen extends ScreenAdapter {
    
    GameRenderer game;
    GameLogic gameLogic;
    SpriteBatch batch;
    OrthographicCamera cam;

    public GameOverScreen(GameRenderer game, GameLogic gameLogic, SpriteBatch batch, OrthographicCamera cam) {
        this.game = game;
        this.gameLogic = gameLogic;
        this.batch = batch;
        System.out.println("GameOverScreen: " + gameLogic.getGameState());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        System.out.println("GameState: " + gameLogic.getGameState());
        if (gameLogic.getGameState() == GAME_TITLE) {
            game.setScreen(new GameTitleScreen(game, gameLogic, batch, cam));
        }

        Gdx.gl.glClearColor(.25f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        game.font.draw(batch, "You Lost!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(batch, "Press enter to restart.", Gdx.graphics.getWidth() * .25f,
                Gdx.graphics.getHeight() * .25f);
        batch.end();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
