package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Heart {
    private Texture heartTexture;
    private float x;
    private float y;
    private float width;
    private float height;
    private boolean filled;

    public Heart(Texture texture, float x, float y, float width, float height, boolean filled) {
        this.heartTexture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filled = filled;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(heartTexture, x, y, width, height);
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }
}
