package inf112.skeleton.app.view.HUD;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Button> buttons;

    public Menu() {
        buttons = new ArrayList<>();
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void setVisible(boolean visible) {
        for (Button button : buttons) {
            button.setClicked(visible);
        }
    }
}
