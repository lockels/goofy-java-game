package inf112.skeleton.app;

/**
 * Interface which stores all constants used in-game.
 *
 * @author Fredric Hegland
 */
public class Constants {
    // App
    public final static int WINDOW_HEIGHT = 800;
    public final static int WINDOW_WIDTH = 800;
    public final static int GAME_FPS = 60;

    // Player
    public final static int PLAYER_WIDTH = 24;
    public final static int PLAYER_HEIGHT = 32;
    public final static int PLAYER_ACCELERATION = 1000;
    public final static int MAX_PLAYER_VELOCITY = 500;
    public final static int PLAYER_FRICTION = 2000;
    public final static int PLAYER_HEALTH = 10;

    // Grid
    public final static int NUM_ROWS = 10;
    public final static int NUM_COLS = 10;
    public final static int CELL_WIDTH = WINDOW_WIDTH / NUM_COLS;
    public final static int CELL_HEIGHT = WINDOW_HEIGHT / NUM_ROWS;

    // HUD
    public final static int HEART_WIDTH = 20;
    public final static int HEART_HEIGHT = HEART_WIDTH;
    public final static int HEART_PADDING = 25;
    public final static int HEART_X = 30;
    public final static int HEART_Y = 800 - HEART_HEIGHT - HEART_X;

    // Game
    public final static int HIT_COOLDOWN = 1000;
    public final static int HIT_WARNING_DURATION = 150;

}
