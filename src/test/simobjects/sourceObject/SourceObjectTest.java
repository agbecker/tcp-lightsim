package test.simobjects.sourceObject;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.descriptors.SimulationScreen;

public class SourceObjectTest {
    
    public static void main(String[] args) {
        SimulationScreen simulationScreen = new SimulationScreen();
        //Updater updater = new Updater(simulationScreen);
        //StatsScreen statsScreen = new StatsScreen(simulationScreen);

        SourceObject sourceObject = new SourceObject(new Vector2(100, simulationScreen.getAxisHeight()));
        //Lens lens = new Lens(100.0, new Vector2(SimulationScreen.WIDTH_DEF/2, simulationScreen.getAxisHeight()), true);
        Mirror mirror = new Mirror(-100.0, new Vector2(SimulationScreen.WIDTH_DEF/2, simulationScreen.getAxisHeight()), true);

        //sourceObject.setOpticalDevice(lens);
        sourceObject.setOpticalDevice(mirror);

        simulationScreen.setSource(sourceObject);
        simulationScreen.setDevice(mirror);

        Raylib rlj = UIElement.rlj;

        while(!rlj.core.WindowShouldClose()){
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.BLUE);
            simulationScreen.render();
            rlj.core.EndDrawing();
        }

        simulationScreen.unloadTextures();

    }


}
