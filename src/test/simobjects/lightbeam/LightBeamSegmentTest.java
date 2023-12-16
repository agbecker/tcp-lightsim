package test.simobjects.lightbeam;

import java.util.ArrayList;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import core.simobjects.lightbeam.LightBeamSegment;
import core.simscreens.descriptors.SimulationScreen;

public class LightBeamSegmentTest {
    
    public static void main(String[] args) {
        Raylib rlj = new Raylib(800, 600, "teste");
        SimulationScreen simulationScreen = new SimulationScreen(600, 400, 100, 100, null);
        
        LightBeamSegment segment = new LightBeamSegment(new Vector2(50, 100), new Vector2(150, 250), simulationScreen, rlj);

        //ArrayList<LightBeamSegment> segs = new ArrayList<LightBeamSegment>();
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 0, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 20, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 70, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 120, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 160, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 190, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 240, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 280, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 310, simulationScreen, rlj));
        //segs.add(new LightBeamSegment(new Vector2(simulationScreen.getWidth()/2, simulationScreen.getHeight()/2), 350, simulationScreen, rlj));

        while(!rlj.core.WindowShouldClose()){
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.BLUE);
            segment.render();
            //for(int i = 0; i < segs.size(); i++) {
            //    segs.get(i).render();
            //}
            rlj.core.EndDrawing();
        }

    }

}
