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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;

public class GameTitleScreen extends ScreenAdapter {
    GameRenderer game;
    GameLogic gameLogic;
    SpriteBatch batch;
    OrthographicCamera cam;
    Stage stage;
    Viewport viewport;
    Button startButton, noButton;

    public GameTitleScreen(GameRenderer game, GameLogic gameLogic) {
        this.game = game;
        this.gameLogic = gameLogic;
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera();
        this.viewport = new ExtendViewport(800, 800, cam); // Adjust viewport size as needed
        this.stage = new Stage(viewport, batch);

        setupUi();
    }

    private void setupUi() {
        // Background image
        Texture backgroundTexture = new Texture("new_menu.png"); // Ensure you have this asset
        Button backgroundImage = new Button(new TextureRegionDrawable(new Texture("new_menu.png")));
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        // Start button
        startButton = new Button(new TextureRegionDrawable(new Texture("Yes_Button.png")));
        startButton.setPosition(300, 200);
        stage.addActor(startButton);

        // No button (add functionality if needed)
        noButton = new Button(new TextureRegionDrawable(new Texture("No_Button.png")));
        noButton.setPosition(100, 100);
        // Add listener to noButton if required
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
        //System.out.println("heo");


        // Check if the start button is pressed
        if (startButton.isPressed()) {
            gameLogic.setGameState(GameState.GAME_ACTIVE);  // Ensure GameRenderer has public access to batch and cam
            game.setScreen(new GameActiveScreen(game, gameLogic, batch, cam));
            System.out.println("Gamestate changed " + gameLogic.getGameState());
        }
        if (noButton.isPressed()){
            System.out.println("nei");
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
