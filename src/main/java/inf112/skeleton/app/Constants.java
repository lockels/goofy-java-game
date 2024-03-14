package inf112.skeleton.app;

/**
 * Class which stores all constants used in-game.
 *
 * @author Fredric Hegland
 */
public class Constants {
    // App
    public final static int WINDOW_HEIGHT = 800;
    public final static int WINDOW_WIDTH = 800;
    public final static int GAME_FPS = 60;

    // Player
    public final static int PLAYER_SPAWN_X = 40;
    public final static int PLAYER_SPAWN_Y = 150;
    public final static int PLAYER_WIDTH = 24;
    public final static int PLAYER_HEIGHT = 32;
    public final static int PLAYER_ACCELERATION = 700;
    public final static int MAX_PLAYER_VELOCITY = 200;
    public final static int PLAYER_FRICTION = 1000;
    public final static int PLAYER_HEALTH = 10;
    public final static int PLAYER_SPRITESHEET_X = 306;
    public final static int PLAYER_SPRITESHEET_Y = 112;
    public final static int PLAYER_SPRITESHEET_WIDTH = 16;
    public final static int PLAYER_SPRITESHEET_HEIGHT = 12;

    // Enemies
    public final static float ENEMY_SPEED_MAX = 1.2f;
    public final static float ENEMY_SPEED_MIN = 0.8f;
    public final static int NUM_ENEMIES = 5;
    public final static int ENEMY_SPEED = 100;
    public final static int ENEMY_WIDTH = 24;
    public final static int ENEMY_HEIGHT = 32;
    public final static int ENEMY_ACCELERATION = 400;
    public final static int MAX_ENEMY_VELOCITY = 200;
    public final static int ENEMY_FRICTION = 2000;
    public final static int ENEMY_HEALTH = 10;
    public final static int ENEMY_SPRITESHEET_X = 322;
    public final static int ENEMY_SPRITESHEET_Y = 112;
    public final static int ENEMY_SPRITESHEET_WIDTH = 16;
    public final static int ENEMY_SPRITESHEET_HEIGHT = 12;

    // Grid - PS: NOT CURRENTLY IN USE !!!
    public final static int NUM_ROWS = 50;
    public final static int NUM_COLS = 50;
    public final static int CELL_WIDTH = WINDOW_WIDTH / NUM_COLS;
    public final static int CELL_HEIGHT = WINDOW_HEIGHT / NUM_ROWS;

    // HUD
    public final static int HEART_WIDTH = 20;
    public final static int HEART_HEIGHT = HEART_WIDTH;
    public final static int HEART_PADDING = 25;
    public final static int HEART_X = 30;
    public final static int HEART_Y = WINDOW_HEIGHT - HEART_HEIGHT - HEART_X;

    // Game
    public final static int HIT_DAMAGE = 1;
    public final static int HIT_COOLDOWN = 1000;
    public final static int HIT_WARNING_DURATION = 150;

    // Files
    public final static String DUNGEON_SHEET_IMG = "dungeon_sheet.png";
    public final static String MAP_IMG = "maps/map2.tmx";
    public final static String HEART_IMG = "src/main/resources/HUD/heart16x16.png";
    public final static String HIT_WARNING_IMG = "src/main/resources/hitWarning.png";
    public final static String GAME_OVER_IMG = "src/main/resources/gameOver.png";

}
