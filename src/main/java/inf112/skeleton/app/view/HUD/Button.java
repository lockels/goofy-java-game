package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.math.Rectangle;

/**
 * Represents a clickable button in the Heads-Up Display (HUD).
 */
public class Button {
    private Rectangle bounds;
    private String text;
    private Runnable onClickAction;
    private boolean clicked;

    /**
     * Constructs a new Button with the specified parameters.
     *
     * @param x             the x-coordinate of the button's top-left corner
     * @param y             the y-coordinate of the button's top-left corner
     * @param width         the width of the button
     * @param height        the height of the button
     * @param text          the text to be displayed on the button
     * @param onClickAction the action to be performed when the button is clicked
     */
    public Button(float x, float y, float width, float height, String text, Runnable onClickAction) {
        this.bounds = new Rectangle(x, y, width, height);
        this.text = text;
        this.onClickAction = onClickAction;
        this.clicked = false;
    }

    /**
     * Checks if the button has been clicked.
     *
     * @return true if the button has been clicked, false otherwise
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * Sets the clicked status of the button.
     *
     * @param clicked true to mark the button as clicked, false otherwise
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    /**
     * Gets the bounds of the button.
     *
     * @return the bounding rectangle of the button
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gets the text displayed on the button.
     *
     * @return the text of the button
     */
    public String getText() {
        return text;
    }

    /**
     * Performs the action associated with clicking the button.
     */
    public void onClick() {
        if (onClickAction != null) {
            onClickAction.run();
        }
    }
}
