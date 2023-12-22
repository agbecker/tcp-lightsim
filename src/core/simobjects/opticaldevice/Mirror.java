package core.simobjects.opticaldevice;

import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;

public class Mirror extends OpticalDevice implements UIElement {
    
    final private static String CONCAVE_TEXTURE = "";
    final private static String CONVEX_TEXTURE = "";
    private boolean isConvex;

    public Mirror(double focus, Vector2 vertex, boolean displayFocus, boolean isConvex) {
        super(focus, vertex, displayFocus);
        this.isConvex = isConvex;
    }

    public void render() {

    }

    public void render(int xAbs, int yAbs) {

    }

}