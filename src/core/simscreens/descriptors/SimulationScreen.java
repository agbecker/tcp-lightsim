package core.simscreens.descriptors;

import java.util.ArrayList;

import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.*;
import core.simobjects.opticaldevice.*;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.Screen;

public class SimulationScreen extends Screen {
    
    private static final int BEGX_DEF = 20;
    private static final int BEGY_DEF = 20;
    private static final int WIDTH_DEF = 750;
    private static final int HEIGHT_DEF = 450;

    private static int numSource;
    private static int numLenses;
    private static int numMirrors;
    private static int numImages;

    private int axisHeight;
    ArrayList<ObjectToRender> objectsToRender;

    private OpticalDevice device;

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

        // Atualiza contadores de instância de objeto
        numSource = 0;
        numLenses = 0;
        numMirrors = 0;
        numImages = 0;
        for(ObjectToRender o : this.objectsToRender) {
            if(o instanceof SourceObject) {
                if(((SourceObject) o).isImage())
                    numImages++;
                else
                    numSource++;
            }
                
            if(o instanceof Lens)
                numLenses++;
            if(o instanceof Mirror)
                numMirrors++;

        }
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

    public static int getNumSourceObjects() {
        return numSource;
    }

    public static int getNumImages() {
        return numImages;
    }

    public static int getNumLenses() {
        return numLenses;
    }

    public static int getNumMirrors() {
        return numMirrors;
    }

    public void setOpticalDevice(OpticalDevice device) {
        this.device = device;
    }

}
