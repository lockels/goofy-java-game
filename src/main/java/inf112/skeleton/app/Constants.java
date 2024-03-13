package inf112.skeleton.app;

/**
 * Interface which stores all constants used in-game.
 *
 * @author Fredric Hegland
 */
public interface Constants {
    // App
    int WINDOW_HEIGHT = 800;
    int WINDOW_WIDTH = 800;
    int GAME_FPS = 60;

    // Player
    int PLAYER_WIDTH = 24;
    int PLAYER_HEIGHT = 32;
    int PLAYER_ACCELERATION = 1000;
    int MAX_PLAYER_VELOCITY = 500;
    int PLAYER_FRICTION = 2000;

    // Grid
    int NUM_ROWS = 10;
    int NUM_COLS = 10;
    int CELL_WIDTH = WINDOW_WIDTH / NUM_COLS;
    int CELL_HEIGHT = WINDOW_HEIGHT / NUM_ROWS;
}
