package inf112.skeleton.app.view;

import static inf112.skeleton.app.utils.Constants.GAME_OVER_BACKGROUND;
import static inf112.skeleton.app.utils.Constants.NO_BUTTON;
import static inf112.skeleton.app.utils.Constants.YES_BUTTON;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;

public class GameOverScreen extends ScreenAdapter {
    GameRenderer game;
    GameLogic gameLogic;
    SpriteBatch batch;
    OrthographicCamera cam;
    Stage stage;
    Viewport viewport;
    Button yesButton, noButton;

    public GameOverScreen(GameRenderer game, GameLogic gameLogic) {
        this.game = game;
        this.gameLogic = gameLogic;
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera();
        this.viewport = new ExtendViewport(800, 800, cam);
        this.stage = new Stage(viewport, batch);

        setupUi();
    }

    private void setupUi() {
        Texture backgroundTexture = new Texture(GAME_OVER_BACKGROUND);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        yesButton = new Button(new TextureRegionDrawable(new Texture(YES_BUTTON)));
        yesButton.setPosition(100, 10);
        yesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameLogic.resetGame();
                gameLogic.setGameState(GameState.GAME_ACTIVE);
                game.setScreen(new GameActiveScreen(game, gameLogic, batch, cam));
            }
        });
        stage.addActor(yesButton);

        noButton = new Button(new TextureRegionDrawable(new Texture(NO_BUTTON)));
        noButton.setPosition(400, 10);
        stage.addActor(noButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}
