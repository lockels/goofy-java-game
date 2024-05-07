package inf112.skeleton.app.view;

import static inf112.skeleton.app.utils.Constants.BACK_TO_GAME_BUTTON;
import static inf112.skeleton.app.utils.Constants.DIAMOND_SWORD_BUTTON;
import static inf112.skeleton.app.utils.Constants.METAL_SWORD_BUTTON;
import static inf112.skeleton.app.utils.Constants.DIAMOND_SWORD_COST;
import static inf112.skeleton.app.utils.Constants.HELP_BUTTON;
import static inf112.skeleton.app.utils.Constants.MENU;
import static inf112.skeleton.app.utils.Constants.METAL_SWORD_BUTTON;
import static inf112.skeleton.app.utils.Constants.METAL_SWORD_COST;
import static inf112.skeleton.app.utils.Constants.PLAY_BUTTON;
import static inf112.skeleton.app.utils.Constants.QUIT_BUTTON;
import static inf112.skeleton.app.utils.Constants.TREE_SWORD_BUTTON;
import static inf112.skeleton.app.utils.Constants.WEAPON_SELECTION;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.entities.weapons.DiamondSword;
import inf112.skeleton.app.model.entities.weapons.MetalSword;
import inf112.skeleton.app.model.entities.weapons.TreeSword;

/**
 * The GameTitleScreen class is responsible for rendering the title screen.
 * Extends {@link ScreenAdapter}.
 */
public class WeaponSelection extends ScreenAdapter{
    private GameRenderer game;
    private GameLogic gameLogic;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private Stage stage; 
    private Button backButton; 
    private Button treeButton, diamondSwordButton, metalSwordButton;
    private Viewport viewport;


    /**
     * Constructs a WeaponSelection.
     *
     * @param game      The game renderer instance.
     * @param gameLogic The game logic instance.
     */
    public WeaponSelection(GameRenderer game, GameLogic gameLogic) {
        this.game = game;
        this.gameLogic = gameLogic;
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera();
        this.viewport = new ExtendViewport(800, 800, cam); 
        this.stage = new Stage(viewport, batch);

        this.viewport = new ExtendViewport(800, 800, cam);
        this.stage = new Stage(viewport, batch);
    
        setupUi();
    }

    private void setupUi() {
        // Background
        Texture backgroundTexture = new Texture(WEAPON_SELECTION);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        // Buttons
        backButton = new Button(new TextureRegionDrawable(new Texture(BACK_TO_GAME_BUTTON)));
        backButton.setPosition(50, 20);
        stage.addActor(backButton);

        treeButton = new Button(new TextureRegionDrawable(new Texture(TREE_SWORD_BUTTON)));
        treeButton.setPosition(110, 335);
        stage.addActor(treeButton);

        metalSwordButton = new Button(new TextureRegionDrawable(new Texture(METAL_SWORD_BUTTON)));
        metalSwordButton.setPosition(430, 335);
        stage.addActor(metalSwordButton);

        diamondSwordButton = new Button(new TextureRegionDrawable(new Texture(DIAMOND_SWORD_BUTTON)));
        diamondSwordButton.setPosition(270, 60);
        stage.addActor(diamondSwordButton);
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        if (backButton.isPressed()) {
            gameLogic.setGameState(GameState.GAME_ACTIVE);
            game.setScreen(new GameActiveScreen(game, gameLogic, game.batch, game.cam)); 
        }
        else if (treeButton.isPressed()){
            gameLogic.setGameState(GameState.GAME_ACTIVE);
            game.setScreen(new GameActiveScreen(game, gameLogic, game.batch, game.cam)); 
            gameLogic.getWeapon().setIsDestroyed(true);
            gameLogic.setWeapon(new TreeSword(gameLogic.getWorld()));
        }
        else if(metalSwordButton.isPressed()){
            System.out.printf("num coins: %d", gameLogic.getCoinValue());
            if (gameLogic.getCoinValue() >= METAL_SWORD_COST) {
                gameLogic.setGameState(GameState.GAME_ACTIVE);
                game.setScreen(new GameActiveScreen(game, gameLogic, game.batch, game.cam));
                gameLogic.getWeapon().setIsDestroyed(true);
                gameLogic.setCoinValue(gameLogic.getCoinValue() - METAL_SWORD_COST);
                gameLogic.setWeapon(new MetalSword(gameLogic.getWorld()));
            }
        }
        else if(diamondSwordButton.isPressed()){
            System.out.printf("num coins: %d", gameLogic.getCoinValue());
            if (gameLogic.getCoinValue() >= DIAMOND_SWORD_COST) {
                gameLogic.setGameState(GameState.GAME_ACTIVE);
                game.setScreen(new GameActiveScreen(game, gameLogic, game.batch, game.cam));
                gameLogic.getWeapon().setIsDestroyed(true);
                gameLogic.setCoinValue(gameLogic.getCoinValue() - DIAMOND_SWORD_COST);
                gameLogic.setWeapon(new DiamondSword(gameLogic.getWorld()));
            }
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
