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
    void testCreateEntityBody() {
        Vector2 position = new Vector2(100, 100);
        Vector2 offset = new Vector2(1, 1);
        float width = 50;
        float height = 50;
        boolean collisionEnabled = true;

        Body body = PhysicsFactory.createEntityBody(world, position, offset, width, height, collisionEnabled);

        //body should not be null
        assertNotNull(body);
        //Body x position should be scaled by PPM
        assertEquals(position.x / PPM, body.getPosition().x);
        //Body y position should be scaled by PPM
        assertEquals(position.y / PPM, body.getPosition().y);
        //Body should have at least one fixture
        assertFalse(body.getFixtureList().isEmpty());

        Fixture fixture = body.getFixtureList().first();

        //Fixture should not be a sensor when collisionEnabled is true
        assertFalse(fixture.isSensor());
    }
}
