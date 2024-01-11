package core.simscreens.editors;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;

public class Updater extends Screen implements UIElement {
    
    private static final int BEGX_DEF = 220;
    private static final int BEGY_DEF = 500;
    private static final int WIDTH_DEF = 690;  
    private static final int HEIGHT_DEF = 180;

    // Atributos
    private ObjectToRender objectSelected;
    private boolean displayUpdaterDescription;

    public Updater() {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF);
    }

    public Updater(int width, int height, int begX, int begY) {
        super(width, height, begX, begY);
        displayUpdaterDescription = true;
    }

    public void setObjectSelected(ObjectToRender objectSelected) {
        //System.out.println("Objeto alterado no updater");
        this.objectSelected = objectSelected;
    }

    void updateParameters() {
        // Atualiza os parâmetros do objectSelected
    }

    public void render() {

    }

}
