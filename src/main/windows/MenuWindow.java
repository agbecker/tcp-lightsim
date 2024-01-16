package main.windows;

import com.raylib.java.Raylib;

import core.UI.UIElement;

public class MenuWindow extends Window {
    
    private Raylib rlj = UIElement.rlj;
    private WindowButton simButton, manualButton, aboutButton;

    private void assignButtons() {
        simButton = new WindowButton(
            UIElement.RLJ_WIDTH/2, 100, 200, 100, new String("Simular!"),
            super.getManager(), new SimulationWindow(super.getManager(), this)
        );
        manualButton = new WindowButton(
            UIElement.RLJ_WIDTH/2, 250, 200, 100, new String("Como Usar"),
            super.getManager(), new TextWindow(super.getManager(), this, "howTo")
        );
        aboutButton = new WindowButton(
            UIElement.RLJ_WIDTH/2, 400, 200, 100, new String("Sobre"),
            super.getManager(), new TextWindow(super.getManager(), this, "about")
        );
    }

    public MenuWindow() {
        super();
    }

    public void setManager(WindowManager manager) {
        super.setManager(manager);
        assignButtons();
    }

    public MenuWindow(WindowManager manager) {
        super(manager);
        assignButtons();
    }

    public void render() {
        rlj.text.DrawText("LightSim", UIElement.RLJ_WIDTH/2, 50, 20, UIElement.WHITE);
        simButton.render();
        manualButton.render();
        aboutButton.render();
    }

}
