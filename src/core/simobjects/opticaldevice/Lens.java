package core.simobjects.opticaldevice;

import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;

public class Lens extends OpticalDevice implements UIElement {
    
    final private static String CONVERGENT_TEXTURE = "";
    final private static String DIVERGENT_TEXTURE = "";
    private boolean isDivergent;

    public Lens(double focus, Vector2 vertex, boolean displayFocus, boolean isDivergent) {
        super(focus, vertex, displayFocus);
        this.isDivergent = isDivergent;
    }

    public void render() {

    }

    public void render(int xAbs, int yAbs) {

    }

    public void unloadTexture() {
        
    }

}
