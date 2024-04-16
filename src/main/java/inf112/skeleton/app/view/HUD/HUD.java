// package inf112.skeleton.app.view.HUD;

// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import inf112.skeleton.app.model.Constants;

// import java.util.ArrayList;
// import java.util.List;

// /**
//  * The HUD class represents the Heads-Up Display in the game.
//  */
// public class HUD {
//     private List<Heart> hearts;
//     private Texture heartTexture;
//     private int maxHearts;

//     private int heartWidth = Constants.HEART_WIDTH;
//     private int heartHeight = Constants.HEART_HEIGHT;
//     private int heartPadding = Constants.HEART_PADDING;
//     private int heartX = Constants.HEART_X;
//     private int heartY = Constants.HEART_Y;
//     private int cameraX;
//     private int cameraY;

//     /**
//      * Constructs a HUD with the specified heart texture and maximum number of hearts.
//      *
//      * @param heartTexture the texture representing a heart icon
//      * @param maxHearts    the maximum number of hearts to be displayed
//      */
//     public HUD(Texture heartTexture, int maxHearts, int cameraX, int cameraY) {
//         this.heartTexture = heartTexture;
//         this.maxHearts = maxHearts;
//         this.cameraX = cameraX;
//         this.cameraY = cameraY;
//         createHearts();
//         System.out.println("HUD created");
//     }

//     private void createHearts() {
//         hearts = new ArrayList<>();
//         for (int i = 0; i < maxHearts; i++) {
//             float x = heartX + i * heartPadding;
//             float y = heartY;
//             System.out.println("Heart Pos: " + x + ", " + y);
//             hearts.add(new Heart(heartTexture, x, y, heartWidth, heartHeight, true));
//         }
//     }

//     /**
//      * Draws the filled hearts representing player health.
//      *
//      * @param batch the SpriteBatch used for rendering
//      */
//     public void draw(SpriteBatch batch) {
//         for (Heart heart : hearts) {
//             if (heart.isFilled()) {
//                 heart.draw(batch);
//             }
//         }
//     }

//     /**
//      * Updates the hearts based on the current player health.
//      *
//      * @param currentHealth the current health of the player
//      */
//     public void updateHearts(int currentHealth, int heartCameraX, int heartCameraY) {
//         this.heartX = heartCameraX;
//         this.heartY = heartCameraY + 800;
//         System.out.println("HUD: updateHearts");
//         System.out.println("currentHealth: " + currentHealth);
//         System.out.println("Heart position (x, y): " + this.heartX + ", " + this.heartY);
//         for (int i = 0; i < hearts.size(); i++) {
//             if (i < currentHealth) {
//                 hearts.get(i).setFilled(true);
//             } else {
//                 hearts.get(i).setFilled(false);
//             }
//         }
//     }

//     // private void setHeartPositions() {
//     //     for (int i = 0; i < hearts.size(); i++) {
//     //         hearts.get(i).setX(heartX + i * heartPadding);
//     //         hearts.get(i).setY(heartY);
//     //     }
//     // }
// }

package inf112.skeleton.app.view.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static inf112.skeleton.app.model.Constants.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The HUD class represents the Heads-Up Display in the game.
 */
public class HUD {
    private List<Heart> hearts;
    private Texture heartTexture;
    private int maxHearts;

    private int screenX;
    private int screenY;

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
    public HUD(Texture heartTexture, int maxHearts, int screenX, int screenY) {
        this.heartTexture = heartTexture;
        this.maxHearts = maxHearts;
        this.screenX = screenX;
        this.screenY = screenY;
        createHearts();
        System.out.println("HUD created");
    }

    private void createHearts() {
        hearts = new ArrayList<>();
        for (int i = 0; i < maxHearts; i++) {
            float x = screenX + i * (HEART_WIDTH + HEART_HEIGHT);
            float y = screenY;
            System.out.println("Heart Pos: " + x + ", " + y);
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
     * @param screenX       the screen X coordinate where the hearts should be
     *                      displayed
     * @param screenY       the screen Y coordinate where the hearts should be
     *                      displayed
     */
    public void updateHearts(int currentHealth, int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
        System.out.println("HUD: updateHearts");
        System.out.println("currentHealth: " + currentHealth);
        System.out.println("Heart position (x, y): " + this.screenX + ", " + this.screenY);
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
