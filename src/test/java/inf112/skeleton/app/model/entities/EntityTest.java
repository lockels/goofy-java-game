package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    @Mock
    private Body mockBody;    
    private TestEntity testEntity;


    private static class TestEntity extends Entity {
        public TestEntity(Body body, String textureIdentifier, String tag) {
            super(body, textureIdentifier, tag);
        }
    }


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockBody.getPosition()).thenReturn(new Vector2(0, 0)); // Setting initial position to (0, 0)
        when(mockBody.getAngle()).thenReturn(0.0f); // Initial angle is 0 radians
        testEntity = new TestEntity(mockBody, "testTextureId", "testTag");
    }

    @Test 
    public void testGetIsDestroyedAndSetIsDestroyed(){
        //tests both getIsDestroyed and setIsDestroyed
        assertFalse(testEntity.getIsDestroyed());
        testEntity.setIsDestroyed(true);
        assertTrue(testEntity.getIsDestroyed());
    }

    @Test
    public void testGetPositionAndSetPosition() {
    // Test initial position
    Vector2 initialPosition = new Vector2(0, 0);
    assertEquals(initialPosition, testEntity.getPosition());

    // Change position and test the new pos
    Vector2 newPosition = new Vector2(20, 20);
    when(mockBody.getPosition()).thenReturn(newPosition); 
    testEntity.setPos(20, 20); 
    assertEquals(newPosition, testEntity.getPosition());
    }

    @Test 
    public void testGetXAndGetY(){
        Vector2 position = new Vector2(10.5f, 20.0f);
        when(mockBody.getPosition()).thenReturn(position);
        float x = testEntity.getX();
        float y = testEntity.getY();

        assertEquals(10.5f, x);
        assertEquals(20.0f, y);
    }

    @Test
    public void testSetAngle() {
        testEntity.setAngle(90f);
        verify(mockBody).setTransform(anyFloat(), anyFloat(), eq((float) -Math.PI / 2)); 
    }

    @Test
    public void testGetBody() {
        assertSame(mockBody, testEntity.getBody());
    }

    @Test
    public void testGetTag() {
        assertEquals("testTag", testEntity.getTag());
    }

    @Test
    public void testGetTextureId(){
        assertEquals("testTextureId", testEntity.getTextureId());
    }

    @Test
    public void testGetAngle() {
        float radians = (float) Math.PI / 2; // 90 degrees in radians
        when(mockBody.getAngle()).thenReturn(radians);

        // Assert
        assertEquals(90.0f, testEntity.getAngle());
    }

    @Test
    public void testIsActiveAndIsDestroyed() {
        assertFalse(testEntity.getIsDestroyed());
        testEntity.setIsDestroyed(true);
        assertTrue(testEntity.getIsDestroyed());
    }

    @Test
    public void testGetBaseAngleAndSetBaseAngle() {
        assertEquals(0.0f, testEntity.getBaseAngle());

        // Set new base angle
        float newBaseAngle = 180.0f;
        testEntity.setBaseAngle(newBaseAngle);

        assertEquals(newBaseAngle, testEntity.getBaseAngle());
    }
}

