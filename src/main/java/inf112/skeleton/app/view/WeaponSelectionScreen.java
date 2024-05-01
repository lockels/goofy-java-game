package inf112.skeleton.app.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import inf112.skeleton.app.model.GameLogic;

import static inf112.skeleton.app.utils.Constants.*;


// /**
//  * The WeaponSelectionScreen class represents the screen where players can select weapons.
//  * This class extends ScreenAdapter and is used to display and handle interactions with
//  * the weapon selection interface.
//  * 
//  * It handles rendering a static image that contains the weapon selection options,
//  * and it is meant to be displayed when a player activates the weapon selection functionality
//  * from the GameActiveScreen.
//  *
//  */
public class WeaponSelectionScreen extends ScreenAdapter implements ApplicationListener {
    private GameRenderer game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Stage stage; 
    private Texture backButtonTexture; // Texture for the back to game button
    private Button backButton; 

     /**
        * Constructs a new WeaponSelectionScreen which sets up the environment to display
        * the weapon selection options to the player.
        *
        * @param game The main game renderer, used for managing game screens and overall game state.
        */
    public WeaponSelectionScreen(GameRenderer game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.backgroundTexture = new Texture(WEAPON_SELECTION);
        this.backButtonTexture = new Texture(BACK_TO_GAME_BUTTON); 
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(backButtonTexture));
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonDrawable; 

        backButton = new Button(buttonStyle);
        backButton.setSize(200, 30);

        float xPosition = 45; 
        float yPosition = 30; 

        backButton.setPosition(xPosition, yPosition);
        stage.addActor(backButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    SpriteBatch batch = game.getBatch(); 
                    GameLogic gameLogic = game.getGameLogic();
                    OrthographicCamera cam = game.getCamera(); 

                    if (gameLogic != null && cam != null) {
                        game.setScreen(new GameActiveScreen(game, gameLogic, batch, cam));
                    } else {
                        System.out.println("Game logic or camera is null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta); 
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        batch.end();

        stage.draw(); 
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        backButtonTexture.dispose();
        stage.dispose();
    }

    @Override public void create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

}
