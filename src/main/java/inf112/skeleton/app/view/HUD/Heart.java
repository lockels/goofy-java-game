package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The Heart class represents a graphical heart icon used in the Heads-Up Display (HUD).
 */
public class Heart {
    private Texture heartTexture;
    private float x;
    private float y;
    private float width;
    private float height;
    private boolean filled;

    /**
     * Constructs a Heart object with the specified parameters.
     *
     * @param texture the texture representing the heart icon
     * @param x       the x-coordinate of the heart's top-left corner
     * @param y       the y-coordinate of the heart's top-left corner
     * @param width   the width of the heart icon
     * @param height  the height of the heart icon
     * @param filled  true if the heart is filled, false otherwise
     */
    public Heart(Texture texture, float x, float y, float width, float height, boolean filled) {
        this.heartTexture = texture;
        this.x = x;
        this.y = y;
        this.width = width; 
        this.height = height;
        this.filled = filled;
    }

    /**
     * Draws the heart icon on the screen using the specified SpriteBatch.
     *
     * @param batch the SpriteBatch used for rendering
     */
    public void draw(SpriteBatch batch) {
        System.out.println("Heart: draw " + x + ", " + y);
        batch.draw(heartTexture, x, y, width, height);
    }

    /**
     * Sets the filled status of the heart icon.
     *
     * @param filled true if the heart should be filled, false otherwise
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Checks if the heart icon is filled.
     *
     * @return true if the heart is filled, false otherwise
     */
    public boolean isFilled() {
        return filled;
    }

    public void setPosition(float x2, float y2) {
        this.x = x2;
        this.y = y2;
    }
}
