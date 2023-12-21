package core.UI;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

public interface UIElement {
    public final int SIMULATION_SCREEN_WIDTH = 800;
    public final int SIMULATION_SCREEN_HEIGHT = 600;

    public final Color LIGHT_PURPLE = new Color(102,44,246,255);
    public final Color DARK_PURPLE = new Color(61,7,194,255);
    public final Color TRACK_BLUE = new Color(0,116,203,255);
    public final Color WHITE = Color.RAYWHITE;
    public final Color LIGHT_BLUE = new Color(0,146,255,255);
    public final Color PALE_BLUE = new Color(128,200,255,255);
    public final Color DARK_BLUE = new Color(0,116,203,255);

    public final Raylib rlj = new Raylib(800, 600, "LightSim");
    public abstract void render();
}
