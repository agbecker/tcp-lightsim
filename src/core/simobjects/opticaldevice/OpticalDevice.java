package core.simobjects.opticaldevice;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;

import com.raylib.java.raymath.Vector2;

public abstract class OpticalDevice extends ObjectToRender implements UIElement {

    private double focus;
    private Vector2 vertex;
    private boolean displayFocus;      

    public OpticalDevice(double focus, Vector2 vertex, boolean displayFocus) {
        this.focus = focus;
        this.vertex = vertex;
        this.displayFocus = displayFocus;
    }

    public void render() {

    }

    public Vector2 getVertex() {
        return this.vertex;
    }
    
}
