package inf112.skeleton.app;

import inf112.skeleton.app.entities.Entity;
import inf112.skeleton.app.entities.Enemy;
import inf112.skeleton.app.entities.Player;
import inf112.skeleton.app.grid.Grid;
import inf112.skeleton.app.myInput.MyInputAdapter;
import inf112.skeleton.app.HUD.HUD;

import static inf112.skeleton.app.Constants.*;

import java.util.ArrayList;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
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
import com.badlogic.gdx.utils.TimeUtils;

public class GameRenderer extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<TextureRegion> entitySprites = new ArrayList<>();
    private Texture spriteSheet;
    private MyInputAdapter inputAdapter;
    private BitmapFont font;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Grid grid;
    private HUD hud;
    private int playerHealth = PLAYER_HEALTH;
    private long lastHitTime;
    private final long hitCooldown = HIT_COOLDOWN;
    private GameStates gameState;
    private int hitWarningDuration = HIT_WARNING_DURATION;

    // Preloading files:
    private String dungeon_sheet = DUNGEON_SHEET_IMG;

    public GameRenderer(GameStates gameState) {
        this.gameState = gameState;
        player = new Player(
                new Rectangle(400, 20, PLAYER_WIDTH, PLAYER_HEIGHT),
                dungeon_sheet, PLAYER_SPRITESHEET_X, PLAYER_SPRITESHEET_Y, PLAYER_SPRITESHEET_WIDTH,
                PLAYER_SPRITESHEET_HEIGHT);
        Enemy enemy1 = new Enemy(
                new Rectangle(400, 400, ENEMY_WIDTH, ENEMY_HEIGHT),
                dungeon_sheet, ENEMY_SPRITESHEET_X, ENEMY_SPRITESHEET_Y, ENEMY_SPRITESHEET_WIDTH,
                ENEMY_SPRITESHEET_HEIGHT);
        Enemy enemy2 = new Enemy(
                new Rectangle(300, 500, ENEMY_WIDTH, ENEMY_HEIGHT),
                dungeon_sheet, ENEMY_SPRITESHEET_X, ENEMY_SPRITESHEET_Y, ENEMY_SPRITESHEET_WIDTH,
                ENEMY_SPRITESHEET_HEIGHT);
        Enemy enemy3 = new Enemy(
                new Rectangle(500, 500, ENEMY_WIDTH, ENEMY_HEIGHT),
                dungeon_sheet, ENEMY_SPRITESHEET_X, ENEMY_SPRITESHEET_Y, ENEMY_SPRITESHEET_WIDTH,
                ENEMY_SPRITESHEET_HEIGHT);
        entities.add(player);
        entities.add(enemy1);
        entities.add(enemy2);
        entities.add(enemy3);
        inputAdapter = new MyInputAdapter(player);
    }

    @Override
    public void create() {
        // Batch
        batch = new SpriteBatch();

        // Camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Sprites
        spriteSheet = getSpriteSheet(player.getSpriteSheetPath());
        for (Entity entity : entities) {
            entitySprites.add(getSpriteFromSheet(spriteSheet, entity.getSpriteSheetX(), entity.getSpriteSheetY(),
                    entity.getSpriteWidth(), entity.getSpriteHeight()));
        }

        // Grid
        grid = new Grid(NUM_ROWS, NUM_COLS, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Font
        font = new BitmapFont();

        // Input
        Gdx.input.setInputProcessor(inputAdapter);

        // Load the TiledMap
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load(MAP_IMG);
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        // Initialize HUD
        Texture heartTexture = new Texture(HEART_IMG);
        hud = new HUD(heartTexture, playerHealth);
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw map
        mapRenderer.setView(cam);
        mapRenderer.render();

        // Update player position
        player.move();

        // Begin batch rendering
        batch.begin();

        // Check collision with enemies if hit cooldown has elapsed
        long currentTime = TimeUtils.millis();

        if (currentTime - lastHitTime < hitWarningDuration) {
            batch.setColor(1, 0, 0, 0.9f);
            drawHitWarning();
            batch.setColor(1, 1, 1, 1);
        }

        if (currentTime - lastHitTime > hitCooldown) {
            for (int i = 1; i < entities.size(); i++) {
                Entity entity = entities.get(i);
                if (player.getHitbox().overlaps(entity.getHitbox()) && playerHealth > 0) {
                    // Collision detected, remove one heart
                    playerHealth = playerHealth - HIT_DAMAGE;
                    lastHitTime = currentTime;
                }
            }
        }

        // Update entities' positions
        for (Entity entity : entities.subList(1, entities.size())) {
            entity.moveTowards(player.getX(), player.getY());
        }

        // Update HUD hearts
        hud.updateHearts(playerHealth);

        // Draw grid, entities, and HUD
        batch.setProjectionMatrix(cam.combined);
        grid.draw(batch);
        for (Entity entity : entities) {
            TextureRegion entitySprite = entitySprites.get(entities.indexOf(entity));
            batch.draw(entitySprite, entity.getX(), entity.getY(), PLAYER_WIDTH, PLAYER_HEIGHT);
        }

        // Debug
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        font.draw(batch, activePlayerDirections(), 10, 40);

        if (playerHealth <= 0) {
            // Draw a rectangle with a picture
            Rectangle rectangle = new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            batch.draw(new Texture(GAME_OVER_IMG), rectangle.x, rectangle.y, rectangle.width,
                    rectangle.height);
            gameState = GameStates.GAME_OVER;
        }

        // Render HUD
        hud.draw(batch);

        // End batch rendering
        batch.end();
    }

    private void drawHitWarning() {
        batch.draw(new Texture(HIT_WARNING_IMG), 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private String activePlayerDirections() {
        StringBuilder result = new StringBuilder();
        Map<Direction, Boolean> dict = player.getMovementDirections();
        for (Direction dir : player.getMovementDirections().keySet()) {
            if (dict.get(dir)) {
                result.append(dir.name()).append("/");
            }
        }
        return result.toString();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportHeight = height;
        cam.viewportWidth = width;
        cam.update();
    }

    private TextureRegion getSpriteFromSheet(Texture spriteSheet, int x, int y, int width, int height) {
        return new TextureRegion(spriteSheet, x, y, width, height);
    }

    private Texture getSpriteSheet(String spriteSheet) {
        return new Texture(Gdx.files.internal(spriteSheet));
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
        // grid.dispose();
    }
}
