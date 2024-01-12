package test.simobjects.opticaldevice;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.opticaldevice.Lens;
import core.simscreens.descriptors.SimulationScreen;

public class LensTest {
    public static void main(String[] args) {
        SimulationScreen simulationScreen = new SimulationScreen();

        Lens lens = new Lens(50.0, new Vector2(SimulationScreen.WIDTH_DEF/2, simulationScreen.getAxisHeight()), true);
        simulationScreen.setDevice(lens);

        Raylib rlj = UIElement.rlj;

        while(!rlj.core.WindowShouldClose()){
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.BLUE);
            simulationScreen.render();
            rlj.core.EndDrawing();
        }

    }

}
