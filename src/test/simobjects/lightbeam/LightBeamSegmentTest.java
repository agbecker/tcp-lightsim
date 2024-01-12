package test.simobjects.lightbeam;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.lightbeam.LightBeamSegment;
import core.simscreens.descriptors.SimulationScreen;

public class LightBeamSegmentTest {
    
    private static Raylib rlj = UIElement.rlj;

    @Test
    public void intersectionTest() {
        LightBeamSegment lref1 = new LightBeamSegment(new Vector2(300, 100), new Vector2(400, 100));
        LightBeamSegment ltst1 = new LightBeamSegment(new Vector2(200, 200), new Vector2(400, 0));
        LightBeamSegment ltst2 = new LightBeamSegment(new Vector2(300, 200), new Vector2(400, 0));
        LightBeamSegment ltst3 = new LightBeamSegment(new Vector2(400, 200), new Vector2(400, 0));
        LightBeamSegment ltst4 = new LightBeamSegment(new Vector2(500, 200), new Vector2(400, 0));
        LightBeamSegment ltst5 = new LightBeamSegment(new Vector2(300, 200), new Vector2(400, 200));
        assertEquals(300, LightBeamSegment.intersection(lref1, ltst1, false).x, 0.1);
        assertEquals(100, LightBeamSegment.intersection(lref1, ltst1, false).y, 0.1);
        assertEquals(350, LightBeamSegment.intersection(lref1, ltst2, false).x, 0.1);
        assertEquals(100, LightBeamSegment.intersection(lref1, ltst2, false).y, 0.1);
        assertEquals(400, LightBeamSegment.intersection(lref1, ltst3, false).x, 0.1);
        assertEquals(100, LightBeamSegment.intersection(lref1, ltst3, false).y, 0.1);
        assertEquals(null, LightBeamSegment.intersection(lref1, ltst4, false));
        assertEquals(450, LightBeamSegment.intersection(lref1, ltst4, true).x, 0.1);
        assertEquals(100, LightBeamSegment.intersection(lref1, ltst4, true).y, 0.1);
        assertEquals(null, LightBeamSegment.intersection(lref1, ltst5, true));
    }
    public static void main(String[] args) {

        LightBeamSegment segment1 = new LightBeamSegment(new Vector2(300, 100), new Vector2(400, 100));
        LightBeamSegment segment2 = new LightBeamSegment(new Vector2(300, 200), new Vector2(400, 50));
        LightBeamSegment segment3 = new LightBeamSegment(new Vector2(550, 150), new Vector2(450, 220));
        LightBeamSegment segment4 = new LightBeamSegment(new Vector2(550, 300), new Vector2(450, 250));

        ArrayList<LightBeamSegment> segs = new ArrayList<LightBeamSegment>();

        // 1: Ângulo entre canto superior direito e canto superior esquerdo
        segs.add(new LightBeamSegment(new Vector2(100, 200), 90));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 70));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 100));
                
        // 2: Ângulo entre canto superior esquerdo e canto inferior esquerdo
        segs.add(new LightBeamSegment(new Vector2(100, 200), 180));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 160));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 190));

        // 3: Ângulo entre canto inferior esquerdo e canto inferior direito
        segs.add(new LightBeamSegment(new Vector2(100, 200), 270));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 280));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 250));

        // 4: Ângulo entre canto inferior direito e canto superior direito
        segs.add(new LightBeamSegment(new Vector2(100, 200), 0));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 340));
        segs.add(new LightBeamSegment(new Vector2(100, 200), 10));

        while(!rlj.core.WindowShouldClose()){
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.BLUE);
            rlj.shapes.DrawRectangleLines(SimulationScreen.BEGX_DEF, SimulationScreen.BEGY_DEF, SimulationScreen.WIDTH_DEF, SimulationScreen.HEIGHT_DEF, Color.RAYWHITE);
            segment1.render();
            segment2.render();
            Vector2 intersectionPoint1 = LightBeamSegment.intersection(segment1, segment2, false);
            if(intersectionPoint1 != null) {
                rlj.shapes.DrawCircle(SimulationScreen.BEGX_DEF+(int)intersectionPoint1.x, 
                                      SimulationScreen.BEGY_DEF+(int)intersectionPoint1.y, 3, Color.RED);
            }
            segment3.render();
            segment4.render();
            Vector2 intersectionPoint2 = LightBeamSegment.intersection(segment3, segment4, true);
            if(intersectionPoint2 != null) {
                rlj.shapes.DrawCircle(SimulationScreen.BEGX_DEF+(int)intersectionPoint2.x, 
                                      SimulationScreen.BEGY_DEF+(int)intersectionPoint2.y, 3, Color.RED);
            }
            for(int i = 0; i < segs.size(); i++) {
                segs.get(i).render();
            }
            rlj.core.EndDrawing();
        }

    }

}
