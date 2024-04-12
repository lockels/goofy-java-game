package inf112.skeleton.app.model.box2Dworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Represents a Box2D world with physics simulation.
 */
public class Box2DWorld {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private boolean debugEnabled;

    /**
     * Constructs a new Box2D world with zero gravity.
     */
    public Box2DWorld() {
        world = new World(new Vector2(0, 0), true); // Gravity set to zero
        debugRenderer = new Box2DDebugRenderer();
        debugEnabled = false;
    }

    /**
     * Enables or disables debug rendering of the Box2D world.
     *
     * @param enable true to enable debug rendering, false to disable
     */
    public void enableDebugRendering(boolean enable) {
        debugEnabled = enable;
    }

    /**
     * Advances the physics simulation of the world by a time step.
     *
     * @param cam the camera to render the debug information with
     */
    public void tick(OrthographicCamera cam) {
        if (debugEnabled) {
            debugRenderer.render(world, cam.combined);
        }
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        world.clearForces();
    }

    /**
     * Gets the Box2D world instance.
     *
     * @return the Box2D world
     */
    public World getWorld() {
        return this.world;
    }

    /**
     * Disposes of the Box2D world and debug renderer.
     */
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }

}

