package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.model.entities.Spike;
import inf112.skeleton.app.model.entities.enemies.Enemy;
<<<<<<< HEAD
import inf112.skeleton.app.model.entities.weapons.RedSword;
=======
import inf112.skeleton.app.model.entities.weapons.MetalSword;
>>>>>>> 79394bb (Fixes swordsprites)
import inf112.skeleton.app.model.entities.weapons.Weapon;
import inf112.skeleton.app.utils.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static inf112.skeleton.app.utils.Constants.METAL_STUN;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class B2dContactListenerTest {

    private B2dContactListener listener;
    private CollisionCallBack mockCallback;
    private Contact mockContact;
    private Fixture fixtureA, fixtureB;
    private Player mockPlayer;
    private Spike mockSpike;
<<<<<<< HEAD
    private RedSword mocksSword;
=======
    private MetalSword mocksSword;
>>>>>>> 79394bb (Fixes swordsprites)
    private Enemy mockEnemy;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        mockCallback = mock(CollisionCallBack.class);
        listener = new B2dContactListener(mockCallback);
        mockContact = mock(Contact.class);
        fixtureA = mock(Fixture.class);
        fixtureB = mock(Fixture.class);
        mockPlayer = mock(Player.class);
        mockSpike = mock(Spike.class);
<<<<<<< HEAD
        mocksSword = mock(RedSword.class);
=======
        mocksSword = mock(MetalSword.class);
>>>>>>> 79394bb (Fixes swordsprites)
        mockEnemy = mock(Enemy.class);

        // Setup default fixture behaviors
        when(mockContact.getFixtureA()).thenReturn(fixtureA);
        when(mockContact.getFixtureB()).thenReturn(fixtureB);
    }

    @Test
    void testPlayerAndSpikeCollision() {
        // Setup fixture user data for the test
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(mockSpike);

        listener.beginContact(mockContact);

        // Verify interaction was correctly handled
        verify(mockCallback).onPlayerSpikeCollision(eq(mockPlayer), eq(mockSpike));
    }

    @Test
    void testSwordAndEnemyCollision() {
        when(fixtureA.getUserData()).thenReturn(mocksSword);
        when(fixtureB.getUserData()).thenReturn(mockEnemy);

        listener.beginContact(mockContact);
        // verify(mockEnemy).hit(1, new Vector2()); // Check if the hit method was
        // called with expected arguments
    }

    @Test
    void testCollisionCallBack() {
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(mockSpike);

        listener.beginContact(mockContact);

        verify(mockCallback, times(1)).onPlayerSpikeCollision(mockPlayer, mockSpike);
    }

}
