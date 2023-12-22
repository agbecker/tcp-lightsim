package core.simscreens.descriptors;

import java.util.ArrayList;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;

public class StatsScreen extends Screen implements UIElement {
    
    private static final int BEGX_DEF = 790;
    private static final int BEGY_DEF = 20;
    private static final int WIDTH_DEF = 390;
    private static final int HEIGHT_DEF = 450;

    // Atributos
    private ObjectToRender objectSelected;
    private boolean displaySimulationStats;

    public StatsScreen() {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF);
    }

    public StatsScreen(int width, int height, int begX, int begY) {
        super(width, height, begX, begY);
        displaySimulationStats = true;
    }

    void setObjectSelected(ObjectToRender objectSelected) {
        
    }

    void updateParameters() {
        // Atualiza os parâmetros do objectSelected
    }

    public void render() {

    }

}
