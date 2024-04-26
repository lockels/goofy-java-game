package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.entities.*;
import inf112.skeleton.app.model.entities.weapons.Weapon;

public class B2dContactListener implements ContactListener {

    public B2dContactListener() {}

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();
        weaponAndEnemyContact(fA, fB);
    }

    @Override
    public void endContact(Contact contact) { }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {}

    private void weaponAndEnemyContact(Fixture fA, Fixture fB){
        if (isWeaponContact(fA, fB) && (isEnemyContact(fA, fB))) {
            Weapon weapon;
            Enemy enemy;
            if (fA.getUserData() instanceof Weapon) { weapon = (Weapon) fA.getUserData(); enemy = (Enemy) fB.getUserData(); }
            else                                    { weapon = (Weapon) fB.getUserData(); enemy = (Enemy) fA.getUserData(); }
            enemy.hit(weapon.getDmg(), weapon.getKnockback(), weapon.getAngle());
        }
    }

    private boolean isWeaponContact(Fixture fA, Fixture fB) {
        return((fA.getUserData() instanceof Weapon)||(fB.getUserData() instanceof Weapon));
    }

    private boolean isEnemyContact(Fixture fA, Fixture fB) {
        return((fA.getUserData() instanceof Enemy)||(fB.getUserData() instanceof Enemy));
    }

}
