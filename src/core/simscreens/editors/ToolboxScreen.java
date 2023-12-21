package core.simscreens.editors;

import java.util.ArrayList;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;
import core.simscreens.utils.ToolboxButton;

public class ToolboxScreen extends Screen implements UIElement {
    
    ArrayList<ToolboxButton> items;

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
