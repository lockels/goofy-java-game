package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.model.GameLogic;

public class GameOverScreen extends ScreenAdapter {
    
    GameRenderer game;
    GameLogic gameLogic;
    SpriteBatch batch;
    OrthographicCamera cam;

    public GameOverScreen(GameRenderer game, GameLogic gameLogic, SpriteBatch batch, OrthographicCamera cam) {
        this.game = game;
        this.gameLogic = gameLogic;
        System.out.println("Game: Over: " + gameLogic.getGameState());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {

                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new GameTitleScreen(game, gameLogic, batch, cam));
                }

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "You win!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Press enter to restart.", Gdx.graphics.getWidth() * .25f,
                Gdx.graphics.getHeight() * .25f);
        game.batch.end();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
