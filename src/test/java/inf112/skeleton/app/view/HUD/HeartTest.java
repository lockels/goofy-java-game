package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.utils.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class HeartTest {

    private Heart heart;
    private Texture mockTexture;
    private SpriteBatch mockBatch;
    private final float x = Constants.HEART_X;
    private final float y = Constants.HEART_Y;
    private final float width = Constants.HEART_WIDTH;
    private final float height = Constants.HEART_HEIGHT;
    private final boolean filled = true;


    @BeforeEach
    public void setup() {
        mockTexture = mock(Texture.class);
        mockBatch = mock(SpriteBatch.class);
        heart = new Heart(mockTexture, x, y, width, height, true);
    }

    @Test
    public void testConstructor() {
        assertEquals(x, heart.getX());
        assertEquals(y, heart.getY());
        assertEquals(width, heart.getWidth());
        assertEquals(height, heart.getHeight());
        assertTrue(heart.isFilled());
    }   

    @Test
    public void testDraw() {
        heart.draw(mockBatch);
        verify(mockBatch).draw(mockTexture, x, y, width, height);
    }

    @Test
    public void testSetFilled() {
        heart.setFilled(false);
        assertFalse(heart.isFilled(), "Heart should be unfilled after setting to false");
    }

    @Test 
    public void testIsFilled(){
        assertEquals(filled, heart.isFilled());
    }

    @Test
    public void testSetPosition() {
        float newX = 200.0f;
        float newY = 250.0f;
        heart.setPosition(newX, newY);
        assertEquals(newX, heart.getX());
        assertEquals(newY, heart.getY());
    }

    @Test
    public void testGetX(){
        assertEquals(x, heart.getX());
    }

    @Test
    public void testGetY(){
        assertEquals(y, heart.getY());
    }

    @Test
    public void testGetWidth(){
        assertEquals(width, heart.getWidth());
    }

    @Test
    public void testGetHeight(){
        assertEquals(height, heart.getHeight());
    }
}

