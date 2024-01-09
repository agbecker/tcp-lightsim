package core.simobjects.opticaldevice;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;

import com.raylib.java.raymath.Vector2;

public abstract class OpticalDevice extends ObjectToRender implements UIElement {

    final protected static int HEIGHT_DEF = 200;
    final protected static int WIDTH_DEF = 30;

    protected double focus;
    protected Vector2 vertex;
    protected boolean displayFocus;      

    public OpticalDevice(double focus, Vector2 vertex, boolean displayFocus) {
        this.focus = focus;
        this.vertex = vertex;
        this.displayFocus = displayFocus;
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

}
