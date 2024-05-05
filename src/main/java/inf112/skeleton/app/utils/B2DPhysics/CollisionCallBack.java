package inf112.skeleton.app.utils.B2DPhysics;

import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.model.entities.Spike;

/**
 * Callback interface for handling collisions in the game.
 */
public interface CollisionCallBack {

    /**
     * Called when a player collides with a spike.
     *
     * @param player The player involved in the collision.
     * @param spike  The spike involved in the collision.
     */
    void onPlayerSpikeCollision(Player player, Spike spike);
}
