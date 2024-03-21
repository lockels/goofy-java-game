package inf112.skeleton.app.box2Dworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DWorld {

    // Instance of a Box2DWorld
    private World world;

    // Used to render objects which would be invisible
    private Box2DDebugRenderer debugRenderer;

    // Flag to enable/disable debug rendering
    private boolean debugEnabled;

    public Box2DWorld() {
        // Initialize world with no gravity
        world = new World(new Vector2(0, 0), true);
        // Initialize debug renderer
        debugRenderer = new Box2DDebugRenderer();
        // Initially, debug rendering is disabled
        debugEnabled = false;
    }

    public void enableDebugRendering(boolean enable) {
        debugEnabled = enable;
    }

    public void tick(OrthographicCamera cam) {
        // If debug rendering is enabled, render debug information
        if (debugEnabled) {
            debugRenderer.render(world, cam.combined);
        }
        // Step the world forward in time
        world.step(Gdx.app.getGraphics().getDeltaTime(), 6, 2);
        world.clearForces();
    }

    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }
}


