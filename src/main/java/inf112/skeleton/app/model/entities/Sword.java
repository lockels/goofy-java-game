package inf112.skeleton.app.model.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;

public class Sword extends Entity {
    int dmg = 1;
    public Sword(Body body, String textureId, String tag) {
       super(body, textureId, tag);
   }

}
