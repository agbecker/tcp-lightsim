package core.simobjects.lightbeam;

import java.util.ArrayList;

import com.raylib.java.raymath.Vector2;

import core.simobjects.ObjectToRender;

public class LightBeamSegment extends ObjectToRender {

    private Vector2 startingPoint, endingPoint;
    private boolean isDotted;
    private boolean showArrows;
    private double slope;
    
    public LightBeamSegment(Vector2 startingPoint, Vector2 endingPoint, boolean isDotted, boolean showArrows) {
        
    }

    public static Vector2 intersection(LightBeamSegment l1, LightBeamSegment l2) {
        return new Vector2();
    }

    public void render() {

    }

}
