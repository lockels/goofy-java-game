package inf112.skeleton.app.utils;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.model.Constants;

public class TiledObjectUtil {


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
            body.createFixture(shape, 1.0f); // You may want to set the density to 0 for static bodies
            shape.dispose();
        }
    }
	private static Shape createPolyLine(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i * 2] / Constants.PPM,
                                           vertices[i * 2 + 1] / Constants.PPM);
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
            worldVertices[i] = new Vector2(vertices[i * 2] / Constants.PPM, vertices[i * 2 + 1] / Constants.PPM);
        }

        shape.set(worldVertices);

        return shape;
    }

}
