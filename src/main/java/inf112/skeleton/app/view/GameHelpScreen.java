package inf112.skeleton.app.view;

import static inf112.skeleton.app.utils.Constants.EXIT_BUTTON;
import static inf112.skeleton.app.utils.Constants.HELP_SCREEN_BACKGROUND;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.model.GameLogic;

/**
 * The GameHelpScreen class is responsible for rendering the help screen.
 * Extends {@link ScreenAdapter}.
 */
public class GameHelpScreen extends ScreenAdapter {
    private GameRenderer game;
    private GameLogic gameLogic;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Stage stage;
    private Viewport viewport;
    private Button backButton;
    private Viewport viewPort;

    /**
     * Constructs a GameHelpScreen.
     *
     * @param game      The game renderer instance.
     * @param gameLogic The game logic instance.
     */
    public GameHelpScreen(GameRenderer game, GameLogic gameLogic) {
        this.game = game;
        this.gameLogic = gameLogic;
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera();
        this.viewport = new ExtendViewport(800, 800, cam); 
        this.stage = new Stage(viewport, batch);

        this.viewPort = new ExtendViewport(800, 800, cam);
        this.stage = new Stage(viewPort, batch);

        setupUi();
    }

    private void setupUi() {
        // Background image
        Texture backgroundTexture = new Texture(HELP_SCREEN_BACKGROUND);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        // Back Button
        backButton = new Button(new TextureRegionDrawable(new Texture(EXIT_BUTTON)));
        backButton.setPosition(700, 700);
        stage.addActor(backButton);
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
        if (backButton.isPressed()) {
            game.setScreen(new GameTitleScreen(game, gameLogic));
        }
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

