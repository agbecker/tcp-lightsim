package core.simobjects.lightbeam;

import java.util.ArrayList;
import com.raylib.java.raymath.Vector2;

import core.simobjects.ObjectToRender;

public class LightBeam extends ObjectToRender {
    
    private ArrayList<LightBeamSegment> segments;

    public LightBeam() {

    }

    public static Vector2 findImagePoint(LightBeam beam1, LightBeam beam2) {
        return new Vector2();
    }

    public void render(int xAbs, int yAbs) {

    }

}
