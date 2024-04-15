package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.model.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * The HUD class represents the Heads-Up Display in the game.
 */
public class HUD {
    private List<Heart> hearts;
    private Texture heartTexture;
    private int maxHearts;

    private int heartWidth = Constants.HEART_WIDTH;
    private int heartHeight = Constants.HEART_HEIGHT;
    private int heartPadding = Constants.HEART_PADDING;
    private int heartX = Constants.HEART_X;
    private int heartY = Constants.HEART_Y;

    /**
     * Constructs a HUD with the specified heart texture and maximum number of hearts.
     *
     * @param heartTexture the texture representing a heart icon
     * @param maxHearts    the maximum number of hearts to be displayed
     */
    public HUD(Texture heartTexture, int maxHearts) {
        this.heartTexture = heartTexture;
        this.maxHearts = maxHearts;
        createHearts();
        System.out.println("HUD created");
    }

    private void createHearts() {
        hearts = new ArrayList<>();
        for (int i = 0; i < maxHearts; i++) {
            float x = heartX + i * heartPadding;
            float y = heartY;
            hearts.add(new Heart(heartTexture, x, y, heartWidth, heartHeight, true));
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
     */
    public void updateHearts(int currentHealth) {
        for (int i = 0; i < hearts.size(); i++) {
            if (i < currentHealth) {
                hearts.get(i).setFilled(true);
            } else {
                hearts.get(i).setFilled(false);
            }
        }
    }
}
