package inf112.skeleton.app.view;

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

public class GameHelpScreen extends ScreenAdapter {
    GameRenderer game;
    GameLogic gameLogic;
    SpriteBatch batch;
    OrthographicCamera cam;
    Stage stage;
    Viewport viewport;
    Button backButton;

    Viewport viewPort;

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
        Texture backgroundTexture = new Texture("new_menu.png");
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        //Back Button
        backButton = new Button(new TextureRegionDrawable(new Texture("exit.png")));
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
        if(backButton.isPressed()){
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

