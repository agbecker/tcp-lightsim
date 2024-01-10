package test.simobjects.opticaldevice;

import java.util.ArrayList;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.opticaldevice.Lens;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.descriptors.SimulationScreen;

public class LensTest {
    public static void main(String[] args) {
        SimulationScreen simulationScreen = new SimulationScreen(new ArrayList<ObjectToRender>());
        ObjectToRender.setSimulationScreen(simulationScreen);

        Lens lens = new Lens(50.0, new Vector2(simulationScreen.getWidth()/2, simulationScreen.getAxisHeight()), true);
        simulationScreen.addObject(lens);

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
