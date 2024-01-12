package core.simobjects.lightbeam;

import java.util.ArrayList;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

import core.UI.UIElement;
import core.simscreens.Screen;
import core.simscreens.descriptors.SimulationScreen;

public class LightBeam {
    
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
            // Implementação do código que encontra a imagem virtual.
            // Essa funcionalidade deve ser transferida a SourceObject para que ele consiga fazer a distinção de a imagem ser real ou virtual.
            /*LightBeamSegment beam1Virtual = new LightBeamSegment(beam1.lastSegment().getStartingPoint(), 
                                                                (beam1.lastSegment().getTheta()+180)%360, simulationScreen, rlj);
            LightBeamSegment beam2Virtual = new LightBeamSegment(beam2.lastSegment().getStartingPoint(), 
                                                                (beam2.lastSegment().getTheta()+180)%360, simulationScreen, rlj);
            Vector2 virtualIntersection = LightBeamSegment.intersection(beam1Virtual, beam2Virtual, true);
            if(virtualIntersection != null) {
                beam1Virtual.setIsDashed(true); beam1.add(beam1Virtual);
                beam2Virtual.setIsDashed(true); beam2.add(beam2Virtual);
                return virtualIntersection;
            } else {
                return null;
            }*/
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

    public void unloadTexture() {}

}
