package inf112.skeleton.app;

import inf112.skeleton.app.entities.Enemy;
import inf112.skeleton.app.entities.Entity;
import inf112.skeleton.app.entities.Player;
import inf112.skeleton.app.grid.Grid;
import inf112.skeleton.app.myInput.MyInputAdapter;

import static inf112.skeleton.app.Constants.*;

import java.util.ArrayList;
import java.util.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.HUD.HUD;

/**
 * GameLogic is the class that handles the game logic.
 * 
 * @author Fredric Hegland
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

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
        initializeEntities();
    }

    private void initializeEntities() {
    player = new Player(new Rectangle(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, PLAYER_WIDTH, PLAYER_HEIGHT),
            DUNGEON_SHEET_IMG, PLAYER_SPRITESHEET_X, PLAYER_SPRITESHEET_Y, PLAYER_SPRITESHEET_WIDTH,
            PLAYER_SPRITESHEET_HEIGHT);
    entities.add(player);

    // Create enemies with random speeds
    for (int i = 0; i < NUM_ENEMIES; i++) {
        float randomSpeed = MathUtils.random(ENEMY_SPEED_MIN, ENEMY_SPEED_MAX) * ENEMY_SPEED;
        Enemy enemy = new Enemy(new Rectangle(MathUtils.random(0, WINDOW_WIDTH), MathUtils.random(0, WINDOW_HEIGHT), ENEMY_WIDTH, ENEMY_HEIGHT),
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
