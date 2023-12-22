package core.UI;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

public interface UIElement {
    public final Color LIGHT_PURPLE = new Color(102,44,246,255);
    public final Color DARK_PURPLE = new Color(61,7,194,255);
    public final Color TRACK_BLUE = new Color(0,116,203,255);
    public final static Color MEDIUM_BLUE = new Color(0, 146, 255, 255);
    public final static Color DARK_BLUE = new Color(0, 116, 203, 255);
    public final static Color LIGHT_BLUE = new Color(128, 200, 255, 255);
    public final static Color BG_BLUE = new Color(14, 137, 230, 255);
    public final Color WHITE = Color.RAYWHITE;

    public final Raylib rlj = new Raylib(1200, 700, "LightSim");
    public abstract void render();
}
