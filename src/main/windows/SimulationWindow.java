package main.windows;

import com.raylib.java.Raylib;

import core.UI.UIElement;
import core.simscreens.descriptors.SimulationScreen;
import core.simscreens.descriptors.StatsScreen;
import core.simscreens.editors.Updater;

public class SimulationWindow extends Window {
    
    private Raylib rlj = UIElement.rlj;
    private SimulationScreen simScreen = new SimulationScreen(null);
    private StatsScreen statsScreen = new StatsScreen();
    private Updater updater = new Updater();
    private WindowButton menuButton;

    private void assignButtons() {
        assignButtons(new MenuWindow(super.getManager()));
    }

    private void assignButtons(Window origin) {
        menuButton = new WindowButton(
            930, 500, 250, 180, new String("Menu"),
            super.getManager(), origin
        );
    }

    public SimulationWindow() {
        super();
    }

    public void setManager(WindowManager manager) {
        super.setManager(manager);
        assignButtons();
    }

    public SimulationWindow(WindowManager manager) {
        super(manager);
        assignButtons();
    }

    public SimulationWindow(WindowManager manager, Window origin) {
        super(manager, origin);
        assignButtons(origin);
    }

    public void render() {
        rlj.text.DrawText("LightSim", UIElement.RLJ_WIDTH/2, 50, 20, UIElement.WHITE);
        simScreen.render();
        statsScreen.render();
        updater.render();
        menuButton.render();
    }

}
