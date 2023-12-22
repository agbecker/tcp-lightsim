package core.simscreens.editors;

import java.util.ArrayList;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;
import core.simscreens.utils.ToolboxButton;

public class ToolboxScreen extends Screen implements UIElement {
    
    // Essas são as dimensões da tela que mostra os botões que inserem os
    // objetos na simulação. O botão que faz a ToolboxScreen aparecer pode/
    // deve ser implementado à parte

    private static final int BEGX_DEF = 20;
    private static final int BEGY_DEF = 500;
    private static final int WIDTH_DEF = 1160;      // OBS: notar que há overlap com 
    private static final int HEIGHT_DEF = 180;      // Updater e o botão de voltar para o menu

    ArrayList<ToolboxButton> items;

    public ToolboxScreen(ArrayList<ToolboxButton> items) {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF, items);
    }

    public ToolboxScreen(int width, int height, int begX, int begY, ArrayList<ToolboxButton> items) {
        super(width, height, begX, begY);
        this.items = items;
    }

    public ObjectToRender createObject() {
        return items.get(0).getGeneratable();
    }

    public void render() {

    }

}
