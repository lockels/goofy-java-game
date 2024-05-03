package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import inf112.skeleton.app.model.GameLogic;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.view.HUD.HUD;

import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class GameActiveScreenTest {
    @Mock
    private SpriteBatch mockBatch;
    @Mock
    private OrthographicCamera mockCam;
    @Mock
    private GameLogic mockGameLogic;
    @Mock
    private HUD mockHud;
    @Mock
    private Box2DDebugRenderer mockDebugRenderer;
    @Mock
    private OrthogonalTiledMapRenderer mockTmr;
    @Mock
    private TiledMap mockMap;
    @Mock 
    private GameRenderer mockGame;
 

    private GameActiveScreen screen;

    @BeforeEach
    public void setUp() {
        mockBatch = mock(SpriteBatch.class);
        mockCam = mock(OrthographicCamera.class);
        mockGameLogic = mock(GameLogic.class);
        mockHud = mock(HUD.class);
        mockDebugRenderer = mock(Box2DDebugRenderer.class);
        mockTmr = mock(OrthogonalTiledMapRenderer.class);
        mockMap = mock(TiledMap.class);
        mockGame = mock(GameRenderer.class);
        
        screen = new GameActiveScreen(mockGame, mockGameLogic, mockBatch, mockCam);
    }

    @Test
    void testDispose() {
        
    }

    @Test
    void testGetCameraX() {
        
    }

    @Test
    void testGetCameraY() {
        
    }

    @Test
    void testRender() {
        
    }

    @Test
    void testShow() {
        
    }
}


