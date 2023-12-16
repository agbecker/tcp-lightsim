package core.simobjects.lightbeam;

import java.util.ArrayList;
import static core.utils.Geometry.*;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.Raylib;

import core.simobjects.ObjectToRender;
import core.simscreens.Screen;
import core.UI.UIElement;

public class LightBeamSegment extends ObjectToRender {

    private Vector2 startingPoint, endingPoint;
    private double theta;
    private boolean isDotted;
    private boolean showArrows;

    private static Screen screen;
    private static Raylib rlj;
    
    public LightBeamSegment(Vector2 startingPoint, Vector2 endingPoint, Screen screen, Raylib rlj) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.theta = getAngle(startingPoint, endingPoint);
        LightBeamSegment.screen = screen;
        LightBeamSegment.rlj = rlj;
    }

    /*public LightBeamSegment(Vector2 startingPoint, double theta, Screen screen, Raylib rlj) {
        this.startingPoint = startingPoint;
        this.theta = theta;

        ArrayList<Double> cornerAngles = new ArrayList<Double>();
        cornerAngles.add(getAngle(startingPoint, new Vector2(screen.getWidth(), 0                 )));
        cornerAngles.add(getAngle(startingPoint, new Vector2(0                , 0                 )));
        cornerAngles.add(getAngle(startingPoint, new Vector2(0                , screen.getHeight())));
        cornerAngles.add(getAngle(startingPoint, new Vector2(screen.getWidth(), screen.getHeight())));

        if(cornerAngles.get(0) < this.theta && this.theta <= cornerAngles.get(1)) {
            double x = (startingPoint.y - 0) / getSlope(theta) + startingPoint.x;
            this.endingPoint = new Vector2((float)x, (float)0);
        } else if(cornerAngles.get(1) < this.theta && this.theta <= cornerAngles.get(2)) {
            double y = (0 - startingPoint.x)*getSlope(theta) + startingPoint.y;
            this.endingPoint = new Vector2((float)0, (float)y);
        } else if(cornerAngles.get(2) < this.theta && this.theta <= cornerAngles.get(3)) {
            double x = (screen.getHeight() - startingPoint.y) / getSlope(theta) + startingPoint.x;
            this.endingPoint = new Vector2((float)x, (float)screen.getHeight());
        } else {
            double y = (screen.getWidth() - startingPoint.x)*getSlope(theta) + startingPoint.y;
            this.endingPoint = new Vector2((float)screen.getWidth(), (float)y);
        }

        LightBeamSegment.screen = screen;
        LightBeamSegment.rlj = rlj;
    }*/

    public static Vector2 intersection(LightBeamSegment l1, LightBeamSegment l2) {
        return new Vector2();
    }

    public void render(int xAbs, int yAbs) {
        int xIni = (int)startingPoint.x + xAbs;
        int yIni = (int)startingPoint.y + yAbs;
        int xFim = (int)endingPoint.x + xAbs;
        int yFim = (int)endingPoint.y + yAbs;
        rlj.shapes.DrawLine(xIni, yIni, xFim, yFim, new Color(255,255,255,255));
    }

    public void render() {
        int xIni = (int)startingPoint.x + screen.getBegX();
        int yIni = (int)startingPoint.y + screen.getBegY();
        int xFim = (int)endingPoint.x + screen.getBegX();
        int yFim = (int)endingPoint.y + screen.getBegY();
        rlj.shapes.DrawLine(xIni, yIni, xFim, yFim, new Color(255,255,255,255));
    }

}
