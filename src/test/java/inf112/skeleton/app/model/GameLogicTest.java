package inf112.skeleton.app.model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.skeleton.app.model.entities.Enemy;
import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.model.entities.Sword;
import inf112.skeleton.app.utils.Constants;

public class GameLogicTest {

   // @Mock private World mockWorld;
   //  @Mock private Player mockPlayer;
   //  @Mock private Enemy mockEnemy;
   //  @Mock private Sword mockSword;

   //  private GameLogic gameLogic;

   //  @BeforeEach
   //  public void setUp() {
   //      gameLogic = new GameLogic(GameState.GAME_ACTIVE);
   //      gameLogic.setWorld(mockWorld);
   //      gameLogic.setPlayer(mockPlayer);
   //  }

   // @Test
   // public void testInitialization() {
   //  // This test verifies that the GameLogic constructor initializes the game correctly

   //  // Verify that a contact listener is set on the World object upon initialization
   //  verify(mockWorld).setContactListener(any());

   //  // Assert that the player object is not null after GameLogic initialization
   //  assertNotNull(gameLogic.getPlayer(), "The player should be initialized and not null");
   // }

}


