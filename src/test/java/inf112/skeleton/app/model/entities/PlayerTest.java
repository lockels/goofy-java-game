// package inf112.skeleton.app.model.entities;

// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.Body;
// import com.badlogic.gdx.physics.box2d.Fixture;
// import com.badlogic.gdx.utils.Array;

// import inf112.skeleton.app.model.Direction;
// import inf112.skeleton.app.model.entities.enemies.Enemy;
// import inf112.skeleton.app.utils.Constants;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.*;

// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.*;

// import java.util.Map;

// import static org.junit.jupiter.api.Assertions.*;

// public class PlayerTest {

//    @Mock
//     private Body mockBody;

//     @Mock
//     private Fixture mockFixture;

//     @Mock
//     private Coin mockCoin;

//     @Mock
//     private Enemy mockEnemy;


//     private Player player;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);

//         // Mocking fixture list to avoid null pointer
//         Array<Fixture> fixtures = new Array<>();
//         fixtures.add(mockFixture); // Add mock fixture to the list
//         when(mockBody.getFixtureList()).thenReturn(fixtures);

//         when(mockBody.getPosition()).thenReturn(new Vector2(0, 0)); // Default position
//         when(mockCoin.isCollected()).thenReturn(false);
//         when(mockCoin.getValue()).thenReturn(5);
//         when(mockEnemy.getBody()).thenReturn(mockBody);

//         player = new Player(mockBody, "testTextureId", "player");
//     }

//     @Test
//     public void testGetHealth() {
//         assertEquals(Constants.PLAYER_HEALTH, player.getHealth());
//     }

//     @Test
//     public void testSetHealth() {
//         player.setHealth(80);
//         assertEquals(80, player.getHealth());
//     }

//     @Test
//     public void testSetInContactWithSpike() {
//         // Test setting to true
//         player.setInContactWithSpike(true);
//         assertTrue(player.isInContactWithSpike());

//         // Test setting to false
//         player.setInContactWithSpike(false);
//         assertFalse(player.isInContactWithSpike());
//     }

//     @Test
//     public void testIsInContactWithSpike() {
//         // Initially setting the player's spike contact to false and testing
//         player.setInContactWithSpike(false);
//         assertFalse(player.isInContactWithSpike());
//         // Setting the player's spike contact to true and testing
//         player.setInContactWithSpike(true);
//         assertTrue(player.isInContactWithSpike());
//     }

//     @Test
//     public void testCollect() {
//         // Arrange
//         when(mockCoin.isCollected()).thenReturn(false);
//         when(mockCoin.getValue()).thenReturn(10);

//         int initialCoinCount = player.getCoinCount(); // Assuming getCoinCount exists to track coins

//         player.collect(mockCoin);

//         verify(mockCoin, times(1)).setCollected();
//         verify(mockBody, times(1)).setActive(false);
//         assertEquals(initialCoinCount + 10, player.getCoinCount());
//     }

//     @Test
//     public void testTakeDamage() {
//         int initialHealth = player.getHealth();
//         player.takeDamage(10);
//         assertEquals(initialHealth - 10, player.getHealth(), "Health should decrease by 10.");
//     }

//     @Test
//     public void testGetMovementDirections() {
//         // Setup different directions and states
//         player.setMovement(Direction.UP, true);
//         player.setMovement(Direction.DOWN, false);
//         player.setMovement(Direction.LEFT, true);
//         player.setMovement(Direction.RIGHT, false);

//         // Act
//         Map<Direction, Boolean> directions = player.getMovementDirections();

//         // Assert
//         assertTrue(directions.get(Direction.UP), "North should be active.");
//         assertFalse(directions.get(Direction.DOWN), "South should be inactive.");
//         assertTrue(directions.get(Direction.LEFT), "East should be active.");
//         assertFalse(directions.get(Direction.RIGHT), "West should be inactive.");
//     }

//     @Test
//     public void testSetMovement() {
//         // Arrange
//         Direction testDirection = Direction.UP;
//         boolean initialActiveState = player.getMovementDirections().get(testDirection);
        
//         // Act
//         player.setMovement(testDirection, !initialActiveState); 

//         // Assert
//         assertNotEquals(initialActiveState, player.getMovementDirections().get(testDirection));
//     }

<<<<<<< HEAD
    // @Test
    // public void testMove_WhenMovingUp() {
    //     // Arrange
    //     player.setMovement(Direction.UP, true);

    //     // Act
    //     player.move();

    //     // Assert
    //     ArgumentCaptor<Vector2> vectorCaptor = ArgumentCaptor.forClass(Vector2.class);
    //     verify(mockBody).applyForceToCenter(vectorCaptor.capture(), eq(true));

       
    // }

    // @Test
    // public void testMove_WhenMovingInMultipleDirections() {
    //     // Arrange
    //     player.setMovement(Direction.UP, true);
    //     player.setMovement(Direction.RIGHT, true);

    //     // Act
    //     player.move();

    //     // Assert
    //     ArgumentCaptor<Vector2> vectorCaptor = ArgumentCaptor.forClass(Vector2.class);
    //     verify(mockBody).applyForceToCenter(vectorCaptor.capture(), eq(true));

    //     // Checking normalization
      
    // }
=======
//     @Test
//     public void testMove_WhenMovingUp() {
//         // Arrange
//         player.setMovement(Direction.UP, true);

//         // Act
//         player.move();

//         // Assert
//         ArgumentCaptor<Vector2> vectorCaptor = ArgumentCaptor.forClass(Vector2.class);
//         verify(mockBody).applyForceToCenter(vectorCaptor.capture(), eq(true));

       
//     }

//     @Test
//     public void testMove_WhenMovingInMultipleDirections() {
//         // Arrange
//         player.setMovement(Direction.UP, true);
//         player.setMovement(Direction.RIGHT, true);

//         // Act
//         player.move();

//         // Assert
//         ArgumentCaptor<Vector2> vectorCaptor = ArgumentCaptor.forClass(Vector2.class);
//         verify(mockBody).applyForceToCenter(vectorCaptor.capture(), eq(true));

//         // Checking normalization
      
//     }
>>>>>>> 6dd5fac (Fixes sword-sprites)

//     @Test
//     public void testStopMovement() {
//         // Act
//         player.stopMovement();

//         // Assert
//         verify(mockBody).setLinearVelocity(0, 0); // Verify that linear velocity is set to zero
//     }

//     @Test
//     public void testCollidesWith() {
//         when(mockEnemy.getBody()).thenReturn(mockBody);
//         when(mockEnemy.getBody().getPosition()).thenReturn(new Vector2(0, 0.5f));
//         assertTrue(player.collidesWith(mockEnemy));
//     }

//     @Test
//     public void testGetCoinCount() {
//         assertEquals(0, player.getCoinCount());
//         player.collect(mockCoin);
//         assertEquals(5, player.getCoinCount());
//     }

// }

