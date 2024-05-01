// package inf112.skeleton.app.model.entities;

// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.Body;

// import inf112.skeleton.app.model.Direction;
// import inf112.skeleton.app.utils.Constants;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.*;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// public class PlayerTest {

//     @Mock
//     private Body mockBody;
//     @Mock
//     private Coin mockCoin;
//     @Mock
//     private Enemy mockEnemy;

//     private Player player;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//         when(mockBody.getPosition()).thenReturn(new Vector2(0, 0)); // Default position
//         when(mockCoin.isCollected()).thenReturn(false);
//         when(mockCoin.getValue()).thenReturn(5);
//         when(mockEnemy.getBody()).thenReturn(mockBody);
        
//         player = new Player(mockBody, "testTextureId", "player");
//     }

//     @Test
//     public void testGetHealth() {
//         assertEquals(Constants.PLAYER_HEALTH, player.getHealth(), "Health should be initialized to PLAYER_HEALTH");
//     }

//     @Test
//     public void testCollectCoinNotCollected() {
//         player.collect(mockCoin);
//         verify(mockCoin).setCollected();
//         assertEquals(5, player.getCoinCount());
//     }

//     @Test
//     public void testCollectCoinAlreadyCollected() {
//         when(mockCoin.isCollected()).thenReturn(true);
//         player.collect(mockCoin);
//         verify(mockCoin, never()).setCollected();
//         assertEquals(0, player.getCoinCount());
//     }

//     @Test
//     public void testTakeDamage() {
//         int initialHealth = player.getHealth();
//         player.takeDamage(10);
//         assertEquals(initialHealth - 10, player.getHealth(), "Health should decrease by 10.");
//     }

//     @Test
//     public void testSetHealth() {
//         player.setHealth(80);
//         assertEquals(80, player.getHealth());
//     }

//     @Test
//     public void testMovementDirections() {
//         player.setMovement(Direction.UP, true);
//         assertTrue(player.getMovementDirections().get(Direction.UP));
//     }

//     @Test
//     public void testMove() {
//         player.setMovement(Direction.UP, true);
//         player.move();
//         ArgumentCaptor<Vector2> captor = ArgumentCaptor.forClass(Vector2.class);
//         verify(mockBody).applyForceToCenter(captor.capture(), eq(true));
//         Vector2 expectedForce = new Vector2(0, 1).nor().scl(Constants.PLAYER_ACCELERATION);
//         assertTrue(expectedForce.epsilonEquals(captor.getValue(), 0.01f));
//     }

//     @Test
//     public void testCollidesWithEnemy() {
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

