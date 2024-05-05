package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;

import inf112.skeleton.app.model.entities.Spike;
import inf112.skeleton.app.utils.Constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TiledObjectUtilTest {

    private World world;
    private TiledMap map;
    private static final CountDownLatch latch = new CountDownLatch(1);

    @BeforeAll
    public static void setup() throws InterruptedException {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
                Gdx.gl = Mockito.mock(GL20.class);
                Gdx.gl20 = Gdx.gl;  // Set Gdx.gl20 to the same mock if needed
                latch.countDown();  // Signal that the application is ready
            }

            @Override
            public void render() {
            }

            @Override
            public void dispose() {
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }
        }, config);
        
        // Wait for the application to be ready
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new RuntimeException("Failed to initialize LibGDX environment");
        }
    }

    @BeforeAll
    public static void loadNatives() {
        new SharedLibraryLoader().load("gdx-box2d");
    }

    @BeforeEach
    public void setupMap() {
        // Load the map
        world = new World(new Vector2(0, -9.81f), true);
        map = new TmxMapLoader().load(Constants.MAP_IMG);
        assertNotNull(map, "Map should be loaded");
    }


    @Test
    public void testLoadMap() {
        map = new TmxMapLoader().load(Constants.MAP_IMG);
        assertNotNull(map, "Map should be loaded without throwing NullPointerException");
    }

    @Test
    public void testParseTiledObjectLayer() {
        // Replace "YourLayerName" with the actual name of the layer
        MapLayer layer = map.getLayers().get("collision-layer");
        assertNotNull(layer, "Layer 'YourLayerName' should exist in the map");

        // Now that we know layer is not null, we can safely access its objects
        assertNotNull(layer.getObjects(), "Objects in the layer should not be null");

        // Assuming TiledObjectUtil.parseTiledObjectLayer is a static method
        TiledObjectUtil.parseTiledObjectLayer(world, layer.getObjects());
    }

    @Test
    public void testCreateSpikes() {
        // Setup
        MapObjects objects = new MapObjects();
        float[] vertices = new float[] {0, 0, 100, 0, 100, 100, 0, 100};
        PolygonMapObject polygon = new PolygonMapObject(vertices);
        objects.add(polygon);

        // Call the method under test
        TiledObjectUtil.createSpikes(world, objects);

        // Prepare to collect bodies
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);

        // Verify that a body with a Spike user data is created
        boolean foundSpike = false;
        for (Body body : bodies) {
            for (Fixture fixture : body.getFixtureList()) {
                if (fixture.getUserData() instanceof Spike) {
                    foundSpike = true;
                    Spike spike = (Spike) fixture.getUserData();
                    assertNotNull(spike, "Spike should not be null");
                    assertEquals(Constants.SPIKE_DAMAGE, spike.getDamage(), "Damage should match the constant value");
                }
            }
        }

        assertTrue(foundSpike, "At least one Spike should have been created");
    }
}
