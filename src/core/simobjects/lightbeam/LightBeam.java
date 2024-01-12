package core.simobjects.lightbeam;

import java.util.ArrayList;

import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simscreens.descriptors.SimulationScreen;

public class LightBeam implements UIElement {
    
    private ArrayList<LightBeamSegment> segments;
    private Vector2 startingPoint;

    public LightBeam(Vector2 startingPoint) {
        segments = new ArrayList<LightBeamSegment>();
        this.startingPoint = startingPoint;
    }

    public LightBeam(Vector2 startingPoint, ArrayList<LightBeamSegment> segments) {
        this.segments = segments;
        this.startingPoint = startingPoint;
    }

    public void addSegment(LightBeamSegment segment) {
        segments.add(segment);
    }
    public void addSegment(Vector2 endingPoint) {

        Vector2 startingPoint;
        if(segments.size() == 0) {
            startingPoint = this.startingPoint;
        } else {
            startingPoint = lastSegment().getEndingPoint();
        } 
        LightBeamSegment segment = new LightBeamSegment(startingPoint, endingPoint);
        segments.add(segment);
    }
    public void addSegment(double theta) {
        Vector2 startingPoint;
        if(segments.size() == 0) {
            startingPoint = this.startingPoint;
        } else {
            startingPoint = lastSegment().getEndingPoint();
        } 
        LightBeamSegment segment = new LightBeamSegment(startingPoint, theta);
        segments.add(segment);
    }

    public LightBeamSegment lastSegment() {
        return segments.get(segments.size()-1);
    }

    public static Vector2 findImagePoint(LightBeam beam1, LightBeam beam2) {
        Vector2 realIntersection = LightBeamSegment.intersection(beam1.lastSegment(), beam2.lastSegment(), true);
        if(realIntersection != null) {
            return realIntersection;
        } else {
            return null;
        }
    }

    public void render() {
        render(SimulationScreen.BEGX_DEF, SimulationScreen.BEGY_DEF);
    }

    public void render(int xAbs, int yAbs) {
        for(int i = 0; i < segments.size(); i++) {
            segments.get(i).render(xAbs, yAbs);
        }
    }

}
