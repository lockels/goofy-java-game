package inf112.skeleton.app;

import inf112.skeleton.app.entities.Entity;
import inf112.skeleton.app.grid.Grid;
import inf112.skeleton.app.myInput.MyInputAdapter;
import inf112.skeleton.app.HUD.HUD;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera; 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

import static inf112.skeleton.app.Constants.*;

/**
 * GameRenderer is the main class for rendering the game.
 */
public class GameRenderer extends Game {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private GameLogic gameLogic;
    private ArrayList<TextureRegion> entitySprites = new ArrayList<>();
    private Texture spriteSheet;
    private BitmapFont font;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private HUD hud;

    public GameRenderer(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
        spriteSheet = getSpriteSheet(DUNGEON_SHEET_IMG);
        for (Entity entity : gameLogic.getEntities()) {
            entitySprites.add(getSpriteFromSheet(spriteSheet, entity.getSpriteSheetX(), entity.getSpriteSheetY(),
                                                 entity.getSpriteWidth(), entity.getSpriteHeight()));
        }
        font = new BitmapFont();
        Gdx.input.setInputProcessor(new MyInputAdapter(gameLogic.getPlayer()));
        map = new TmxMapLoader().load(MAP_IMG);
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        Texture heartTexture = new Texture(HEART_IMG);
        hud = new HUD(heartTexture, gameLogic.getPlayer().getHealth());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.setView(cam);
        mapRenderer.render();
        gameLogic.update();
        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        drawEntities();
        drawHUD();
        if (gameLogic.isShowHitWarning()) {
            drawHitWarning();
        }
        if (gameLogic.getGameState() == GameState.GAME_OVER) {
            drawGameOver();
        }
        batch.end();
    }

    private void drawEntities() {
        for (Entity entity : gameLogic.getEntities()) {
            TextureRegion entitySprite = entitySprites.get(gameLogic.getEntities().indexOf(entity));
            batch.draw(entitySprite, entity.getX(), entity.getY(), PLAYER_WIDTH, PLAYER_HEIGHT);
        }
    }

    private void drawHUD() {
        hud.updateHearts(gameLogic.getPlayer().getHealth());
        hud.draw(batch);
    }

    private void drawGameOver() {
        // Draw a rectangle with a picture
        Rectangle rectangle = new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        batch.draw(new Texture(GAME_OVER_IMG), rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private void drawHitWarning() {
        batch.setColor(1, 0, 0, 0.9f);
        batch.draw(new Texture(HIT_WARNING_IMG), 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        batch.setColor(1, 1, 1, 1);
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
        for (TextureRegion textureRegion : entitySprites) {
            textureRegion.getTexture().dispose();
        }
        font.dispose();
        map.dispose();
        mapRenderer.dispose();
    }

    private TextureRegion getSpriteFromSheet(Texture spriteSheet, int x, int y, int width, int height) {
        return new TextureRegion(spriteSheet, x, y, width, height);
    }

    private Texture getSpriteSheet(String spriteSheet) {
        return new Texture(Gdx.files.internal(spriteSheet));
    }
}
