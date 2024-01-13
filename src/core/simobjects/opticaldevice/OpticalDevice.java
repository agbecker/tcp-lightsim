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
    protected Rectangle hitbox;

    public OpticalDevice(double focus, Vector2 vertex, boolean displayFocus) {
        // O vértice do dispositivo é o seu ponto central, 
        // enquanto o vértice da hitbox é o canto superior esquerdo
        this.hitbox = new Rectangle(vertex.x-WIDTH_DEF/2, vertex.y-HEIGHT_DEF/2, WIDTH_DEF, HEIGHT_DEF);
        this.focus = focus;
        this.vertex = vertex;
        this.displayFocus = displayFocus;
    }

    public int getObjectWidth() {
        return (int)hitbox.width;
    }
    public int getObjectHeight() {
        return (int)hitbox.height;
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

    public boolean showsFocus() {
        return this.displayFocus;
    }

    public abstract Vector2 getFocalPoint();

    public abstract void unloadTexture();

    public void setFocus(double focus) {
        this.focus = focus;
    }

}
