package core.simscreens.descriptors;

import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.opticaldevice.*;
import core.simobjects.sourceObject.SourceObject;

public class SimulationScreen implements UIElement {
    
    public static final int BEGX_DEF = 20;
    public static final int BEGY_DEF = 20;
    public static final int WIDTH_DEF = 750;
    public static final int HEIGHT_DEF = 450;

    private final Vector2 SOURCE_DEFAULT_POSITION = new Vector2(100, HEIGHT_DEF/2);
    private final Vector2 DEVICE_DEFAULT_POSITION = new Vector2(WIDTH_DEF/2, HEIGHT_DEF/2);
    private final double DEVICE_DEFAULT_FOCUS = -50;

    private int axisHeight;
    private SourceObject source, image;
    private OpticalDevice device;

    public SimulationScreen() {
        this.device = new Mirror(DEVICE_DEFAULT_FOCUS, DEVICE_DEFAULT_POSITION, true);
        this.source = new SourceObject(SOURCE_DEFAULT_POSITION, this.device);
        this.image = null;
        axisHeight = HEIGHT_DEF/2;
    }

    public int getAxisHeight() {
        return axisHeight;
    }

    public void renderObjects() {
        this.source.render();
        this.device.render();
        image = source.generateImage();
        if(image != null) {
            if(image.getX() > WIDTH_DEF) {
                // right-arrow
                rlj.shapes.DrawRectangle(BEGX_DEF+WIDTH_DEF-50, BEGY_DEF+50, 20, 6, WHITE);
                rlj.shapes.DrawTriangle(
                    new Vector2(BEGX_DEF+WIDTH_DEF-30, BEGY_DEF+53-8), 
                    new Vector2(BEGX_DEF+WIDTH_DEF-30, BEGY_DEF+53+8), 
                    new Vector2(BEGX_DEF+WIDTH_DEF-20, BEGY_DEF+53), 
                    WHITE
                );
            } else if (image.getX()+image.getWidth() < 0) {
                // left-arrow
                rlj.shapes.DrawRectangle(BEGX_DEF+30, BEGY_DEF+50, 20, 6, WHITE);
                rlj.shapes.DrawTriangle(
                    new Vector2(BEGX_DEF+30, BEGY_DEF+53+8),
                    new Vector2(BEGX_DEF+30, BEGY_DEF+53-8),  
                    new Vector2(BEGX_DEF+20, BEGY_DEF+53), 
                    WHITE
                );
            } else {
                this.image.render(true);
            }
        }
    }

    public void unloadTextures() {
        this.source.unloadTexture();
        this.device.unloadTexture();
        if(image != null) this.image.unloadTexture();
    }

    public void render() {
        render(BEGX_DEF, BEGY_DEF);
    }
    public void render(int xAbs, int yAbs) {
        rlj.shapes.DrawRectangle(xAbs, yAbs, WIDTH_DEF, HEIGHT_DEF, UIElement.DARK_BLUE);
        rlj.shapes.DrawRectangleLines(xAbs, yAbs, WIDTH_DEF, HEIGHT_DEF, UIElement.WHITE);
        rlj.shapes.DrawLineEx(new Vector2(xAbs, yAbs+axisHeight), new Vector2(xAbs+WIDTH_DEF, yAbs+axisHeight), 1, UIElement.WHITE);
        renderObjects();
    }

    public SourceObject getSource() {
        return source;
    }

    public SourceObject getImage() {
        return image;
    }

    public OpticalDevice getDevice() {
        return device;
    }

    public void setOpticalDevice(OpticalDevice device) {
        this.device = device;
        this.source.setOpticalDevice(device);
    }

    public OpticalDevice getOpticalDevice() {
        return this.device;
    }

}
