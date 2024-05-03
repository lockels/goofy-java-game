package inf112.skeleton.app.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.view.HUD.HUD;

public class GameActiveScreenTest {
    @Mock private SpriteBatch mockBatch;
    @Mock private OrthographicCamera mockCam;
    @Mock private GameLogic mockGameLogic;
    @Mock private HUD mockHud;
    @Mock private Box2DDebugRenderer mockDebugRenderer;
    @Mock private OrthogonalTiledMapRenderer mockTmr;
    @Mock private TiledMap mockMap;
    @Mock private GameRenderer mockGame;
    @Mock private Stage mockStage; // Mock Stage if necessary
    @Mock private Player mockPlayer;

    private GameActiveScreen screen;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = Gdx.gl; // Ensure that Gdx.gl20 is also mocked

        // Create a Vector3 instance for the camera position
        Vector3 mockPosition = new Vector3();

        // Mock the OrthographicCamera and its position field
        mockCam = mock(OrthographicCamera.class);
        when(mockCam.position).thenReturn(mockPosition); // Return the mock position when accessed

        screen = new GameActiveScreen(mockGame, mockGameLogic, mockBatch, mockCam);
    }

    @Test
    void testSetStage() {
        screen.setStage(mockStage); // Using the setter to set the mock stage
        Stage retrievedStage = screen.getStage(); // Retrieving the stage to check if it was set correctly

        assertEquals(mockStage, retrievedStage, "The setStage method should correctly set the stage");
    }

    @Test
    void testGetStage() {
        screen.show(); // Ensure the stage is initialized
        Stage returnedStage = screen.getStage();
        assertNotNull(returnedStage, "Stage should not be null");
        assertEquals(screen.getStage(), returnedStage, "getStage should return the correct Stage instance");
    }

    @Test
    void testDispose() {
        screen.dispose();
        verify(mockBatch, times(1)).dispose();
        verify(mockMap, times(1)).dispose();
        verify(mockTmr, times(1)).dispose();
        // Ensure that dispose is called on any other disposable resources
    }

    @Test
    void testGetCameraX() {
        // Directly set the x value of the mocked position
        mockCam.position.x = 100f;
        float x = screen.getCameraX();
        assertEquals(100f, x, "Camera X position should be correctly retrieved.");
    }

    @Test
    void testGetCameraY() {
        // Directly set the y value of the mocked position
        mockCam.position.y = 200f;
        float y = screen.getCameraY();
        assertEquals(200f, y, "Camera Y position should be correctly retrieved.");
    }

    @Test
    void testRenderGameOver() {
        when(mockGameLogic.getGameState()).thenReturn(GameState.GAME_OVER);
        screen.render(0.1f);
        verify(mockGame, times(1)).setScreen(any(GameOverScreen.class));
    }

    @Test
    void testRenderActiveGame() {
        when(mockGameLogic.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        when(mockGameLogic.getPlayer()).thenReturn(mockPlayer); // Prevent NPE from getPlayer being null
        doNothing().when(mockTmr).render(); // Prevent actual rendering
        doNothing().when(mockDebugRenderer).render(any(), any()); // Prevent actual rendering

        screen.render(0.1f);

        verify(mockTmr, times(1)).setView(mockCam);
        verify(mockTmr, times(1)).render();
        verify(mockDebugRenderer, times(1)).render(mockGameLogic.world, mockCam.combined);
        verify(mockBatch, times(1)).begin();
        verify(mockBatch, times(1)).end();
    }

    @Test
    void testShow() {
        screen.show();
        // Ensure no real OpenGL-dependent methods are called
        verify(mockGameLogic, atLeastOnce()).getPlayer();
        verify(mockCam, times(1)).setToOrtho(false, 800, 800);
        assertNotNull(screen.getStage());
        verify(mockGame, never()).setScreen(any());
    }
}
