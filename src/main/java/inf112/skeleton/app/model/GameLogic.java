package inf112.skeleton.app.model;

import inf112.skeleton.app.model.entities.Enemy;
import inf112.skeleton.app.model.entities.Entity;
import inf112.skeleton.app.model.entities.Player;

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
 * GameLogic is the class that handles the game logic.
 *
 */

public class GameLogic {
    // State
    private GameState gameState;
    // Entites
    private Player player;
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
    //private Sound collisionSound; 



    public GameLogic(GameState gameState) {
        this.gameState = gameState;
        //this.map = map; 
        world = new World(new Vector2(0, -9.8f), true);
        //loadSounds();


        initializeEntities();
    }
    // void loadSounds() {
    //     collisionSound = Gdx.audio.newSound(Gdx.files.internal("resources/weapload.wav"));
    // }
    
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
        initializePlayer();
        entities.add(player);
        initializeEnemies();
        entities.addAll(enemies);
    }

    private void initializePlayer() {
        this.player = new Player(new Rectangle(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, PLAYER_WIDTH, PLAYER_HEIGHT),
                DUNGEON_SHEET_IMG, PLAYER_SPRITESHEET_X, PLAYER_SPRITESHEET_Y, PLAYER_SPRITESHEET_WIDTH,
                PLAYER_SPRITESHEET_HEIGHT);
    }

    private void initializeEnemies() {
        for (int i = 0; i < NUM_ENEMIES; i++) {
            float randomSpeed = MathUtils.random(ENEMY_SPEED_MIN, ENEMY_SPEED_MAX) * ENEMY_SPEED;
            Enemy enemy = new Enemy(new Rectangle(MathUtils.random(0, WINDOW_WIDTH),
                                                  MathUtils.random(0, WINDOW_HEIGHT),
                                                  ENEMY_WIDTH, ENEMY_HEIGHT),
                                                  DUNGEON_SHEET_IMG, ENEMY_SPRITESHEET_X,
                                                  ENEMY_SPRITESHEET_Y, ENEMY_SPRITESHEET_HEIGHT,
                                                  ENEMY_SPRITESHEET_WIDTH, randomSpeed);
            enemies.add(enemy);
            }
    }




    public void update() {
        updatePlayerPosition();
        checkPlayerHit();
        checkEnemyCollisions();
        checkGameOver();
        updateHitWarning();
        updateEnemyPositions();
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
                //collisionSound.play();
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

 
    private void checkTileCollisions() {
        // for (Entity entity : entities){
        //     Rectangle entityBounds = entity.getHitbox();

            for (MapLayer layer: map.getLayers() ){
                
                if(layer.getName().equals("collision")){

                }
            }

    }


    //lyd

    
}
        

