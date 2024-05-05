package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.Spike;
import static inf112.skeleton.app.utils.Constants.*;

/**
 * Utility class for creating Box2D shapes and bodies from tiled map objects.
 * Provides methods for parsing layers, creating polygons, and creating spikes.
 */
public class TiledObjectUtil {

    /**
     * Parses a tiled object layer to create static Box2D bodies in the world.
     *
     * @param world   The Box2D world in which to create the bodies.
     * @param objects The map objects representing the layer.
     */
    public static void parseTiledObjectLayer(World world, MapObjects objects) {
        for (MapObject object : objects) {
            Shape shape;
            if (object instanceof PolylineMapObject) {
                shape = createPolyLine((PolylineMapObject) object);
            } else if (object instanceof PolygonMapObject) {
                shape = createPolygon((PolygonMapObject) object);
            } else {
                continue;
            }

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bdef);
            body.createFixture(shape, 1.0f);
            shape.dispose();
        }
    }

    private static Shape createPolyLine(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for (int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i * 2] / PPM,
                                           vertices[i * 2 + 1] / PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    private static PolygonShape createPolygon(PolygonMapObject polygon) {
        float[] vertices = polygon.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];
        PolygonShape shape = new PolygonShape();
        for (int i = 0; i < worldVertices.length; ++i) {
            worldVertices[i] = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
        }
        shape.set(worldVertices);

        return shape;
    }

    /**
     * Creates spike entities from polygon map objects.
     *
     * @param world   The Box2D world in which to create the spikes.
     * @param objects The map objects representing the spikes.
     */
    public static void createSpikes(World world, MapObjects objects) {
        for (MapObject object : objects) {
            if (object instanceof PolygonMapObject) {
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                Body body = world.createBody(bodyDef);

                PolygonShape shape = TiledObjectUtil.createPolygon((PolygonMapObject) object);
                FixtureDef fixtureDef = new FixtureDef(); 
                fixtureDef.shape = shape;
                fixtureDef.isSensor = true;

                Fixture fixture = body.createFixture(fixtureDef);
                shape.dispose();

                Spike spike = new Spike(body, "", SPIKE_DAMAGE, 0, 0);
                fixture.setUserData(spike);
            }
        }
    }
}
