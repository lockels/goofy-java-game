package inf112.skeleton.app;

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

import inf112.skeleton.app.entities.Entity;
import inf112.skeleton.app.entities.Enemy;
import inf112.skeleton.app.entities.Player;
import inf112.skeleton.app.grid.Grid;
import inf112.skeleton.app.myInput.MyInputAdapter;
import inf112.skeleton.app.Constants;
import inf112.skeleton.app.HUD.HUD;

import java.util.ArrayList;
import java.util.Map;

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
    private int playerHealth = 10;
    private long lastHitTime;
    private final long hitCooldown = 1000; // 1 second in milliseconds

    // Preloading files:
    private String dungeon_sheet = "dungeon_sheet.png";

    public GameRenderer() {
        player = new Player(
                new Rectangle(400, 20, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT),
                dungeon_sheet, 306, 112, 16, 12);
        Enemy enemy1 = new Enemy(
                new Rectangle(400, 400, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT),
                dungeon_sheet, 322, 112, 16, 12);
        Enemy enemy2 = new Enemy(
                new Rectangle(300, 500, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT),
                dungeon_sheet, 322, 112, 16, 12);
        Enemy enemy3 = new Enemy(
                new Rectangle(500, 500, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT),
                dungeon_sheet, 322, 112, 16, 12);
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
        cam.setToOrtho(false, 800, 800);

        // Sprites
        spriteSheet = getSpriteSheet(player.getSpriteSheetPath());
        for (Entity entity : entities) {
            entitySprites.add(getSpriteFromSheet(spriteSheet, entity.getSpriteSheetX(), entity.getSpriteSheetY(),
                    entity.getSpriteWidth(), entity.getSpriteHeight()));
        }

        // Grid
        grid = new Grid(10, 10, 800, 800);
        // Set entities for each cell in the grid

        // Font
        font = new BitmapFont();

        // Input
        Gdx.input.setInputProcessor(inputAdapter);

        // Load the TiledMap
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/map2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        // Initialize HUD
        Texture heartTexture = new Texture("src/main/resources/HUD/heart16x16.png");
        hud = new HUD(heartTexture, 10); // Example: 10 hearts
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

        // Check collision with enemies if hit cooldown has elapsed
        long currentTime = TimeUtils.millis();
        if (currentTime - lastHitTime > hitCooldown) {
            for (int i = 1; i < entities.size(); i++) {
                Entity entity = entities.get(i);
                if (player.getHitbox().overlaps(entity.getHitbox()) && playerHealth > 0) {
                    // Collision detected, remove one heart
                    playerHealth--;
                    lastHitTime = currentTime;
                }
            }
        }

        // Update entities' positions
        for (Entity entity : entities.subList(1, entities.size())) {
            entity.moveTowards(player.getX(), player.getY(), 1000);
        }

        // Update HUD hearts
        hud.updateHearts(playerHealth);

        // Draw grid, entities, and HUD
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        grid.draw(batch);
        for (Entity entity : entities) {
            TextureRegion entitySprite = entitySprites.get(entities.indexOf(entity));
            batch.draw(entitySprite, entity.getX(), entity.getY(), Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        }

        // Debug
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        font.draw(batch, activePlayerDirections(), 10, 40);

        // Render HUD
        hud.draw(batch);

        batch.end();
    }

    /////////////////////////////////////////////////

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
