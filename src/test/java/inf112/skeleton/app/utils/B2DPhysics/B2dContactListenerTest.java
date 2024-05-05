package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.model.entities.Spike;
import inf112.skeleton.app.model.entities.enemies.Enemy;
import inf112.skeleton.app.model.entities.weapons.DiamondSword;
import inf112.skeleton.app.model.entities.weapons.MetalSword;
import inf112.skeleton.app.model.entities.weapons.Weapon;
import inf112.skeleton.app.utils.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class B2dContactListenerTest {

    private B2dContactListener listener;
    private CollisionCallBack mockCallback;
    private Contact mockContact;
    private Fixture fixtureA, fixtureB;
    private Player mockPlayer;
    private Spike mockSpike;
    private MetalSword mocksSword;
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
        mocksSword = mock(MetalSword.class);
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

    @Test
    void testBeginContactBasic() {
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(mockSpike);
        listener.beginContact(mockContact);
        verify(mockCallback).onPlayerSpikeCollision(eq(mockPlayer), eq(mockSpike));
    }

    @Test
    void testEndContactBasic() {
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(mockSpike);
        listener.endContact(mockContact);
        verify(mockPlayer).setInContactWithSpike(false);
    }

    @Test
    void testBeginContactWithNullUserData() {
        when(fixtureA.getUserData()).thenReturn(null);
        when(fixtureB.getUserData()).thenReturn(null);
        listener.beginContact(mockContact);
        verifyNoInteractions(mockCallback);
    }

    @Test
    void testEndContactWithNullUserData() {
        when(fixtureA.getUserData()).thenReturn(null);
        when(fixtureB.getUserData()).thenReturn(null);
        listener.endContact(mockContact);
        verifyNoInteractions(mockPlayer);
    }

    @Test
    void testBeginContactWithOneNullUserData() {
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(null);

        listener.beginContact(mockContact);

        verifyNoInteractions(mockCallback);
    }

    @Test
    void testEndContactWithNonSpike() {
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(new Object());  // Non-spike object

        listener.endContact(mockContact);

        verify(mockPlayer, never()).setInContactWithSpike(false);
    }

    
}
