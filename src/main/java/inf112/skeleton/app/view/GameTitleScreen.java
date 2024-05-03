package inf112.skeleton.app.view;

import static inf112.skeleton.app.utils.Constants.HELP_BUTTON;
import static inf112.skeleton.app.utils.Constants.MENU;
import static inf112.skeleton.app.utils.Constants.PLAY_BUTTON;
import static inf112.skeleton.app.utils.Constants.QUIT_BUTTON;

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
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.controller.myInput.SoundController;

public class GameTitleScreen extends ScreenAdapter {
    private GameRenderer game;
    private GameLogic gameLogic;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Stage stage;
    private Viewport viewport;
    private Button playButton, quitButton, helpButton;
    
    Viewport viewPort;

    public GameTitleScreen(GameRenderer game, GameLogic gameLogic) {
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
        Texture backgroundTexture = new Texture(MENU);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        // Play Button
        playButton = new Button(new TextureRegionDrawable(new Texture(PLAY_BUTTON)));
        playButton.setPosition(viewPort.getWorldWidth() / 4, 200);
        stage.addActor(playButton);

        // Quit Button
        quitButton = new Button(new TextureRegionDrawable(new Texture(QUIT_BUTTON)));
        quitButton.setPosition(300, 100); // Next to the play button
        stage.addActor(quitButton);

        //Help Button
        helpButton = new Button(new TextureRegionDrawable(new Texture(HELP_BUTTON)));
        helpButton.setPosition(700, 700);
        stage.addActor(helpButton);

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
        if (playButton.isPressed()) {
            gameLogic.setGameState(GameState.GAME_ACTIVE);
            game.setScreen(new GameActiveScreen(game, gameLogic, game.batch, game.cam)); 
        }
        if (quitButton.isPressed()){
            Gdx.app.exit();
        }
        if(helpButton.isPressed()){
            game.setScreen(new GameHelpScreen(game, gameLogic)); 

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
