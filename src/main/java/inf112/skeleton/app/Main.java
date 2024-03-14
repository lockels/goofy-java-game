package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        // Game window
        Lwjgl3ApplicationConfiguration gameWindow = new Lwjgl3ApplicationConfiguration();
        gameWindow.setForegroundFPS(Constants.GAME_FPS);
        gameWindow.useVsync(true);
        gameWindow.setTitle("ImprovedMovementTest");
        gameWindow.setWindowedMode(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Run
        new Lwjgl3Application(new GameRenderer(GameStates.GAME_ACTIVE), gameWindow);
    }
}
