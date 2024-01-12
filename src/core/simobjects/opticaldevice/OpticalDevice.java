package core.simobjects.opticaldevice;

import core.UI.UIElement;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

public abstract class OpticalDevice implements UIElement {

    final protected static int HEIGHT_DEF = 200;
    final protected static int WIDTH_DEF = 30;

    protected double focus;
    protected Vector2 vertex;
    protected boolean displayFocus;      
    protected Rectangle bounds;

    public OpticalDevice(double focus, Vector2 vertex, boolean displayFocus) {
        // O vértice do dispositivo é o seu ponto central, 
        // enquanto o vértice da bounds é o canto superior esquerdo
        this.bounds = new Rectangle(vertex.x-WIDTH_DEF/2, vertex.y-HEIGHT_DEF/2, WIDTH_DEF, HEIGHT_DEF);
        this.focus = focus;
        this.vertex = vertex;
        this.displayFocus = displayFocus;
    }

    public int getWidth() {
        return (int)bounds.width;
    }
    public int getHeight() {
        return (int)bounds.height;
    }

    public void setDisplayFocus(boolean displayFocus) {
        this.displayFocus = displayFocus;
    }

    public double getFocus() {
        return focus;
    }

    public Vector2 getVertex() {
        return vertex;
    }

    public abstract Vector2 getFocalPoint();

    public abstract void unloadTexture();

}
