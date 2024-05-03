package inf112.skeleton.app.controller.myInput;

import com.badlogic.gdx.Input.Keys;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyInputProcessorTest {
    
    private MyInputProcessor processor = new MyInputProcessor();

    @Test
    void testKeyDown() {
        // Test that keyDown always returns false
        assertFalse(processor.keyDown(Keys.ANY_KEY));
    }

    @Test
    void testKeyTyped() {
        // Test that keyTyped always returns false
        assertFalse(processor.keyTyped('a'));
    }

    @Test
    void testKeyUp() {
        // Test that keyUp always returns false
        assertFalse(processor.keyUp(Keys.ANY_KEY));
    }

    @Test
    void testMouseMoved() {
        // Test that mouseMoved always returns false
        assertFalse(processor.mouseMoved(100, 100));
    }

    @Test
    void testScrolled() {
        // Test that scrolled always returns false
        assertFalse(processor.scrolled(1.0f, 1.0f));
    }

    @Test
    void testTouchCancelled() {
        // Test that touchCancelled always returns false
        assertFalse(processor.touchCancelled(100, 100, 1, 0));
    }

    @Test
    void testTouchDown() {
        // Test that touchDown always returns false
        assertFalse(processor.touchDown(100, 100, 1, 0));
    }

    @Test
    void testTouchDragged() {
        // Test that touchDragged always returns false
        assertFalse(processor.touchDragged(100, 100, 1));
    }

    @Test
    void testTouchUp() {
        // Test that touchUp always returns false
        assertFalse(processor.touchUp(100, 100, 1, 0));
    }
}

