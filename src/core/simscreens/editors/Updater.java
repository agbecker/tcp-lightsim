package core.simscreens.editors;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;

public class Updater extends Screen implements UIElement {
    
    // Atributos
    private ObjectToRender objectSelected;
    private boolean displayUpdaterDescription;

    public Updater(int width, int height, int begX, int begY) {
        super(width, height, begX, begY);
        displayUpdaterDescription = true;
    }

    void setObjectSelected(ObjectToRender objectSelected) {

    }

    void updateParameters() {
        // Atualiza os parâmetros do objectSelected
    }

    public void render() {

    }

}
