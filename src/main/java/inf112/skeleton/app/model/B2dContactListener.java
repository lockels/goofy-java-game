package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.*;

public class B2dContactListener implements ContactListener {
    public B2dContactListener() {}

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();
        swordAndEnemyContact(fA, fB);
    }

    @Override
    public void endContact(Contact contact) {}

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
            enemy.hit();
        }
    }

    private boolean isSwordContact(Fixture fA, Fixture fB) {
        return((fA.getUserData() instanceof Sword)||(fB.getUserData() instanceof Sword));
    }

    private boolean isEnemyContact(Fixture fA, Fixture fB) {
        return((fA.getUserData() instanceof Enemy)||(fB.getUserData() instanceof Enemy));
    }

}
