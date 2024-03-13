package inf112.skeleton.app.HUD;

import com.badlogic.gdx.math.Rectangle;

public class Button {
    private Rectangle bounds;
    private String text;
    private Runnable onClickAction;
    private boolean clicked;

    public Button(float x, float y, float width, float height, String text, Runnable onClickAction) {
        this.bounds = new Rectangle(x, y, width, height);
        this.text = text;
        this.onClickAction = onClickAction;
        this.clicked = false;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public String getText() {
        return text;
    }

    public void onClick() {
        if (onClickAction != null) {
            onClickAction.run();
        }
    }
}
