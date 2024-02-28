package inf112.skeleton.app.myInput;

import com.badlogic.gdx.Input.Keys;
import inf112.skeleton.app.Player;

public class MyInputAdapter extends MyInputProcessor{
    Player player;

    public MyInputAdapter(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                player.setLeftMove(true);
                break;
            case Keys.RIGHT:
                player.setRightMove(true);
                break;
            case Keys.UP:
                player.setUpMove(true);
                break;
            case Keys.DOWN:
                player.setDownMove(true);
                break;
        }
        return true;
    }
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                player.setLeftMove(false);
                break;
            case Keys.RIGHT:
                player.setRightMove(false);
                break;
            case Keys.UP:
                player.setUpMove(false);
                break;
            case Keys.DOWN:
                player.setDownMove(false);
                break;
        }
        return true;
    }
}
