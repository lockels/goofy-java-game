package inf112.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class HUD {
    private List<Heart> hearts;
    private Texture heartTexture;
    private int maxHearts;

    public HUD(Texture heartTexture, int maxHearts) {
        this.heartTexture = heartTexture;
        this.maxHearts = maxHearts;
        createHearts();
    }

    private void createHearts() {
        hearts = new ArrayList<>();
        for (int i = 0; i < maxHearts; i++) {
            float heartX = 20 + i * 40;
            float heartY = 20;
            hearts.add(new Heart(heartTexture, heartX, heartY, 32, 32, true));
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
                System.out.println("Heart " + i + " is filled");
            } else {
                hearts.get(i).setFilled(false);
                System.out.println("Heart " + i + " is empty");
            }
        }
    }

}
