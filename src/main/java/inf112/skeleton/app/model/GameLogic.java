package inf112.skeleton.app.model;

import inf112.skeleton.app.model.entities.*;

import static inf112.skeleton.app.model.Constants.*;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
* GameLogic handles the game logic including player and enemy interactions.
*/
public class GameLogic {
    // State
    private GameState gameState;
    // Entities
    private Player player;
    private Sword sword;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    // Time
    private long lastHitTime;
    private final long hitCooldown = HIT_COOLDOWN;
    private final int hitWarningDuration = HIT_WARNING_DURATION;
    private boolean showHitWarning = false;
    private long hitWarningStartTime = 0;
    private TiledMap map;
    private World world;
    // private Sound collisionSound;

    /**
     * Constructs a new GameLogic instance with the given game state.
     *
     * @param gameState the initial game state
     */
    public GameLogic(GameState gameState) {
        this.gameState = gameState;
        world = new World(new Vector2(0, -9.8f), true);
        // loadSounds();
        initializeEntities();
    }

    /**
     * Gets the list of entities in the game.
     *
     * @return the list of entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Gets the player entity.
     *
     * @return the player entity
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the current game state.
     *
     * @return the current game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Checks if the hit warning should be displayed.
     *
     * @return true if the hit warning should be displayed, false otherwise
     */
    public boolean isShowHitWarning() {
        return showHitWarning;
    }

    // ER DENNE BRUKT NOE STED??
    private void setShowHitWarning(boolean showHitWarning) {
        this.showHitWarning = showHitWarning;
    }

    private void initializeEntities() {
        initializeSword();
        entities.add(sword);
        initializePlayer();
        entities.add(player);
        initializeEnemies();
        entities.addAll(enemies);
    }

    private void initializePlayer() {
        this.player = new Player(new Rectangle(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, PLAYER_WIDTH, PLAYER_HEIGHT),
                DUNGEON_SHEET_IMG, PLAYER_SPRITESHEET_X, PLAYER_SPRITESHEET_Y, PLAYER_SPRITESHEET_WIDTH,
                PLAYER_SPRITESHEET_HEIGHT, 0, 0, 0, "Player");
    }

    private void initializeEnemies() {
        for (int i = 0; i < NUM_ENEMIES; i++) {
            float randomSpeed = MathUtils.random(ENEMY_SPEED_MIN, ENEMY_SPEED_MAX) * ENEMY_SPEED;
            Enemy enemy = new Enemy(new Rectangle(MathUtils.random(0, WINDOW_WIDTH),
                    MathUtils.random(0, WINDOW_HEIGHT),
                    ENEMY_WIDTH, ENEMY_HEIGHT),
                    DUNGEON_SHEET_IMG, ENEMY_SPRITESHEET_X,
                    ENEMY_SPRITESHEET_Y, ENEMY_SPRITESHEET_HEIGHT,
                    ENEMY_SPRITESHEET_WIDTH, randomSpeed, 0,0,0,"Enemy");
            enemies.add(enemy);
        }
    }

    private void initializeSword() {
        this.sword = new Sword(new Rectangle(0, 0, SWORD_WIDTH, SWORD_HEIGHT),
                SWORD_SPRITE_PATH, 0, 0, SWORD_HEIGHT,
                SWORD_WIDTH, SWORD_WIDTH/2, SWORD_Y_OFFSET, -45, "Sword");
    }

    /**
     * Updates the game logic.
     */
    public void update() {
        updateSword();
        updatePlayerPosition();
        checkPlayerHit();
        checkEnemyCollisions();
        checkGameOver();
        updateHitWarning();
        updateEnemyPositions();
    }

    //Sword
    private void updateSword(){
        updateSwordPosition();
        updateSwordAngle();
    }

    private void updateSwordPosition() { sword.move(player.getX(), player.getY()); }

    private void updateSwordAngle() {
        angleTowards(sword, Gdx.input.getX(), Gdx.input.getY());
    }

    //WIP - Doesnt work properly
    private float getAngle(Rectangle hitbox,  float targetX, float targetY) {
        float distanceX = targetX - hitbox.x;
        float distanceY = (Gdx.graphics.getHeight() - targetY) - hitbox.y;
        float angle = MathUtils.radiansToDegrees * MathUtils.atan2(distanceY, distanceX);
        if (angle < 0) { angle += 360; }
        return angle;
    }

    private void angleTowards(Entity entity, float targetX, float targetY) {
        entity.setAngle(getAngle(entity.getHitbox(), targetX, targetY));
    }

    private void updatePlayerPosition() {
        player.move();
    }

    private void checkEnemyCollisions() {
        for (Enemy enemy : enemies) {
            for (Enemy other : enemies) {
                if (enemy != other && enemy.collidesWith(other)) {
                    separateEntities(enemy, other);
                }
            }
        }
    }

    private void separateEntities(Entity entityA, Entity entityB) {
        float distanceX = entityB.getX() - entityA.getX();
        float distanceY = entityB.getY() - entityA.getY();
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        float overlap = (entityA.getHitbox().width + entityB.getHitbox().width) / 2 - distance;

        if (distance > 0) {
            distanceX /= distance;
            distanceY /= distance;
        }

        float separationX = overlap * distanceX / 2;
        float separationY = overlap * distanceY / 2;

        entityA.move(separationX, separationY);
    }

    private void checkPlayerHit() {
        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                applyHitToPlayer(enemy);
                // collisionSound.play();
            }
        }
    }

    private void applyHitToPlayer(Enemy enemy) {
        if (System.currentTimeMillis() - lastHitTime > hitCooldown) {
            player.takeDamage(HIT_DAMAGE);
            lastHitTime = System.currentTimeMillis();
            showHitWarning = true;
            hitWarningStartTime = System.currentTimeMillis();
        }
    }

    private void checkGameOver() {
        if (player.getHealth() <= 0) {
            gameState = GameState.GAME_OVER;
        }
    }

    private void updateHitWarning() {
        if (showHitWarning && System.currentTimeMillis() - hitWarningStartTime > hitWarningDuration) {
            showHitWarning = false;
        }
    }

    private void updateEnemyPositions() {
        for (Enemy enemy : enemies) {
            enemy.moveTowards(player.getX(), player.getY());
        }
    }

    private void checkTileCollisions() {
        // for (Entity entity : entities){
        // Rectangle entityBounds = entity.getHitbox();

        for (MapLayer layer : map.getLayers()) {

            if (layer.getName().equals("collision")) {

            }
        }

    }

}
