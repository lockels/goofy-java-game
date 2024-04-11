package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.entities.*;

import static inf112.skeleton.app.Constants.*;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * GameLogic is the class that handles the game logic.
<<<<<<< HEAD
 * 
 * @author Fredric Hegland
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

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
        initializeEntities();
    }
    
    public List<Entity> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean isShowHitWarning() {
        return showHitWarning;
    }

    public void setShowHitWarning(boolean showHitWarning) {
        this.showHitWarning = showHitWarning;
    }

    private void initializeEntities() {
        //Sword
        initializeSword();
        entities.add(sword);
        //Characters
        initializePlayer();
        entities.add(player);
        initializeEnemies();
        entities.addAll(enemies);

    }
    private void initializePlayer() {
        this.player = new Player(new Rectangle(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, PLAYER_WIDTH, PLAYER_HEIGHT),
                DUNGEON_SHEET_IMG, PLAYER_SPRITESHEET_X, PLAYER_SPRITESHEET_Y, PLAYER_SPRITESHEET_HEIGHT,
                PLAYER_SPRITESHEET_WIDTH, 0, 0, 0, "Player");
    }

    private void initializeEnemies() {
        for (int i = 0; i < NUM_ENEMIES; i++) {
            float randomSpeed = MathUtils.random(ENEMY_SPEED_MIN, ENEMY_SPEED_MAX) * ENEMY_SPEED;
            Enemy enemy = new Enemy(new Rectangle(MathUtils.random(0, WINDOW_WIDTH),
                                                  MathUtils.random(0, WINDOW_HEIGHT),
                                                  ENEMY_WIDTH, ENEMY_HEIGHT),
                                                  DUNGEON_SHEET_IMG, ENEMY_SPRITESHEET_X,
                                                  ENEMY_SPRITESHEET_Y, ENEMY_SPRITESHEET_WIDTH,
                                                  ENEMY_SPRITESHEET_HEIGHT, 0, 0, 0, "Enemy" , randomSpeed);
            enemies.add(enemy);
            }
    }

    private void initializeSword() {
        this.sword = new Sword(new Rectangle(0, 0, SWORD_WIDTH, SWORD_HEIGHT),
                SWORD_SPRITE, 0, 0, SWORD_WIDTH, SWORD_HEIGHT, SWORD_WIDTH/2, -SWORD_OFFSET, -90,"Sword");
    }

    public void update() {
        updatePlayerPosition();
        updateSword();
        checkPlayerHit();
        checkEnemyCollisions();
        checkGameOver();
        updateHitWarning();
        updateEnemyPositions();
    }

    private void updatePlayerPosition() {
        player.move();
    }

    //Sword
    private void updateSword() {
        updateSwordPosition();
        updateSwordAngle();
    }
    private void updateSwordPosition() { sword.move(player.getX() + player.getSpriteWidth()/2, player.getY() + player.getSpriteHeight()/2 + SWORD_OFFSET); }
    private void updateSwordAngle() {
        float toMouseX = Gdx.input.getX() - sword.getX();
        float toMouseY = Gdx.graphics.getHeight() - Gdx.input.getY() - sword.getY();
        float angle = MathUtils.radiansToDegrees * MathUtils.atan2(toMouseY, toMouseX);
        sword.setAngle(angle);
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
        float overlap = (entityA.getHitbox().width + entityB.getHitbox().width) / 2 - distance; // Determine the amount of overlap

        if (distance > 0) {
            distanceX /= distance;
            distanceY /= distance;
        }

        // Calculate the separation distance based on the overlap and direction
        float separationX = overlap * distanceX / 2;
        float separationY = overlap * distanceY / 2;

        entityA.move(separationX, separationY);
    }

    private void checkPlayerHit() {
        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                applyHitToPlayer(enemy);
            }
        }
    }
    
    private void applyHitToPlayer(Enemy enemy) {
        if (System.currentTimeMillis() - lastHitTime > hitCooldown) {
            player.takeDamage(HIT_DAMAGE);
            lastHitTime = System.currentTimeMillis(); // Needed so that the enemies don't instantly drain the player's health
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
}
