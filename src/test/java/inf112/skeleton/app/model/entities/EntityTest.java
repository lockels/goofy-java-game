// package inf112.skeleton.app.model.entities;

// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.Body;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.*;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// public class EntityTest {

//     @Mock
//     private Body mockBody;

//     private static class TestEntity extends Entity {
//         public TestEntity(Body body, String textureIdentifier, String tag) {
//             super(body, textureIdentifier, tag);
//         }
//     }

//     private TestEntity testEntity;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//         when(mockBody.getPosition()).thenReturn(new Vector2(0, 0)); // Setting initial position to (0, 0)
//         when(mockBody.getAngle()).thenReturn(0.0f); // Initial angle is 0 radians
//         testEntity = new TestEntity(mockBody, "testTexture", "testTag");
//     }

//     @Test
//     public void testGetters() {
//         assertEquals("testTexture", testEntity.getTextureId());
//         assertEquals("testTag", testEntity.getTag());
//         assertTrue(testEntity.isActive());
//     }

//     @Test
//     public void testSetPosition() {
//         testEntity.setPos(5f, 5f);
//         verify(mockBody).setTransform(eq(5f), eq(5f), anyFloat());
//     }

//     @Test
//     public void testSetAngle() {
//         testEntity.setAngle(90f); // Set angle to 90 degrees
//         verify(mockBody).setTransform(anyFloat(), anyFloat(), eq((float) -Math.PI / 2)); // 90 degrees to radians, negated
//     }

//     @Test
//     public void testIsActiveAndIsDestroyed() {
//         assertFalse(testEntity.getIsDestroyed());
//         testEntity.setIsDestroyed(true);
//         assertTrue(testEntity.getIsDestroyed());
//     }

//     @Test
//     public void testTrigger() {
//         testEntity.trigger(); // Should print to console; not actually verifiable in standard JUnit tests
//     }
// }

