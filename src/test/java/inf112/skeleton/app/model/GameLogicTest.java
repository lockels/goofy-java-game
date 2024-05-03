package inf112.skeleton.app.model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.skeleton.app.model.entities.Entity;
import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.model.entities.enemies.Enemy;
import inf112.skeleton.app.model.entities.weapons.TreeSword;
import inf112.skeleton.app.model.entities.weapons.Weapon;
import inf112.skeleton.app.utils.Constants;

public class GameLogicTest {

   private GameLogic gameLogic;

   @BeforeEach
   void setUp() {
      gameLogic = new GameLogic(GameState.GAME_ACTIVE);
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
   //    Entity entity = new Entity(body, "testTextureId", "testEntity");
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

   // @Test
   // void testSetMap() {
   //    TiledMap map = mock(TiledMap.class);
   //    gameLogic.setMap(map);
   //    assertEquals(map, gameLogic.getMap());
   // }

   // @Test
   // void testGetMap() {
   //    gameLogic.setMap(mock(TiledMap.class));
   //    TiledMap map = gameLogic.getMap();
   //    assertNotNull(map);
   // }
}


