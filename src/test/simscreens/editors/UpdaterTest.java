package test.simscreens.editors;

import java.util.ArrayList;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.descriptors.SimulationScreen;
import core.simscreens.descriptors.StatsScreen;
import core.simscreens.editors.Updater;

public class UpdaterTest {
    
    public static void main(String[] args) {
        SimulationScreen simulationScreen = new SimulationScreen(new ArrayList<ObjectToRender>());
        Updater updater = new Updater();
        StatsScreen statsScreen = new StatsScreen();

        ObjectToRender.setSimulationScreen(simulationScreen);
        ObjectToRender.setUpdater(updater);
        ObjectToRender.setStatsScreen(statsScreen);

        SourceObject sourceObject = new SourceObject(new Vector2(100, simulationScreen.getAxisHeight()));
        //Lens lens = new Lens(100.0, new Vector2(simulationScreen.getWidth()/2, simulationScreen.getAxisHeight()), true);
        Mirror mirror = new Mirror(-100.0, new Vector2(simulationScreen.getWidth()/2, simulationScreen.getAxisHeight()), true);

        //sourceObject.setOpticalDevice(lens);
        sourceObject.setOpticalDevice(mirror);

        simulationScreen.addObject(sourceObject);
        simulationScreen.addObject(mirror);

        SourceObject image = sourceObject.generateImage();
        if(image != null) simulationScreen.addObject(image);

        Raylib rlj = UIElement.rlj;

        //Texture2D texture = rTextures.LoadTexture("arrow.png");

        while(!rlj.core.WindowShouldClose()){
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.BLUE);
            simulationScreen.render();
            simulationScreen.renderObjects();
            //rlj.textures.DrawTexture(texture, 200, 200, Color.BLUE);
            rlj.core.EndDrawing();
        }

        simulationScreen.unloadTextures();
        //rlj.textures.UnloadTexture(texture);

    }

}