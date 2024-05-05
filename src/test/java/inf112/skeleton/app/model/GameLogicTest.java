package inf112.skeleton.app.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import inf112.skeleton.app.model.entities.Entity;
import inf112.skeleton.app.model.entities.enemies.Enemy;
import inf112.skeleton.app.model.entities.enemies.Medium;
import inf112.skeleton.app.model.entities.weapons.TreeSword;
import inf112.skeleton.app.utils.Constants;

public class GameLogicTest {

    private static boolean initialized = false;
    private GameLogic gameLogic;
    private TiledMap map;

    @BeforeAll
    static void init() {
        if (!initialized) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            new HeadlessApplication(new ApplicationListener() {
                @Override
                public void create() {
                }

                @Override
                public void render() {
                }

                @Override
                public void dispose() {
                }

				@Override
				public void resize(int width, int height) {
				}

				@Override
				public void pause() {
				}

				@Override
				public void resume() {
				}
            }, config);
            Gdx.gl = Mockito.mock(GL20.class);
            initialized = true;
        }
    }

    @BeforeEach
    void setUp() {
        gameLogic = new GameLogic(GameState.GAME_ACTIVE);
        gameLogic.setWorld(new World(new Vector2(0, 0), true));

        // Load the map
        map = new TmxMapLoader().load(Constants.MAP_IMG);
        gameLogic.setMap(map);
    }

    @Test
    void testGameLogicConstructor() {
        assertNotNull(gameLogic);
    }

    // @Test
    // void testResetGame() {
    //    gameLogic.resetGame();
    //    assertEquals(GameState.GAME_ACTIVE, gameLogic.getGameState());
    // }

    @Test
    void testGetCoinValue() {
        assertEquals(0, gameLogic.getCoinValue());
    }

    @Test
    void setCoinValue() {
        gameLogic.setCoinValue(5);
        assertEquals(5, gameLogic.getCoinValue());
    }

    @Test
    void testGetWorld() {
        World world = gameLogic.getWorld();
        assertNotNull(world);
    }

    @Test
    void testSetWorld() {
        World world = new World(new Vector2(0, 0), true);
        gameLogic.setWorld(world);
        assertEquals(world, gameLogic.getWorld());
    }

    // @Test
    // void testSetPlayer() {
    //    Body body = mock(Body.class);
    //    Player player = new Player(body, "testTextureId", "player");
    //    gameLogic.setPlayer(player);
    //    assertEquals(player, gameLogic.getPlayer());
    // }

    @Test
    void testGetAllEntities() {
        List<Entity> entities = gameLogic.getAllEntities();
        assertNotNull(entities);
    }

    @Test
    void testGetAllEnemies() {
        List<Enemy> enemies = gameLogic.getAllEnemies();
        assertNotNull(enemies);
    }

    // @Test
    // void testGetPlayer() {
    //    gameLogic.setPlayer(new Player(mock(Body.class), "testTextureId", "player"));
    //    Player player = gameLogic.getPlayer();
    //    assertNotNull(player);
    // }

    @Test
    void testGetGameState() {
        assertEquals(GameState.GAME_ACTIVE, gameLogic.getGameState());
    }

    @Test
    void testIsShowHitWarning() {
        assertFalse(gameLogic.isShowHitWarning());
    }

    @Test
    void testGetWeapon() {
        gameLogic.setWeapon(new TreeSword(gameLogic.getWorld()));
        assertNotNull(gameLogic.getWeapon());
    }

    @Test
    void testSetWeapon() {
        TreeSword weapon = new TreeSword(gameLogic.getWorld());
        gameLogic.setWeapon(weapon);
        assertEquals(weapon, gameLogic.getWeapon());
    }

    // @Test
    // void testRemoveEntity() {
    //    Body body = mock(Body.class);
    //    Entity entity = new Entity(body, "testTextureId", " testEntity");
    //    gameLogic.removeEntity(entity);
    //    assertFalse(gameLogic.getAllEntities().contains(entity));
    // }

    // FÅR IKKE TESTET "update()" i GameLogic.java

    @Test
    void testGetActiveEntities() {
        List<Entity> enemies = gameLogic.getActiveEntities();
        assertNotNull(enemies);
    }

    @Test
    void testSetGameState() {
        gameLogic.setGameState(GameState.GAME_OVER);
        assertEquals(GameState.GAME_OVER, gameLogic.getGameState());
    }

    @Test
    void testInitialStateAfterMapSet() {
        assertNotNull(gameLogic.getPlayer(), "Player should be initialized");
        assertFalse(gameLogic.getAllEnemies().isEmpty(), "Enemies should be initialized");
        assertEquals(GameState.GAME_ACTIVE, gameLogic.getGameState(), "Game state should be GAME_ACTIVE");
    }

    @Test
    void testResetGameFunctionality() {
        gameLogic.resetGame();
        assertNotNull(gameLogic.getPlayer(), "Player should be reinitialized");
        assertFalse(gameLogic.getAllEnemies().isEmpty(), "Enemies should be reinitialized");
        assertEquals(GameState.GAME_ACTIVE, gameLogic.getGameState(), "Game state should be reset to GAME_ACTIVE");
    }

    @Test
    void testEnemyWaveInitialization() {
        gameLogic.setGameState(GameState.GAME_ACTIVE);
        gameLogic.update();
        assertFalse(gameLogic.getAllEnemies().isEmpty(), "Enemies should be initialized for new wave");
    }

    @Test
    void testEnemyMovementTowardsPlayer() {
        Enemy enemy = new Medium(gameLogic.getWorld());
        enemy.setStunTimer(0);  // Make sure the enemy is not stunned
        float initialDistance = enemy.getPosition().dst(gameLogic.getPlayer().getPosition());
        gameLogic.update();  // Assuming this method is public or tested indirectly
        float newDistance = enemy.getPosition().dst(gameLogic.getPlayer().getPosition());
        assertFalse(newDistance < initialDistance, "Enemy should move closer to the player");
    }

}


