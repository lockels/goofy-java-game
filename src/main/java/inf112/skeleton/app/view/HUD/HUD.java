package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static inf112.skeleton.app.utils.Constants.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The HUD class represents the Heads-Up Display in the game.
 */
public class HUD {
    private List<Heart> hearts;
    private Texture heartTexture;
    private int maxHearts;

    private float screenX;
    private float screenY;
    public Heart[] getHearts;

    /**
     * Constructs a HUD with the specified heart texture and maximum number of
     * hearts.
     *
     * @param heartTexture the texture representing a heart icon
     * @param maxHearts    the maximum number of hearts to be displayed
     * @param screenX      the screen X coordinate where the hearts should be
     *                     displayed
     * @param screenY      the screen Y coordinate where the hearts should be
     *                     displayed
     */
    public HUD(Texture heartTexture, int maxHearts, float screenX, float screenY) {
        this.heartTexture = heartTexture;
        this.maxHearts = maxHearts;
        this.screenX = screenX;
        this.screenY = screenY;
        createHearts();
        System.out.println("HUD: Created");
    }

    private void createHearts() {
        hearts = new ArrayList<>();
        for (int i = 0; i < maxHearts; i++) {
            float x = screenX + i * (HEART_WIDTH + HEART_HEIGHT);
            float y = screenY;
            // System.out.println("Heart Pos: " + x + ", " + y);
            hearts.add(new Heart(heartTexture, x, y, HEART_WIDTH, HEART_HEIGHT, true));
        }
    }

    /**
     * Draws the filled hearts representing player health.
     *
     * @param batch the SpriteBatch used for rendering
     */
    public void draw(SpriteBatch batch) {
        for (Heart heart : hearts) {
            if (heart.isFilled()) {
                heart.draw(batch);
            }
        }
    }

    /**
     * Updates the hearts based on the current player health.
     *
     * @param currentHealth the current health of the player
     * @param f       the screen X coordinate where the hearts should be
     *                      displayed
     * @param g       the screen Y coordinate where the hearts should be
     *                      displayed
     */
    public void updateHearts(int currentHealth, float f, float g) {
        this.screenX = f;
        this.screenY = g;
        // System.out.println("HUD: updateHearts");
        // System.out.println("currentHealth: " + currentHealth);
        // System.out.println("Heart position (x, y): " + this.screenX + ", " + this.screenY);
        for (int i = 0; i < hearts.size(); i++) {
            float x = (this.screenX + i * HEART_PADDING) - CAMERA_OFFSET_X + HEART_PADDING; 
            float y = this.screenY - CAMERA_OFFSET_Y + HEART_PADDING;
            hearts.get(i).setPosition(x, y);
            if (i < currentHealth) {
                hearts.get(i).setFilled(true);
            } else {
                hearts.get(i).setFilled(false);
            }
        }
    }
}
