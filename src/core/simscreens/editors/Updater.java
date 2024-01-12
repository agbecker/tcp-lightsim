package core.simscreens.editors;

import core.UI.UIElement;

import core.simscreens.Screen;
import core.simscreens.descriptors.SimulationScreen;

public class Updater extends Screen implements UIElement {
    
    private static final int BEGX_DEF = 220;
    private static final int BEGY_DEF = 500;
    private static final int WIDTH_DEF = 690;  
    private static final int HEIGHT_DEF = 180;

    // Atributos
    private boolean displayUpdaterDescription;
    private SimulationScreen simScreen;

    public Updater(SimulationScreen simScreen) {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF, simScreen);
    }

    public Updater(int width, int height, int begX, int begY, SimulationScreen simScreen) {
        super(width, height, begX, begY);
        displayUpdaterDescription = true;
        this.simScreen = simScreen;
    }


    void updateParameters() {
        // Atualiza os parâmetros do objectSelected
    }

    public void render() {

    }

}
