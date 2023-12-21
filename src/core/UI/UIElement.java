package core.UI;

import com.raylib.java.Raylib;

public interface UIElement {
    public final int SIMULATION_SCREEN_WIDTH = 800;
    public final int SIMULATION_SCREEN_HEIGHT = 600;

    public final static Raylib rlj = new Raylib(800, 600, "LightSim");
    public abstract void render();
}
