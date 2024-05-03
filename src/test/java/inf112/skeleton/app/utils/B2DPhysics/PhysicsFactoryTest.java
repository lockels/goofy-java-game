package inf112.skeleton.app.utils.B2DPhysics;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static inf112.skeleton.app.utils.Constants.PPM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhysicsFactoryTest {

    private World world;

    @BeforeEach
    void setUp() {
        world = new World(new Vector2(0, 0), true);
    }

    @AfterEach
    void tearDown() {
        world.dispose();
    }

    @Test
    void testCreateDynamicEntityBody() {
        Vector2 position = new Vector2(100, 100);
        float width = 50;
        float height = 50;
        boolean collisionEnabled = true;

        Body body = PhysicsFactory.createDynamicEntityBody(world, position, width, height, collisionEnabled);

        assertNotNull(body);
        assertEquals(BodyDef.BodyType.DynamicBody, body.getType());
        assertEquals(position.x / PPM, body.getPosition().x);
        assertEquals(position.y / PPM, body.getPosition().y);

        Fixture fixture = body.getFixtureList().first();
        assertNotNull(fixture);
        assertFalse(fixture.isSensor());

        // Test with collision disabled
        body = PhysicsFactory.createDynamicEntityBody(world, position, width, height, false);
        fixture = body.getFixtureList().first();
        assertTrue(fixture.isSensor());
    }

    @Test
    void testCreateStaticEntityBody() {
        Vector2 position = new Vector2(200, 200);
        float width = 30;
        float height = 40;

        Body body = PhysicsFactory.createStaticEntityBody(world, position, width, height);

        assertNotNull(body);
        assertEquals(BodyDef.BodyType.StaticBody, body.getType());
        assertEquals(position.x / PPM, body.getPosition().x);
        assertEquals(position.y / PPM, body.getPosition().y);

        Fixture fixture = body.getFixtureList().first();
        assertNotNull(fixture);
        assertTrue(fixture.isSensor());
        assertEquals(body, fixture.getUserData());
    }

    @Test
    void testCreateEntityBody() {
        Vector2 position = new Vector2(300, 300);
        Vector2 offset = new Vector2(5, 5);
        float width = 60;
        float height = 60;
        boolean collisionEnabled = true;

        Body body = PhysicsFactory.createEntityBody(world, position, offset, width, height, collisionEnabled);

        assertNotNull(body);
        assertEquals(BodyDef.BodyType.DynamicBody, body.getType());
        assertEquals(position.x / PPM, body.getPosition().x);
        assertEquals(position.y / PPM, body.getPosition().y);

        Fixture fixture = body.getFixtureList().first();
        assertNotNull(fixture);
        assertFalse(fixture.isSensor());

        // Test with collision disabled
        body = PhysicsFactory.createEntityBody(world, position, offset, width, height, false);
        fixture = body.getFixtureList().first();
        assertTrue(fixture.isSensor());
    }
}
