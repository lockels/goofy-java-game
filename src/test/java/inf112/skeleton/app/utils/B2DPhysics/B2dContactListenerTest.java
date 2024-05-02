package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.*;
import inf112.skeleton.app.model.entities.enemies.Enemy;
import inf112.skeleton.app.model.entities.weapons.Sword;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class B2dContactListenerTest {

    private B2dContactListener listener;
    private CollisionCallBack mockCallback;
    private Contact mockContact;
    private Fixture fixtureA, fixtureB;
    private Player mockPlayer;
    private Spike mockSpike;
    private Sword mockSword;
    private Enemy mockEnemy;

    @BeforeEach
    void setUp() {
        mockCallback = mock(CollisionCallBack.class);
        listener = new B2dContactListener(mockCallback);
        mockContact = mock(Contact.class);
        fixtureA = mock(Fixture.class);
        fixtureB = mock(Fixture.class);
        mockPlayer = mock(Player.class);
        mockSpike = mock(Spike.class);
        mockSword = mock(Sword.class);
        mockEnemy = mock(Enemy.class);

        when(mockContact.getFixtureA()).thenReturn(fixtureA);
        when(mockContact.getFixtureB()).thenReturn(fixtureB);
    }

    @Test
    void testPlayerAndSpikeCollision() {
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(mockSpike);

        listener.beginContact(mockContact);

        verify(mockCallback).onPlayerSpikeCollision(any(Player.class), any(Spike.class));
    }

    @Test
    void testSwordAndEnemyCollision() {
        when(fixtureA.getUserData()).thenReturn(mockSword);
        when(fixtureB.getUserData()).thenReturn(mockEnemy);

        listener.beginContact(mockContact);

        //verify(mockEnemy).hit(1, new Vector2());  // Check if the hit method was called with expected arguments
    }

    @Test
    void testCollisionCallBack() {
        when(fixtureA.getUserData()).thenReturn(mockPlayer);
        when(fixtureB.getUserData()).thenReturn(mockSpike);
    
        listener.beginContact(mockContact);
    
        verify(mockCallback, times(1)).onPlayerSpikeCollision(mockPlayer, mockSpike);
    }
    
}
