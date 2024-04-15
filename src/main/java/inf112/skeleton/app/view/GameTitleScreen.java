package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.model.GameLogic;

public class GameTitleScreen extends ScreenAdapter{
    
    GameRenderer game;
    GameLogic gameLogic;
    SpriteBatch batch;

    public GameTitleScreen(GameRenderer game, GameLogic gameLogic, SpriteBatch batch) {
        this.game = game;
        this.gameLogic = gameLogic;
        System.out.println("Game: Title: " + gameLogic.getGameState());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameActiveScreen(game, gameLogic, batch));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "Title Screen!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Click the circle to win.", Gdx.graphics.getWidth() * .25f,
                Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Press space to play.", Gdx.graphics.getWidth() * .25f,
                Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
