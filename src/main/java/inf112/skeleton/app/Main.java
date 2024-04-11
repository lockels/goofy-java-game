package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.skeleton.app.model.Constants;
import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.view.GameRenderer;

public class Main {
    public static void main(String[] args) {
        // Game window
        Lwjgl3ApplicationConfiguration gameWindow = new Lwjgl3ApplicationConfiguration();
        gameWindow.setForegroundFPS(Constants.GAME_FPS);
        gameWindow.useVsync(true);
        gameWindow.setTitle("ImprovedMovementTest");
        gameWindow.setWindowedMode(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Run
        GameLogic gameLogic = new GameLogic(GameState.GAME_ACTIVE);
        new Lwjgl3Application(new GameRenderer(gameLogic), gameWindow);
    }
}
