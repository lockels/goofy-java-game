package inf112.skeleton.app.view;

import inf112.skeleton.app.controller.myInput.MyInputAdapter;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.entities.Entity;
import inf112.skeleton.app.utils.TiledObjectUtil;
//import inf112.skeleton.app.utils.TiledObjectUtil;
import inf112.skeleton.app.view.HUD.HUD;

import com.badlogic.gdx.Game;
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

import static inf112.skeleton.app.utils.Constants.*;

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

    /**
         * Constructs a GameRenderer with the specified GameLogic.
         *
         * @param gameLogic the GameLogic instance to render
         */
    public GameActiveScreen(Game game, GameLogic gameLogic, SpriteBatch batch, OrthographicCamera cam) {
        this.gameLogic = gameLogic;
        this.batch = batch;
        this.cam = cam;
        create();
    }

    public void create() {
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        spriteSheet = getSpriteSheet(DUNGEON_SHEET_IMG); 

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);

        font = new BitmapFont();
        Gdx.input.setInputProcessor(new MyInputAdapter(gameLogic.getPlayer()));

        map = new TmxMapLoader().load(MAP_IMG);
        tmr = new OrthogonalTiledMapRenderer(map);

        TiledObjectUtil.parseTiledObjectLayer(gameLogic.world, 
            map.getLayers().get("collision-layer").getObjects());

        Texture heartTexture = new Texture(HEART_IMG);
        hud = new HUD(heartTexture, gameLogic.getPlayer().getHealth(), 0, 0);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        updateCamera();
        gameLogic.update();

        // Map
        tmr.setView(cam);
        tmr.render();

        debugRenderer.render(gameLogic.world, cam.combined);

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

    private void updateCamera() {
        cam.position.set(gameLogic.getPlayer().getX() + PLAYER_WIDTH / 2,
            gameLogic.getPlayer().getY() + PLAYER_HEIGHT / 2, 0);
        cam.update();
        float zoomLevel = 0.7f;
        cam.zoom = zoomLevel;
    }

    private void drawEntities() {
        for (Entity entity : gameLogic.getEntities()) {
            String textureID = entity.getTextureId() + ".png";
            float xPos = entity.getX();
            float yPos = entity.getY();
            Texture tex = new Texture(textureID);
            batch.draw(tex, xPos * PPM - (tex.getWidth() / 2), yPos * PPM - (tex.getHeight() / 2));
        }
    }

    private void drawHUD() {
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
        batch.draw(new Texture(HIT_WARNING_IMG), 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
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