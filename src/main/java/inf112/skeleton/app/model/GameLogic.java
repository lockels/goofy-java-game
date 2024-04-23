package inf112.skeleton.app.model;

import inf112.skeleton.app.model.entities.*;
import static inf112.skeleton.app.utils.Constants.*;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
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
    public World world;
    // private Sound collisionSound;

    /**
     * Constructs a new GameLogic instance with the given game state.
     *
     * @param gameState the initial game state
     */
    public GameLogic(GameState gameState) {
        this.gameState = gameState;
        setWorld(new World(new Vector2(0, -9.8f), true));
        // loadSounds();
        initializeEntities();
    }

    public World getWorld() {
        return world;
    }


    public void setWorld(World world) {
        this.world = world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
=======

	/**
>>>>>>> Stashed changes
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
    public void setShowHitWarning(boolean showHitWarning) {
        this.showHitWarning = showHitWarning;
    }

    private void initializeEntities() {
        initializePlayer();
        //initializeEnemies();
        initializeSword();
    }


    private void initializePlayer() {
        Body playerBody = PhysicsFactory.createEntityBody(world,
                new Vector2(PLAYER_SPAWN_X, PLAYER_SPAWN_Y),
                PLAYER_WIDTH,
                PLAYER_HEIGHT,
                "dynamic",
                true);
        this.player = new Player(playerBody, "playerSprite");
        entities.add(this.player);
    }

    private void initializeSword() {
        Body swordBody = PhysicsFactory.createEntityBody(world,
                new Vector2(0, 0),
                SWORD_WIDTH,
                SWORD_HEIGHT,
                "kinematic",
                false);
        this.sword = new Sword(swordBody, "swordSprite");
        entities.add(this.sword);
    }

    private void initializeEnemies() {
        for (int i = 0; i < NUM_ENEMIES; i++) {
            Body enemyBody = PhysicsFactory.createEntityBody(
                    world,
                    getRandomEnemyPosition(),
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    "dynamic",
                    true);
            float randomSpeed = MathUtils.random(ENEMY_SPEED_MIN, ENEMY_SPEED_MAX) * ENEMY_SPEED;
            Enemy enemy = new Enemy(enemyBody, "playerSprite", randomSpeed);
            enemies.add(enemy);
        }
        entities.addAll(enemies);
    }

    private Vector2 getRandomEnemyPosition() {
        Vector2 randomPosition;
        do {
            randomPosition = new Vector2(
                    MathUtils.random(0, WINDOW_WIDTH),
                    MathUtils.random(0, WINDOW_HEIGHT));
        } while (!isLegalSpawnPosition(randomPosition));
        return randomPosition;
    }

    private boolean isLegalSpawnPosition(Vector2 position) {
        // TiledMapTileLayer collisionLayer = (TiledMapTileLayer)
        // map.getLayers().get("out-of-bounds-layer"); // Replace with your actual
        // collision layer name
        // System.out.println(collisionLayer);

        //// Convert world coordinates to tile coordinates
        // int tileX = (int) (position.x / collisionLayer.getTileWidth());
        // int tileY = (int) (position.y / collisionLayer.getTileHeight());

        //// Check if the tile is part of the collision getLayer
        // return collisionLayer.getCell(tileX, tileY) == null;
        return true;
    }

    /**
     * Updates the game logic.
     */
    public void update() {
        //System.out.println("GameState: " + gameState);
        updateWorld();
        updatePlayerPosition();
        checkPlayerHit();
        // checkEnemyCollisions();
        checkGameOver();
        updateHitWarning();
        updateEnemyPositions();
        updateSword();
    }

    private void updateSword(){
        updateSwordPos();
        updateSwordAngle();
    }
    private void updateSwordPos() {sword.setPos(player.getX(), player.getY());}

    private void updateSwordAngle() {
        sword.setAngle(getAngleToMouse(400, 400));
    }

    private float getAngleToMouse(float x1, float y1){
        float angle = (float) Math.toDegrees(Math.atan2(Gdx.input.getY() - y1, Gdx.input.getX() - x1));
        if (angle < 0) { angle+=360; }
        return angle;
    }

    private void updateWorld() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        world.step(deltaTime, 6, 2); // The numbers 6 and 2 are velocity and position iterations, you can adjust
                                     // these as needed.
    }

    private void updatePlayerPosition() { player.move(); }

    // private void checkEnemyCollisions() {
    // for (Enemy enemy : enemies) {
    // for (Enemy other : enemies) {
    // if (enemy != other && enemy.collidesWith(other)) {
    // separateEntities(enemy, other);
    // }
    // }
    // }
    // }

    // private void separateEntities(Entity entityA, Entity entityB) {
    // float distanceX = entityB.getX() - entityA.getX();
    // float distanceY = entityB.getY() - entityA.getY();
    // float distance = (float) Math.sqrt(distanceX * distanceX + distanceY *
    // distanceY);
    // float overlap = (entityA.getHitbox().width + entityB.getHitbox().width) / 2 -
    // distance;

    // if (distance > 0) {
    // distanceX /= distance;
    // distanceY /= distance;
    // }

    // float separationX = overlap * distanceX / 2;
    // float separationY = overlap * distanceY / 2;

    // entityA.move(separationX, separationY);
    // }

    private void checkPlayerHit() {
        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                System.out.println("Player hit by enemy");
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
        //System.out.println("Player health: " + player.getHealth());
        //System.out.println("GameState: " + gameState);
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

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
