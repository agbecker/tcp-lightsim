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
        
        LightBeamSegment segment1 = new LightBeamSegment(new Vector2(250, 25), new Vector2(350, 75), simulationScreen, rlj);
        segment1.setIsDashed(true);
        LightBeamSegment segment2 = new LightBeamSegment(new Vector2(250, 50), new Vector2(350, 100), simulationScreen, rlj);
        segment2.setShowArrows(false);

        ArrayList<LightBeamSegment> segs = new ArrayList<LightBeamSegment>();

        // 1: Ângulo entre canto superior direito e canto superior esquerdo
        segs.add(new LightBeamSegment(new Vector2(100, 200), 90, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 70, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 100, simulationScreen, rlj));
                
        // 2: Ângulo entre canto superior esquerdo e canto inferior esquerdo
        segs.add(new LightBeamSegment(new Vector2(100, 200), 180, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 160, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 190, simulationScreen, rlj));

        // 3: Ângulo entre canto inferior esquerdo e canto inferior direito
        segs.add(new LightBeamSegment(new Vector2(100, 200), 270, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 280, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 250, simulationScreen, rlj));

        // 4: Ângulo entre canto inferior direito e canto superior direito
        segs.add(new LightBeamSegment(new Vector2(100, 200), 0, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 340, simulationScreen, rlj));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 10, simulationScreen, rlj));

        while(!rlj.core.WindowShouldClose()){
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.BLUE);
            rlj.shapes.DrawRectangleLines(simulationScreen.getBegX(), simulationScreen.getBegY(), simulationScreen.getWidth(), simulationScreen.getHeight(), Color.RAYWHITE);
            segment1.render();
            segment2.render();
            for(int i = 0; i < segs.size(); i++) {
                segs.get(i).render();
            }
            rlj.core.EndDrawing();
        }

    }

}
