package inf112.skeleton.app.model;

import inf112.skeleton.app.model.entities.*;
import inf112.skeleton.app.utils.B2DPhysics.B2dContactListener;
import inf112.skeleton.app.utils.B2DPhysics.CollisionCallBack;
import inf112.skeleton.app.utils.B2DPhysics.PhysicsFactory;

import static inf112.skeleton.app.utils.Constants.*;

import java.util.List;
import java.util.ArrayList;
import inf112.skeleton.app.controller.myInput.SoundController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.entities.enemies.*;
import inf112.skeleton.app.model.entities.weapons.*;

/**
 * GameLogic handles the game logic including player and enemy interactions.
 */
public class GameLogic implements CollisionCallBack {
    // State
    private GameState gameState;
    private int wave;

    // Entities
    private Player player;
    private Weapon weapon;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private List<Polygon> spikePolygons = new ArrayList<>();

    // Time
    private long lastHitTime;
    private final long hitCooldown = HIT_COOLDOWN;
    private final int hitWarningDuration = HIT_WARNING_DURATION;
    private boolean showHitWarning = false;
    private long hitWarningStartTime = 0;
    private int coinValue = 0;
    private TiledMap map;
    public World world;
    // sounds
    private SoundController soundController = new SoundController();

    private float waveTimer = 0;
    private float waveDelay = 5; // 5 seconds delay for respawning enemies

    /**
     * Constructs a new GameLogic instance with the given game state.
     *
     * @param gameState the initial game state
     */

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
        setWorld(new World(new Vector2(0, 0), true));
        world.setContactListener(new B2dContactListener(this));
        soundController.initializeBackgroundMusic();
        soundController.setMusicVolume(0.1f);
    }

    /**
     * Resets the game to its initial state.
     * This method performs the following actions:
     * - Prints "Resetting game" to the console.
     * - Resets the player's health and position to predefined constants.
     * - Sets the player's linear velocity and applies zero force to the player's center.
     * - Clears all existing entities, coins, and enemies.
     * - Reinitializes the coins and enemies arrays and populates them.
     * - Adds player and weapon back to the entities list along with all enemies and coins.
     */
    public void resetGame() {
        // Reset player
        this.player.setHealth(PLAYER_HEALTH);
        this.player.setPos(PLAYER_SPAWN_X, PLAYER_SPAWN_Y);
        this.player.getBody().setLinearVelocity(new Vector2().scl(0));
        this.player.getBody().applyForceToCenter(new Vector2().scl(0), true);

        // Clear entities
        destroyInactiveEntities();
        entities.clear();
        coins.clear();
        enemies.clear();

        // Initialize new entities
        coins = new ArrayList<>();
        initializeCoins();
        enemies = new ArrayList<>();
        initializeEnemies();

        entities.add(player);
        entities.add(weapon);
        entities.addAll(enemies);
        entities.addAll(coins);
    }
    /**
     * Returns the current value of a coin.
     * @return the coin value as an integer.
     */
    public int getCoinValue() {
        return coinValue;
    }
    /**
     * Sets the value of a coin.
     * @param coinValue the new value for the coin as an integer.
     */
    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }
    /**
     * Returns the current world where the game entities exist.
     * @return the current instance of the World.
     */
    public World getWorld() {
        return world;
    }
    /**
     * Sets the world where the game entities exist.
     * @param world the new instance of the World.
     */
    public void setWorld(World world) {
        this.world = world;
    }
    /**
     * Sets the game's player to a new instance.
     * @param player the new Player instance to be set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the list of entities in the game.
     *
     * @return the list of entities
     */
    public List<Entity> getAllEntities() {
        return entities;
    }

    public List<Enemy> getAllEnemies() {
        return enemies;
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

    // Ensure there is a way to access the specific weapon
    public Weapon getWeapon() {
        return weapon;
    }
    /**
     * Sets the weapon for the game. If a weapon is already set, it removes the existing weapon before setting the new one.
     * Adds the new weapon to the entities list.
     * @param weapon the new weapon to be set for the game.
     */
    public void setWeapon(Weapon weapon) {
        if (this.weapon != null) 
            removeEntity(this.weapon);
        this.weapon = weapon;
        entities.add(weapon);
    }
    /**
     * Initializes all game entities including player, enemies, weapon, and coins.
     * Specific initialization methods for each entity type are called.
     */
    private void initializeEntities() {
        initializePlayer();
        initializeEnemies();
        initializeWeapon(new TreeSword(world));
        initializeCoins();
    }
    /**
     * Associates an entity with its physical representation in the physics engine.
     * This method sets the user data of the entity's first physics body fixture to the entity itself.
     * @param entity the game entity to be linked with its physics body.
     */
    private void setUserDataToParent(Entity entity) {
        entity.getBody().getFixtureList().get(0).setUserData(entity);
    }
    /**
     * Initializes the player by creating a dynamic body in the physics world with predefined dimensions and properties.
     * Sets the player's sprite and type, and adds the player to the game entities.
     */
    private void initializePlayer() {
        Body playerBody = PhysicsFactory.createDynamicEntityBody(world,
                new Vector2(PLAYER_SPAWN_X, PLAYER_SPAWN_Y),
                PLAYER_WIDTH,
                PLAYER_HEIGHT,
                true);
        this.player = new Player(playerBody, PLAYER_SPRITE, "player");
        setUserDataToParent(player);
        entities.add(this.player);
    }
    /**
     * Initializes a weapon by setting it directly to a specified weapon object and adding it to the game entities.
     * @param weapon the weapon to be initialized and added to the game.
     */
    private void initializeWeapon(Weapon weapon) {
        this.weapon = weapon;
        // this.weapon = new DiamondSword(world);
        entities.add(this.weapon);
    }
    /**
     * Initializes enemies based on the current game wave.
     * Creates different types of enemies (light, medium, heavy) in proportions based on the game's difficulty wave.
     * Each enemy is initialized at a random position and added to the enemies list and game entities.
     */
    private void initializeEnemies() {
        int num_enemies = NUM_STARTER_ENEMIES + 2 * this.wave;
        int num_heavy = (int) (num_enemies * 0.2f);
        int num_medium = (int) (num_enemies * 0.4f);
        int num_light = num_enemies - num_heavy - num_medium;
        int[] enemyTypes = {num_light, num_medium, num_heavy};
        for (int i = 0; i < enemyTypes.length; i++) {
            for (int j = 0; j < enemyTypes[i]; j++)   {
                Enemy enemy;
                if (enemyTypes[i] == num_light) {
                    enemy = new Light(world);
                } else if (enemyTypes[i] == num_medium) {
                    enemy = new Medium(world);
                } else if (enemyTypes[i] == num_heavy) {
                    enemy = new Heavy(world);
                } else {
                    throw new IllegalStateException("Unexpected value: " + enemyTypes[i]);
                }
                Vector2 randomPosition = getRandomEntityPosition();
                enemy.setPos(randomPosition.x, randomPosition.y);
                enemies.add(enemy);
            }
        }
        entities.addAll(enemies);
    }
    /**
     * Initializes coins and distributes them randomly within the game world.
     * Each coin is instantiated with a random value and sprite, then added to the game entities.
     */
    private void initializeCoins() {
        for (int i = 0; i < NUM_COINS; i++) {
            Body coinBody = PhysicsFactory.createStaticEntityBody(world,
                    getRandomEntityPosition(),
                    COIN_WIDTH,
                    COIN_HEIGHT);
            coinBody.setUserData("coin");
            Coin coin = switch ((int) (Math.random() * 5)) {
                case 0 -> new Coin(coinBody, COIN_SPRITE, COIN_VALUE_ONE, "coin");
                case 1 -> new Coin(coinBody, COIN_SPRITE, COIN_VALUE_TWO, "coin");
                case 2 -> new Coin(coinBody, COIN_SPRITE, COIN_VALUE_THREE, "coin");
                case 3 -> new Coin(coinBody, COIN_SPRITE, COIN_VALUE_FOUR, "coin");
                case 4 -> new Coin(coinBody, COIN_SPRITE, COIN_VALUE_FIVE, "coin");
                default -> throw new IllegalStateException("Unexpected value");
            };
            coins.add(coin);
        }
        entities.addAll(coins);
        
    }
    /**
     * Generates a random position for an entity within the game window.
     * Ensures that the generated position is a legal spawn position for entities.
     * @return a Vector2 representing a legal random position within the game window.
     */
    private Vector2 getRandomEntityPosition() {
        Vector2 randomPosition;
        do {
            randomPosition = new Vector2(
                    MathUtils.random(0, WINDOW_WIDTH),
                    MathUtils.random(0, WINDOW_HEIGHT));
        } while (!isLegalSpawnPosition(randomPosition));

        return randomPosition;
    }
    /**
     * Checks for collisions between the player and spikes.
     * If the player collides with a spike, the player takes damage and a hit is applied.
     */
    private void checkForSpikeCollisions() {
        Vector2 playerPosition = this.player.getPosition();
        for (Polygon spikePolygon : spikePolygons) {
            if (isPointInPolygon(spikePolygon.getTransformedVertices(), playerPosition)) {
                applyHitToPlayer();
                break;
            }
        }
    }
    /**
     * Removes a specified entity from the game.
     * If the entity is an enemy, it is removed from the enemy list.
     * The entity's body is destroyed in the physics world, and it is marked as destroyed.
     * @param entity the entity to be removed from the game.
     */
    public void removeEntity(Entity entity) {
        if (entity instanceof Enemy) {
            enemies.remove(entity);
        }
        world.destroyBody(entity.getBody());
        entities.remove(entity);
        entity.setIsDestroyed(true);
    }
    /**
     * Checks for collisions between the player and coins.
     * If a coin is collided with and not yet collected, it is marked as collected and its value is added to the player's score.
     */
    private void checkForCoinCollisions() {
        for (Coin coin : coins) {
            if (player.collidesWith(coin)) {
                if (!coin.isCollected()) {
                    coin.setCollected();
                    coinValue += coin.getValue();
                    soundController.playCollectCoinSound();
                }
            }
        }
    }
    /**
     * Determines if a given position is a legal spawn point for entities by checking if it overlaps
     * with any 'out-of-bounds' areas defined in the game map.
     * @param position the Vector2 position to check.
     * @return true if the position is legal (not out-of-bounds), false otherwise.
     */
    private boolean isLegalSpawnPosition(Vector2 position) {
        MapLayer layer = map.getLayers().get("out-of-bounds-layer");
        if (layer == null) {
            return true;
            
        }
        for (MapObject object : layer.getObjects()) {
            if (object instanceof PolygonMapObject) {
                PolygonMapObject polygonObject = (PolygonMapObject) object;
                Polygon polygon = polygonObject.getPolygon();

                if (isPointInPolygon(polygon.getTransformedVertices(), position)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Determines if a given point is inside a polygon defined by a series of vertices.
     * Uses the ray-casting method to count intersections of a horizontal line passing through the point
     * with each edge of the polygon.
     * @param polygonVertices an array of floats representing the x, y pairs of the polygon's vertices.
     * @param point the point to check.
     * @return true if the point is inside the polygon, false otherwise.
     */
    private boolean isPointInPolygon(float[] polygonVertices, Vector2 point) {
        int intersects = 0;
        float x = point.x;
        float y = point.y;

        for (int i = 0; i < polygonVertices.length; i += 2) {
            float x1 = polygonVertices[i];
            float y1 = polygonVertices[i + 1];
            float x2 = polygonVertices[(i + 2) % polygonVertices.length];
            float y2 = polygonVertices[(i + 3) % polygonVertices.length];

            if (((y1 <= y && y < y2) || (y2 <= y && y < y1)) &&
                    (x < (x2 - x1) * (y - y1) / (y2 - y1) + x1)) {
                intersects++;
            }
        }

        return (intersects % 2) == 1;
    }

    /**
     * Updates the game logic.
     */
    public void update() {
        updateWorld();
        destroyInactiveEntities();
        updatePlayerPosition();
        checkPlayerHit();
        checkForSpikeCollisions();
        checkForCoinCollisions();
        checkGameOver();
        updateHitWarning();
        updateEnemyStunTimer();
        updateEnemyPositions();
        updateWeapon();
        updateWave();
    }
    /**
     * Retrieves a list of all active entities in the game.
     * An entity is considered active if its 'isActive' method returns true.
     * @return a list of active entities.
     */
    public List<Entity> getActiveEntities() {
        List<Entity> activeEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.isActive()) {
                activeEntities.add(entity);
            }
        }
        return activeEntities;
    }
    /**
     * Removes all inactive entities from the game.
     * It first gathers all active entities and then removes any that are not in this list and have not been destroyed.
     * If the inactive entity is an enemy, it also plays the death sound for that enemy type.
     */
    private void destroyInactiveEntities() {
        List<Entity> activeEntities = getActiveEntities();
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Entity entity : entities) {
            if (!activeEntities.contains(entity)) {
                if (!entity.getIsDestroyed()) {
                    if (entity instanceof Enemy) {
                        enemiesToRemove.add((Enemy) entity);
                        playEnemyDeathSound((Enemy) entity);
                    }
                }
            }
        }
        for (Enemy enemy : enemiesToRemove) {
            removeEntity(enemy);
        }
    }
    /**
     * Plays a specific sound effect based on the type of enemy that was destroyed.
     * @param enemy the enemy that was destroyed.
     */
    private void playEnemyDeathSound(Enemy enemy) {
        if (enemy instanceof Light) {
            soundController.playTechoShotSound();
        } else if (enemy instanceof Medium) {
            soundController.playDamageSound();
        } else if (enemy instanceof Heavy) {
            soundController.playPestilenceSound();
        }
    }

    private void updateWeapon() {
        updateWeaponCooldownTimer();
        updateWeaponPos();
        updateWeaponAngle();
    }

    private void updateWeaponPos() {
        weapon.setPos(player.getX(), player.getY());
    }

    private void updateWeaponAngle() {
        weapon.setAngle(getAngleToMouse(400 - (PLAYER_WIDTH / 2), 400 + (PLAYER_HEIGHT / 2)));
    }

    private void updateWeaponCooldownTimer() {
        weapon.setCooldownTimer((weapon.getCooldownTimer() - (float) 1 / 60));
        if (weapon.getCooldownTimer() > 0) {
            weapon.setOpacity(0.5f);
        } else {
            weapon.setOpacity(1f);
        }
    }

    private void updateEnemyStunTimer() {
        for (Enemy enemy : enemies) {
            enemy.setStunTimer((enemy.getStunTimer() - (float) 1 / 60));
            if (enemy.getStunTimer() > 0) {
                enemy.setOpacity(0.5f);
            } else {
                enemy.setOpacity(1f);
            }
        }
    }
    /**
     * Calculates the angle from a given point to the mouse cursor in degrees.
     * The angle is adjusted to be between 0 and 360 degrees.
     * @param x1 the x-coordinate of the starting point.
     * @param y1 the y-coordinate of the starting point.
     * @return the angle in degrees from the point to the mouse cursor.
     */
    private float getAngleToMouse(float x1, float y1) {
        float angle = (float) Math.toDegrees(Math.atan2(Gdx.input.getY() - y1, Gdx.input.getX() - x1));
        angle += 90;
        if (angle < 0) {
            angle += 360;
        }
        angle %= 360;
        return angle;
    }
    /**
     * Updates the physics world simulation.
     * @param deltaTime the time elapsed since the last frame in seconds.
     */
    private void updateWorld() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        world.step(deltaTime, 6, 2); // The numbers 6 and 2 are velocity and position iterations, you can adjust
    }

    private void updatePlayerPosition() {
        player.move();
    }

    private void updateWave() {
        waveTimer += Gdx.graphics.getDeltaTime();
        if (enemies.size() == 0 && waveTimer >= waveDelay) {
            player.setHealth(PLAYER_HEALTH);
            initializeEnemies();
            if (coins.size() == 0) {
                initializeCoins();
            }
            wave += 1;
            waveTimer = 0;
        }
    }

    private void checkPlayerHit() {
        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                applyHitToPlayer();

            }
        }
    }
    /**
     * Applies damage to the player and plays a damage sound.
     * Checks the cooldown to prevent repeated hits in a short time.
     */
    private void applyHitToPlayer() {
        if (System.currentTimeMillis() - lastHitTime > hitCooldown) {
            player.takeDamage(HIT_DAMAGE);
            lastHitTime = System.currentTimeMillis();
            showHitWarning = true;
            hitWarningStartTime = System.currentTimeMillis();
        }
        soundController.playDamageSound();
    }

    private void checkGameOver() {
        if (player.getHealth() <= 0) {
            gameState = GameState.GAME_OVER;
            soundController.playGameOverSound();
            soundController.stopBackgroundMusic(); // Stop the background music
        }

    }

    private void updateHitWarning() {
        if (showHitWarning && System.currentTimeMillis() - hitWarningStartTime > hitWarningDuration) {
            showHitWarning = false;
        }
    }

    private void updateEnemyPositions() {
        for (Enemy enemy : enemies) {
            if (enemy.getStunTimer() <= 0) {
                enemy.moveTowards(player.getX(), player.getY());
            } // If enemy has no stun remaining
        }
    }
    /**
     * Sets the current game state to a new value.
     * @param gameState the new state to set for the game.
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    /**
     * Sets the game map to a new TiledMap and reinitializes all game entities.
     * @param map the new TiledMap to set.
     */
    public void setMap(TiledMap map) {
        this.map = map;
        initializeEntities();
    }

    /**
     * Handles collision between the player and a spike object.
     * Applies damage to the player and plays a corresponding sound.
     * @param player the player who collided with the spike.
     * @param spike the spike that collided with the player.
     */
    @Override
    public void onPlayerSpikeCollision(Player player, Spike spike) {
        applyHitToPlayer();
        soundController.spikeHurtSound();
    }
    /**
     * Returns the current game map.
     * @return the TiledMap currently set for the game.
     */
    public TiledMap getMap() {
        return map;
    }
}
