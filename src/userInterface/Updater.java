package userInterface;

import objects.ObjectToRender;

public class Updater extends Screen {
    
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