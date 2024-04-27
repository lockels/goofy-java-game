package inf112.skeleton.app.utils.B2DPhysics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.*;


public class B2dContactListener implements ContactListener {
    private CollisionCallBack collisionCallBack;

    public B2dContactListener(CollisionCallBack collisionCallBack) {
        this.collisionCallBack = collisionCallBack;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();
        swordAndEnemyContact(fA, fB);
        playerAndSpikeContact(fA, fB);
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();
        resolvePlayerAndSpikeContact(fA, fB);
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {}

    private void swordAndEnemyContact(Fixture fA, Fixture fB){
        if (isSwordContact(fA, fB) && (isEnemyContact(fA, fB))) {
            Sword sword;
            Enemy enemy;
            if (fA.getUserData() instanceof Sword) { sword = (Sword) fA.getUserData(); enemy = (Enemy) fB.getUserData(); }
            else                                   { sword = (Sword) fB.getUserData(); enemy = (Enemy) fA.getUserData(); }
            enemy.hit(1, new Vector2());
        }
    }

    private boolean isSwordContact(Fixture fA, Fixture fB) {
        return((fA.getUserData() instanceof Sword)||(fB.getUserData() instanceof Sword));
    }

    private boolean isEnemyContact(Fixture fA, Fixture fB) {
        return((fA.getUserData() instanceof Enemy)||(fB.getUserData() instanceof Enemy));
    }

    private void playerAndSpikeContact(Fixture fA, Fixture fB) {
        if (isPlayerContact(fA, fB) && isSpikeContact(fA, fB)) {
            System.out.println("spike hit");
            Player player = (fA.getUserData() instanceof Player) ? (Player) fA.getUserData() : (Player) fB.getUserData();
            Spike spike = (fA.getUserData() instanceof Spike) ? (Spike) fA.getUserData() : (Spike) fB.getUserData();
            player.setInContactWithSpike(true);
            collisionCallBack.onPlayerSpikeCollision(player, spike); // Call the callback method
        }
    }

    private void resolvePlayerAndSpikeContact(Fixture fA, Fixture fB) {
        if (isPlayerContact(fA, fB) && isSpikeContact(fA, fB)) {
            Player player = (fA.getUserData() instanceof Player) ? (Player) fA.getUserData() : (Player) fB.getUserData();
            player.setInContactWithSpike(false);
        }
    }

    private boolean isPlayerContact(Fixture fA, Fixture fB) {
        return (fA.getUserData() instanceof Player) || (fB.getUserData() instanceof Player);
    }

    private boolean isSpikeContact(Fixture fA, Fixture fB) {
        return (fA.getUserData() instanceof Spike) || (fB.getUserData() instanceof Spike);
    }
}
