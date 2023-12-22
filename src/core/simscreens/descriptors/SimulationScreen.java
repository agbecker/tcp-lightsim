package core.simscreens.descriptors;

import java.util.ArrayList;

import core.UI.UIElement;
import core.simobjects.*;
import core.simscreens.Screen;

public class SimulationScreen extends Screen implements UIElement {
    
    private static final int BEGX_DEF = 20;
    private static final int BEGY_DEF = 20;
    private static final int WIDTH_DEF = 750;
    private static final int HEIGHT_DEF = 450;

    private int axisHeight;
    ArrayList<ObjectToRender> objectsToRender;

    public SimulationScreen(ArrayList<ObjectToRender> objectToRenders) {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF, objectToRenders);
    }

    public SimulationScreen(int width, int height, int begX, int begY, ArrayList<ObjectToRender> objectToRenders) {
        super(width, height, begX, begY);
        this.objectsToRender = objectToRenders;
        axisHeight = super.getHeight()/2;
    }

    private void renderObjects() {
        if(objectsToRender != null) {
            for(ObjectToRender object : objectsToRender) {
                object.render();
            }
        }
    }

    public void render() {
        render(BEGX_DEF, BEGY_DEF);
    }
    public void render(int xAbs, int yAbs) {
        rlj.shapes.DrawRectangleLines(xAbs, yAbs, WIDTH_DEF, HEIGHT_DEF, UIElement.WHITE);
        rlj.shapes.DrawRectangle(xAbs, yAbs, WIDTH_DEF, HEIGHT_DEF, UIElement.DARK_BLUE);
        renderObjects();
    }

}
