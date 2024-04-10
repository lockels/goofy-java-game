package inf112.skeleton.app.model.box2Dworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DWorld {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private boolean debugEnabled;

    public Box2DWorld() {
        world = new World(new Vector2(0, 0), true); // Gravity set to zero
        debugRenderer = new Box2DDebugRenderer();
        debugEnabled = false;
    }

    public void enableDebugRendering(boolean enable) {
        debugEnabled = enable;
    }

    public void tick(OrthographicCamera cam) {
        if (debugEnabled) {
            debugRenderer.render(world, cam.combined);
        }
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        world.clearForces();
    }

    public World getWorld() {
        return this.world;
    }

    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }

}
