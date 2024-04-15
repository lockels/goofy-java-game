package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.controller.myInput.MyInputAdapter;
import inf112.skeleton.app.model.Constants;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.entities.Entity;
import inf112.skeleton.app.view.HUD.HUD;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import static inf112.skeleton.app.model.Constants.*;

/**
 * The GameRenderer class is responsible for rendering the game.
 * It manages the rendering of entities, HUD, and game UI elements.
 */
public class GameActiveScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private ArrayList<Sprite> entitySprites = new ArrayList<>();
    private Texture spriteSheet;
    private BitmapFont font;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private HUD hud;

    GameRenderer game;
    GameLogic gameLogic;
    // Camera cam;

    float circleX = 300;
    float circleY = 150;
    float circleRadius = 50;

    float xSpeed = 4;
    float ySpeed = 3;

    /**
     * Constructs a GameRenderer with the specified GameLogic.
     *
     * @param game the GameLogic instance to render
     */
    public GameActiveScreen(GameRenderer game, GameLogic gameLogic, SpriteBatch batch, OrthographicCamera cam) {
        this.game = game;
        this.gameLogic = gameLogic;
        this.batch = batch;
        this.cam = cam;
        System.out.println("Game: Active: " + gameLogic.getGameState());
        create();
    }

    ////////////////////////////////////////////////////////////////////////////////////

    // @Override
    // public void show() {
    //     Gdx.input.setInputProcessor(new InputAdapter() {
    //         @Override
    //         public boolean touchDown(int x, int y, int pointer, int button) {
    //             int renderY = Gdx.graphics.getHeight() - y;
    //             if (Vector2.dst(circleX, circleY, x, renderY) < circleRadius) {
    //                 game.setScreen(new GameOverScreen(game, gameLogic));
    //             }
    //             return true;
    //         }
    //     });
    // }

    // @Override
    // public void render(float delta) {
    //     circleX += xSpeed;
    //     circleY += ySpeed;

    //     if (circleX < 0 || circleX > Gdx.graphics.getWidth()) {
    //         xSpeed *= -1;
    //     }

    //     if (circleY < 0 || circleY > Gdx.graphics.getHeight()) {
    //         ySpeed *= -1;
    //     }

    //     Gdx.gl.glClearColor(0, 0, .25f, 1);
    //     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    //     game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    //     game.shapeRenderer.setColor(0, 1, 0, 1);
    //     game.shapeRenderer.circle(circleX, circleY, 75);
    //     game.shapeRenderer.end();
    // }

    // @Override
    // public void hide() {
    //     Gdx.input.setInputProcessor(null);
    // }

    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void show() {
        System.out.println("GameState: Active: " + gameLogic.getGameState());
        batch = new SpriteBatch();
    }

    public void create() {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
        spriteSheet = getSpriteSheet(DUNGEON_SHEET_IMG);
        for (Entity entity : gameLogic.getEntities()) {
            TextureRegion spriteTextureRegion = (getSpriteFromSheet(getSpriteSheet((entity.getSpriteSheetPath())),
                    entity.getSpriteSheetX(), entity.getSpriteSheetY(),
                    entity.getSpriteWidth(), entity.getSpriteHeight()));
            Sprite entitySprite = new Sprite(spriteTextureRegion);
            entitySprites.add(entitySprite);
        }
        System.out.println("Gamestate: " + gameLogic.getGameState());
        font = new BitmapFont();
        Gdx.input.setInputProcessor(new MyInputAdapter(gameLogic.getPlayer()));
        map = new TmxMapLoader().load(MAP_IMG);
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        Texture heartTexture = new Texture(HEART_IMG);
        hud = new HUD(heartTexture, gameLogic.getPlayer().getHealth());
    }

    @Override
    public void render(float delta) {
        clearScreen();
        updateCamera();
        mapRenderer.setView(cam);
        mapRenderer.render();
        gameLogic.update();
        if (gameLogic.getGameState() == GameState.GAME_OVER) {
            game.setScreen(new GameOverScreen(game, gameLogic, batch, cam));
            gameLogic.getPlayer().setHealth(Constants.PLAYER_HEALTH);
            gameLogic.setGameState(GameState.GAME_ACTIVE);
        }

        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        drawEntities();
        System.out.println();
        drawHUD();
        drawGameUI();
        batch.end();
    }

    @Override
    public void hide() {
    Gdx.input.setInputProcessor(null);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private float getCenterX(Entity entity) {
        return entity.getX() + entity.getSpriteWidth() / 2;
    }

    private float getCenterY(Entity entity) {
        return entity.getY() + entity.getSpriteHeight() / 2;
    }

    private void updateCamera() {
        cam.position.set(getCenterX(gameLogic.getPlayer()), getCenterY(gameLogic.getPlayer()), 0);
        cam.update();
        float zoomLevel = 0.7f;
        cam.zoom = zoomLevel;
    }

    private void drawEntities() {
        for (Entity entity : gameLogic.getEntities()) {
            updateEntitySprite(entity);
            Sprite entitySprite = entitySprites.get(gameLogic.getEntities().indexOf(entity));
            entitySprite.draw(batch);
        }
    }

    private void updateEntitySprite(Entity entity) {
        Sprite entitySprite = entitySprites.get(gameLogic.getEntities().indexOf(entity));
        entitySprite.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
        entitySprite.setPosition(entity.getX(), entity.getY());
        entitySprite.setRotation(entity.getAngle());
    }

    private void drawHUD() {
        hud.updateHearts(gameLogic.getPlayer().getHealth());
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
}
