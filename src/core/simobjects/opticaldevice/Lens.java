package core.simobjects.opticaldevice;

import com.raylib.java.raymath.Vector2;

public class Lens extends OpticalDevice {
    
    final private static String CONVERGENT_TEXTURE = "";
    final private static String DIVERGENT_TEXTURE = "";
    private boolean isDivergent;

    public Lens(double focus, Vector2 vertex, boolean displayFocus, boolean isDivergent) {
        super(focus, vertex, displayFocus);
        this.isDivergent = isDivergent;
    }

    public void render(int xAbs, int yAbs) {

    }

}
