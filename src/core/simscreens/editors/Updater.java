package core.simscreens.editors;

import core.UI.UIElement;

import core.simscreens.descriptors.SimulationScreen;

public class Updater implements UIElement {
    
    public static final int BEGX_DEF = 220;
    public static final int BEGY_DEF = 500;
    public static final int WIDTH_DEF = 690;  
    public static final int HEIGHT_DEF = 180;

    // Atributos
    private boolean displayUpdaterDescription;
    private SimulationScreen simScreen;

    public Updater(SimulationScreen simScreen) {
        displayUpdaterDescription = true;
        this.simScreen = simScreen;
    }

    void updateParameters() {
        // Atualiza os parâmetros do objectSelected
    }

    public void render() {

    }

}
