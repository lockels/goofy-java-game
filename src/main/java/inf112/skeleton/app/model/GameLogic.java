package inf112.skeleton.app.model;

import inf112.skeleton.app.model.entities.*;

import static inf112.skeleton.app.utils.Constants.*;

import java.util.List;
import java.util.ArrayList;

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
import inf112.skeleton.app.model.entities.weapons.Sword;
import inf112.skeleton.app.model.entities.weapons.Weapon;

/**
 * GameLogic handles the game logic including player and enemy interactions.
 */
public class GameLogic {
    // State
    private GameState gameState;
    // Entities
    private Player player;
    private Weapon weapon;
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
        setWorld(new World(new Vector2(0, 0), true));
        // loadSounds();
        world.setContactListener(new B2dContactListener());
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

    // ER DENNE BRUKT NOE STED??
    public void setShowHitWarning(boolean showHitWarning) {
        this.showHitWarning = showHitWarning;
    }

    private void initializeEntities() {
        initializePlayer();
        initializeEnemies();
        initializeWeapon();
    }

    private void initializePlayer() {
        Body playerBody = PhysicsFactory.createEntityBody(world,
                new Vector2(PLAYER_SPAWN_X, PLAYER_SPAWN_Y),
                new Vector2(),
                PLAYER_WIDTH,
                PLAYER_HEIGHT,
                true);
        this.player = new Player(playerBody, "playerSprite", "player");
        entities.add(this.player);
    }

    private void initializeWeapon() {
        this.weapon = new Sword(world);
        entities.add(this.weapon);
    }

    private void initializeEnemies() {
        for (int i = 0; i < NUM_ENEMIES; i++) {
            Body enemyBody = PhysicsFactory.createEntityBody(
                    world,
                    getRandomEnemyPosition(),
                    new Vector2(),
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    true);
            float randomSpeed = MathUtils.random(ENEMY_SPEED_MIN, ENEMY_SPEED_MAX) * ENEMY_SPEED;
            Enemy enemy = new Enemy(enemyBody, "playerSprite", "enemy", randomSpeed);
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
        MapLayer layer = map.getLayers().get("out-of-bounds-layer");
        if (layer == null) {
            return true;
        }

        for (MapObject object : layer.getObjects()) {
            if (object instanceof PolygonMapObject) {
                PolygonMapObject polygonObject = (PolygonMapObject) object;
                Polygon polygon = polygonObject.getPolygon();

                if (isPointInPolygon(polygon.getTransformedVertices(), position.x, position.y)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isPointInPolygon(float[] polygonVertices, float x, float y) {
        int intersects = 0;

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
        destroyInactiveEnemies();
        updatePlayerPosition();
        checkPlayerHit();
        // checkEnemyCollisions();
        checkGameOver();
        updateHitWarning();
        updateEnemyPositions();
        updateWeapon();
    }

    public List<Entity> getActiveEntities() {
        List<Entity> activeEntities = new ArrayList<>();
        for (Entity entity : entities){
            if (entity.isActive()) {activeEntities.add(entity);}
        }
        return activeEntities;
    }

    public List<Enemy> getActiveEnemies() {
        List<Enemy> activeEnemies = new ArrayList<>();
        for (Enemy enemy : enemies){
            if (enemy.isActive()) {activeEnemies.add(enemy);}
        }
        return activeEnemies;
    }

    private void destroyInactiveEnemies() {
        List<Enemy> activeEnemies = getActiveEnemies();
        for (Enemy enemy : getAllEnemies()){
            if (!activeEnemies.contains(enemy)){
                if (!enemy.getIsDestroyed()){
                    enemy.setIsDestroyed(true);
                    enemy.getBody().setTransform(0,0,0);//Temp solution: teleport body outside of map to avoid collisions
                    world.destroyBody(enemy.getBody());
                }
            }
        }
    }
    private void updateWeapon(){
        updateWeaponPos();
        updateWeaponAngle();
    }
    private void updateWeaponPos() {
        weapon.setPos(player.getX(), player.getY());
    }

    private void updateWeaponAngle() {
        weapon.setAngle(getAngleToMouse(400 - (PLAYER_WIDTH/2), 400 + (PLAYER_HEIGHT/2)));
    }

    private float getAngleToMouse(float x1, float y1){
        float angle = (float) Math.toDegrees(Math.atan2(Gdx.input.getY() - y1, Gdx.input.getX() - x1));
        angle += 90;
        if (angle < 0) { angle+=360; }
        angle %= 360;
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
                //System.out.println("Player hit by enemy");
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

    public void setMap(TiledMap map) {
        this.map = map;
        initializeEntities();
    }
}
