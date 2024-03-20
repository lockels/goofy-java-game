package inf112.skeleton.app;

import inf112.skeleton.app.entities.Enemy;
import inf112.skeleton.app.entities.Entity;
import inf112.skeleton.app.entities.Player;

import static inf112.skeleton.app.Constants.*;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * GameLogic is the class that handles the game logic.
 * 
 * @author 
 */

public class GameLogic {
    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private int playerHealth = PLAYER_HEALTH;
    private long lastHitTime;
    private final long hitCooldown = HIT_COOLDOWN;
    private GameState gameState;
    private final int hitWarningDuration = HIT_WARNING_DURATION;
    private boolean showHitWarning = false;
    private long hitWarningStartTime = 0;
    private TiledMap map; // Add this variable to hold the Tiled map


    //box2D variables
    private World world;
    private Box2DDebugRenderer Box2DDebugRendderer;
    private 

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
        initializeEntities();
        this.map = new TmxMapLoader().load("maps/map2.tmx"); // Load the map here
    }

    private void initializeEntities() {
        player = new Player(new Rectangle(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, PLAYER_WIDTH, PLAYER_HEIGHT),
                DUNGEON_SHEET_IMG, PLAYER_SPRITESHEET_X, PLAYER_SPRITESHEET_Y, PLAYER_SPRITESHEET_WIDTH,
                PLAYER_SPRITESHEET_HEIGHT);
        entities.add(player);

        // Create enemies with random speeds
        for (int i = 0; i < NUM_ENEMIES; i++) {
            float randomSpeed = MathUtils.random(ENEMY_SPEED_MIN, ENEMY_SPEED_MAX) * ENEMY_SPEED;
            Enemy enemy = new Enemy(
                    new Rectangle(MathUtils.random(0, WINDOW_WIDTH), MathUtils.random(0, WINDOW_HEIGHT), ENEMY_WIDTH,
                            ENEMY_HEIGHT),
                    DUNGEON_SHEET_IMG, ENEMY_SPRITESHEET_X, ENEMY_SPRITESHEET_Y, ENEMY_SPRITESHEET_HEIGHT,
                    ENEMY_SPRITESHEET_WIDTH, randomSpeed);
            entities.add(enemy);
        }
    }

    public void update() {
        updatePlayerPosition();
        checkCollisions();
        checkGameOver();
        updateHitWarning();
        updateEnemyPositions();
    }

    private void updatePlayerPosition() {
        player.move();
    }

    private void checkCollisions() {
        int numOverlappingEnemies = 0;
        for (Entity entity : entities) {
            if (entity instanceof Enemy) {
                if (player.getHitbox().overlaps(entity.getHitbox()) && playerHealth > 0) {
                    if (System.currentTimeMillis() - lastHitTime > hitCooldown) {
                        numOverlappingEnemies++;
                    }
                }
            }
        }

        if (numOverlappingEnemies > 0) {
            int healthLost = HIT_DAMAGE * numOverlappingEnemies;
            updatePlayerHealth(healthLost);
            lastHitTime = System.currentTimeMillis();
            showHitWarning = true;
            hitWarningStartTime = System.currentTimeMillis();
        }
    }

    // author Ella og Sara
    private void checkCollisionWall() {
        // implementer at den ikke kan krasje i vegg
    }

    private void checkTransitionNewMap() {
        // implementer om den skal bytte til neste map
    }

    private void updatePlayerHealth(int healthLost) {
        playerHealth -= healthLost;
    }

    private void checkGameOver() {
        if (playerHealth <= 0) {
            gameState = GameState.GAME_OVER;
        }
    }

    private void updateHitWarning() {
        if (showHitWarning && System.currentTimeMillis() - hitWarningStartTime > hitWarningDuration) {
            showHitWarning = false;
        }
    }

    private void updateEnemyPositions() {
        for (Entity entity : entities) {
            if (entity instanceof Enemy) {
                ((Enemy) entity).moveTowards(player.getX(), player.getY());
            }
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int health) {
        this.playerHealth = health;
    }

    public boolean isShowHitWarning() {
        return showHitWarning;
    }

    public void setShowHitWarning(boolean showHitWarning) {
        this.showHitWarning = showHitWarning;
    }
}
