package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.controller.myInput.MyInputAdapter;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.entities.Entity;
import inf112.skeleton.app.model.entities.enemies.Enemy;
import inf112.skeleton.app.view.HUD.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static inf112.skeleton.app.utils.Constants.*;
import static inf112.skeleton.app.model.GameState.*;

/**
     * The GameRenderer class is responsible for rendering the game.
     * It manages the rendering of entities, HUD, and game UI elements.
     */
public class GameActiveScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private GameLogic gameLogic;
    private ArrayList<TextureRegion> entitySprites = new ArrayList<>();

    private Box2DDebugRenderer debugRenderer;

    private Texture spriteSheet;
    private BitmapFont font;
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;
    private HUD hud;
    private MyInputAdapter inputAdapter;
    // private Game game;
    private GameRenderer game;
    private Stage stage;
    private Button weaponSelectionButton;

    /**
         * Constructs a GameActiveScreen.
         *
         * @param gameLogic the GameLogic instance to render
         */
    public GameActiveScreen(GameRenderer game, GameLogic gameLogic, SpriteBatch batch, OrthographicCamera cam) {
        this.gameLogic = gameLogic;
        this.batch = batch;
        this.cam = cam;
        this.game = game;
    }

    @Override
    public void show() {
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        spriteSheet = getSpriteSheet(DUNGEON_SHEET);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);

        font = new BitmapFont();
        // Gdx.input.setInputProcessor(new MyInputAdapter(gameLogic.getPlayer()));

        map = new TmxMapLoader().load(MAP_IMG);
        tmr = new OrthogonalTiledMapRenderer(map);

        // parseObjectLayers();

        Texture heartTexture = new Texture(HEART_IMG);
        hud = new HUD(heartTexture, gameLogic.getPlayer().getHealth(), 0, 0);

        inputAdapter = new MyInputAdapter(gameLogic.getPlayer(), gameLogic);
        Gdx.input.setInputProcessor(inputAdapter);


        stage = new Stage();
        Gdx.input.setInputProcessor(stage); 

        Texture buttonTexture = new Texture(WEAPON_BUTTON);
        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonDrawable; // Set the button image

        weaponSelectionButton = new Button(buttonStyle);
        weaponSelectionButton.setSize(300, 50); 
        weaponSelectionButton.setPosition(500, Gdx.graphics.getHeight() - 750); 
        stage.addActor(weaponSelectionButton); 

        // Setup Input Multiplexer
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage); 
        multiplexer.addProcessor(inputAdapter); 
        Gdx.input.setInputProcessor(multiplexer); // Need this for processing both hearts and button

        // Adding the listener to the button
        weaponSelectionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new WeaponSelectionScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        if (gameLogic.getGameState() == GAME_OVER) {
            initiateGameOver();
        }

        clearScreen();
        updateCamera();
        gameLogic.update();

        // Map
        tmr.setView(cam);
        tmr.render();

        // Draws hitboxes
        debugRenderer.render(gameLogic.world, cam.combined);
        
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        
        // Rendering   
        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        drawEntities();
        drawHUD();
        drawGameUI();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        // spriteSheet.dispose();
        for (TextureRegion textureRegion : entitySprites) {
            textureRegion.getTexture().dispose();
        }
        font.dispose();
        map.dispose();
        tmr.dispose();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void initiateGameOver()   {
        // Set health to enemies to 0
        // for (Enemy enemy : gameLogic.) {
        //     enemy.setHealth(0);
        // }
        gameLogic.destroyInactiveEntities();
        game.setScreen(new GameOverScreen(game, gameLogic));
        gameLogic.getPlayer().setHealth(PLAYER_HEALTH);
    }

    private void updateCamera() {
        cam.position.set(gameLogic.getPlayer().getX() + PLAYER_WIDTH / 2,
            gameLogic.getPlayer().getY() + PLAYER_HEIGHT / 2, 0);
        cam.update();
        cam.zoom = 0.7f;
    }

    private void drawEntities() {
        for (Entity entity : gameLogic.getActiveEntities()) {
            if (entity.getIsDestroyed()) {
                System.out.println("Coin collected");
                gameLogic.removeEntity(entity);
            }
            float x = entity.getX();
            float y = entity.getY();
            float angle = entity.getAngle();
            float heightOffset = entity.getOffset().y;
            Vector2 offset = entity.trigVector(heightOffset,angle);
            Texture tex = new Texture("sprites/" + entity.getTextureId() + ".png");
            Sprite sprite = new Sprite(tex);
            sprite.setScale(entity.getSpriteWidth()/tex.getWidth(), entity.getSpriteHeight()/tex.getHeight());
            sprite.setAlpha(entity.getOpacity());
            sprite.setRotation(angle);
            sprite.setX((x + offset.x * PPM) - sprite.getWidth() / 2);
            sprite.setY((y + offset.y * PPM) - sprite.getHeight() / 2);
            sprite.draw(batch);
        }
    }

    private void drawHUD() {
        font.draw(batch, "Coins: " + gameLogic.getCoinValue(), getCameraX() + CAMERA_OFFSET_X - COIN_PADDING - 200, getCameraY() + CAMERA_OFFSET_Y - COIN_PADDING);
        hud.updateHearts(gameLogic.getPlayer().getHealth(), getCameraX(), getCameraY());
        hud.draw(batch);
    }

    private void drawGameUI() {
        if (gameLogic.isShowHitWarning()) {
            drawHitWarning();
        }
    }

    private void drawHitWarning() {
        batch.setColor(1, 0, 0, 0.9f);
        batch.draw(new Texture(HIT_WARNING), getCameraX() - CAMERA_OFFSET_X, getCameraY() - CAMERA_OFFSET_Y, CAMERA_WINDOW_WIDTH, CAMERA_WINDOW_HEIGHT);
        batch.setColor(1, 1, 1, 1);
    }

    private TextureRegion getSpriteFromSheet(Texture spriteSheet, int x, int y, int width, int height) {
        return new TextureRegion(spriteSheet, x, y, width, height);
    }

    private Texture getSpriteSheet(String spriteSheet) {
        return new Texture(Gdx.files.internal(spriteSheet));
    }

    public float getCameraX() {
        return this.cam.position.x;
    }

    public float getCameraY() {
        return this.cam.position.y;
    }
}
