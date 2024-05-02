package inf112.skeleton.app.utils;

/**
 * Class which stores all constants used in-game.
 *
 */
public class Constants {
    // App
    public final static int WINDOW_HEIGHT = 800;
    public final static int WINDOW_WIDTH = 800;
    public final static int GAME_FPS = 60;
    public final static float CAMERA_ZOOM_LEVEL = 0.7f;
    public final static float CAMERA_WINDOW_WIDTH = WINDOW_WIDTH * CAMERA_ZOOM_LEVEL;
    public final static float CAMERA_WINDOW_HEIGHT = WINDOW_HEIGHT * CAMERA_ZOOM_LEVEL;
    public final static float CAMERA_OFFSET_X = CAMERA_WINDOW_WIDTH / 2;
    public final static float CAMERA_OFFSET_Y = CAMERA_WINDOW_HEIGHT / 2;

    // Player
    public final static int PLAYER_SPAWN_X = 40;
    public final static int PLAYER_SPAWN_Y = 150;
    public final static int PLAYER_WIDTH = 24;
    public final static int PLAYER_HEIGHT = 32;
    public final static int PLAYER_SPEED = 200;
    // public final static int PLAYER_FRICTION = 1000;
    public final static int PLAYER_HEALTH = 10;
    public final static int PLAYER_SPRITESHEET_X = 306;
    public final static int PLAYER_SPRITESHEET_Y = 112;
    public final static int PLAYER_SPRITESHEET_WIDTH = 16;
    public final static int PLAYER_SPRITESHEET_HEIGHT = 12;
    
    public final static float PLAYER_COLLISION_RADIUS = 45;

    // Enemies
    public final static int NUM_STARTER_ENEMIES = 2;

    // - Light
    public final static String LIGHT_ENEMY_SPRITE = "light_enemy";
    public final static int LIGHT_ENEMY_WIDTH = 20;
    public final static int LIGHT_ENEMY_HEIGHT = 20;
    public final static int LIGHT_ENEMY_SPEED = 250;
    public final static int LIGHT_ENEMY_HEALTH = 3;

    // - Medium
    public final static String MEDIUM_ENEMY_SPRITE = "medium_enemy";
    public final static int MEDIUM_ENEMY_WIDTH = 32;
    public final static int MEDIUM_ENEMY_HEIGHT = 32;
    public final static int MEDIUM_ENEMY_SPEED = 100;
    public final static int MEDIUM_ENEMY_HEALTH = 10;

    // - Heavy
    public final static String HEAVY_ENEMY_SPRITE = "heavy_enemy";
    public final static int HEAVY_ENEMY_WIDTH = 60;
    public final static int HEAVY_ENEMY_HEIGHT = 60;
    public final static int HEAVY_ENEMY_SPEED = 50;
    public final static int HEAVY_ENEMY_HEALTH = 25;

    // Sword
    // Weapons
    // Dagger
    public final static String SWORD_SPRITE = "swordSprite";
    public final static int SWORD_HEIGHT = 40;
    public final static int SWORD_WIDTH = 10;
    public final static int SWORD_Y_OFFSET = 40;
    public final static int SWORD_X_OFFSET = 0;
    public final static int SWORD_DMG = 4;
    public final static float SWORD_KNOCKBACK = 100;
    public final static float SWORD_STUN = 1;
    public final static float SWORD_COOLDOWN = 0.5f;

    // - Axe
    public final static String AXE_SPRITE = "axeSprite";
    public final static int AXE_HEIGHT = 60;
    public final static int AXE_WIDTH = 30;
    public final static int AXE_Y_OFFSET = 50;
    public final static int AXE_X_OFFSET = 0;
    public final static int AXE_DMG = 5;
    public final static float AXE_KNOCKBACK = 500;
    public final static float AXE_STUN = 1;
    public final static float AXE_COOLDOWN = 1.5f;

    // - Dagger
    public final static String DAGGER_SPRITE = "swordSprite";
    public final static int DAGGER_HEIGHT = 20;
    public final static int DAGGER_WIDTH = 10;
    public final static int DAGGER_Y_OFFSET = 25;
    public final static int DAGGER_X_OFFSET = 0;
    public final static int DAGGER_DMG = 1;
    public final static float DAGGER_KNOCKBACK = 50;
    public final static float DAGGER_STUN = 0.5f;
    public final static float DAGGER_COOLDOWN = 0.1f;

    // Grid - PS: NOT CURRENTLY IN USE !!!
    public final static int NUM_ROWS = 50;
    public final static int NUM_COLS = 50;
    public final static int CELL_WIDTH = WINDOW_WIDTH / NUM_COLS;
    public final static int CELL_HEIGHT = WINDOW_HEIGHT / NUM_ROWS;

    // HUD - All values adjusted for camera zoom level
    public final static float HEART_WIDTH = 20 * CAMERA_ZOOM_LEVEL;
    public final static float HEART_HEIGHT = HEART_WIDTH;
    public final static float HEART_PADDING = 25 * CAMERA_ZOOM_LEVEL; 
    public final static float HEART_X = 30 * CAMERA_ZOOM_LEVEL;
    public final static float HEART_Y = (WINDOW_HEIGHT - HEART_HEIGHT - HEART_X) * CAMERA_ZOOM_LEVEL;

    // Coins
    public final static int NUM_COINS = 7;
    public final static int COIN_WIDTH = 20;
    public final static int COIN_HEIGHT = 20;

    // Map / Collision
    public final static int PPM = 1;

    // Game
    public final static int HIT_DAMAGE = 1;
    public final static int SPIKE_DAMAGE = 1;
    public final static int HIT_COOLDOWN = 1000;
    public final static int HIT_WARNING_DURATION = 150;

    // Files
    public final static String DUNGEON_SHEET = "dungeon_sheet.png";
    public final static String DUNGEON_SHEET_IMG = "dungeon_sheet.png";
    public final static String MAP_IMG = "maps/map2.tmx";
    public final static String HEART_IMG = "HUD/heart16x16.png";
    public final static String HIT_WARNING = "hitWarning.png";
    public final static String GAME_OVER_IMG = "gameOver.png";
    public final static String PLAYER_SPRITE = "Main_Character";
    public final static String COIN_SPRITE = "coinSprite";
    public final static String ENEMY_SPRITE = "Enemy";

    public final static String WEAPON_SELECTION = "Weapon_Selection.png";
    public final static String WEAPON_BUTTON = "Weapon_Selection_Button.png";
    public final static String BACK_TO_GAME_BUTTON = "back_to_game_button.png";
    public final static String GREEN_SWORD = "Green_sword.png";
    public final static String RED_SWORD = "Red_sword.png";
    public final static String TREE_SWORD = "Tree_sword.png";


    //GameHelpScreen
    public final static String HELP_SCREEN_BACKGROUND = "src/main/resources/gamehelpscreen/helpbackground.png";
    public final static String EXIT_BUTTON = "src/main/resources/gamehelpscreen/exit.png";

    //GameOverScreen
    public final static String GAME_OVER_BACKGROUND = "src/main/resources/gameoverscreen/Game_over.png";
    public final static String YES_BUTTON = "src/main/resources/gameoverscreen/Yes_Button.png";
    public final static String NO_BUTTON = "src/main/resources/gameoverscreen/No_Button.png";

    //GameTitleScreen
    public final static String MENU = "src/main/resources/gametitlescreen/new_menu.png";
    public final static String PLAY_BUTTON = "src/main/resources/gametitlescreen/play.png"; 
    public final static String QUIT_BUTTON = "src/main/resources/gametitlescreen/quit.png";
    public final static String HELP_BUTTON = "src/main/resources/gametitlescreen/help.png";
}

