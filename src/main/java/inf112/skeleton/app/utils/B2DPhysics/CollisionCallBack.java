package inf112.skeleton.app.utils.B2DPhysics;

import inf112.skeleton.app.model.entities.Player;
import inf112.skeleton.app.model.entities.Spike;

public interface CollisionCallBack {
    void onPlayerSpikeCollision(Player player, Spike spike);

}
