package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.model.Constants;

import java.util.ArrayList;
import java.util.List;

public class HUD {
    private List<Heart> hearts;
    private Texture heartTexture;
    private int maxHearts;

    private int heartWidth = Constants.HEART_WIDTH;
    private int heartHeight = Constants.HEART_HEIGHT;
    private int heartPadding = Constants.HEART_PADDING;
    private int heartX = Constants.HEART_X;
    private int heartY = Constants.HEART_Y;

    public HUD(Texture heartTexture, int maxHearts) {
        this.heartTexture = heartTexture;
        this.maxHearts = maxHearts;
        createHearts();
    }

    private void createHearts() {
        hearts = new ArrayList<>();
        for (int i = 0; i < maxHearts; i++) {
            float x = heartX + i * heartPadding;
            float y = heartY;
            hearts.add(new Heart(heartTexture, x, y, heartWidth, heartHeight, true));
        }
    }

    public void draw(SpriteBatch batch) {
        for (Heart heart : hearts) {
            if (heart.isFilled()) {
                heart.draw(batch);
            }
        }
    }

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
