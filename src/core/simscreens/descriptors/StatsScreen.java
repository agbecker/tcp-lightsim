package core.simscreens.descriptors;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;

public class StatsScreen extends Screen implements UIElement {
    
        // Atributos
    private ObjectToRender objectSelected;
    private boolean displaySimulationStats;

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
