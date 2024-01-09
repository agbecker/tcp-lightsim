package core.simscreens.descriptors;

import java.util.ArrayList;

import com.raylib.java.raymath.Vector2;

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

    public SimulationScreen(ArrayList<ObjectToRender> objectsToRender) {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF, objectsToRender);
    }

    public SimulationScreen(int width, int height, int begX, int begY, ArrayList<ObjectToRender> objectsToRender) {
        super(width, height, begX, begY);
        this.objectsToRender = objectsToRender;
        axisHeight = super.getHeight()/2;
    }

    public int getAxisHeight() {
        return axisHeight;
    }

    public void addObject(ObjectToRender object) {
        objectsToRender.add(object);
    } 

    public void renderObjects() {
        if(objectsToRender != null) {
            for(ObjectToRender object : objectsToRender) {
                object.render();
            }
        }
    }

    public void unloadTextures() {
        if(objectsToRender != null) {
            for(ObjectToRender object : objectsToRender) {
                object.unloadTexture();
            }
        }
    }

    public void render() {
        render(super.getBegX(), super.getBegY());
    }
    public void render(int xAbs, int yAbs) {
        rlj.shapes.DrawRectangle(xAbs, yAbs, super.getWidth(), super.getHeight(), UIElement.DARK_BLUE);
        rlj.shapes.DrawRectangleLines(xAbs, yAbs, super.getWidth(), super.getHeight(), UIElement.WHITE);
        rlj.shapes.DrawLineEx(new Vector2(xAbs, yAbs+axisHeight), new Vector2(xAbs+super.getWidth(), yAbs+axisHeight), 1, UIElement.WHITE);
        renderObjects();
    }

}
