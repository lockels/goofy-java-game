package inf112.skeleton.app.model.box2Dworld;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class Box2DHelper {

    public static void createWallsFromTiled(World world, TiledMap tiledMap, String layerName) {
        for (MapObject object : tiledMap.getLayers().get(layerName).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            createWall(world, rect);
        }
    }

    private static Body createWall(World world, Rectangle rect) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((rect.x + rect.width / 2), (rect.y + rect.height / 2));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.width / 2, rect.height / 2);

        body.createFixture(shape, 0.0f);
        shape.dispose();
        return body;
    }

    // Method to create a dynamic body for the player can be added here
}

