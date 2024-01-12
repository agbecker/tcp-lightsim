package core.simscreens.descriptors;

import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.opticaldevice.*;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.Screen;

public class SimulationScreen extends Screen {
    
    public static final int BEGX_DEF = 20;
    public static final int BEGY_DEF = 20;
    public static final int WIDTH_DEF = 750;
    public static final int HEIGHT_DEF = 450;

    private int axisHeight;
    private SourceObject source, image;
    private OpticalDevice device;

    private final Vector2 SOURCE_DEFAULT_POSITION = new Vector2(100, HEIGHT_DEF/2);
    private final Vector2 DEVICE_DEFAULT_POSITION = new Vector2(WIDTH_DEF/2, HEIGHT_DEF/2);
    private final double DEVICE_DEFAULT_FOCUS = 100;

    public SimulationScreen() {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF);
    }

    public SimulationScreen(int width, int height, int begX, int begY) {
        super(width, height, begX, begY);
        this.source = new SourceObject(SOURCE_DEFAULT_POSITION);
        this.device = new Mirror(DEVICE_DEFAULT_FOCUS, DEVICE_DEFAULT_POSITION, true);
        this.image = null;
        axisHeight = super.getHeight()/2;
    }

    public int getAxisHeight() {
        return axisHeight;
    }

    public void renderObjects() {
        this.source.render();
        this.device.render();
        image = source.generateImage();
        if(image != null) this.image.render();
    }

    public void unloadTextures() {
        this.source.unloadTexture();
        this.device.unloadTexture();
        if(image != null) this.image.unloadTexture();
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

    public SourceObject getSource() {
        return source;
    }

    public SourceObject getImage() {
        return image;
    }

    public OpticalDevice getDevice() {
        return device;
    }

    public void setSource(SourceObject source) {
        this.source = source;
    }

    public void setDevice(OpticalDevice device) {
        this.device = device;
    }

}
