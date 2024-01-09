package test.simobjects.opticaldevice;

import java.util.ArrayList;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.opticaldevice.Mirror;
import core.simscreens.descriptors.SimulationScreen;

public class MirrorTest {
    public static void main(String[] args) {
        SimulationScreen simulationScreen = new SimulationScreen(new ArrayList<ObjectToRender>());
        ObjectToRender.setSimulationScreen(simulationScreen);

        Mirror mirror = new Mirror(50.0, new Vector2(simulationScreen.getWidth()/2, simulationScreen.getAxisHeight()), true);
        simulationScreen.addObject(mirror);

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
