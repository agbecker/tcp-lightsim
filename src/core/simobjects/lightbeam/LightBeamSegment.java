package core.simobjects.lightbeam;

import java.util.ArrayList;
import java.lang.Math;
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
    
    private final int LINE_DASH_SIZE = 5;
    private final int ARROW_SIZE = 10;
    private final int ARROW_ANGLE = 30;
    private final int LINE_THICKNESS = 2;
    private final Color LINE_COLOR = Color.RAYWHITE;

    public LightBeamSegment(Vector2 startingPoint, Vector2 endingPoint, Screen screen, Raylib rlj) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.showArrows = true;
        this.isDotted = false;
        ObjectToRender.simulationScreen = screen;
        ObjectToRender.rlj = rlj;
        Vector2 startPointYFlipped = new Vector2(startingPoint.x, -startingPoint.y);
        Vector2 endPointYFlipped = new Vector2(endingPoint.x, -endingPoint.y);
        this.theta = getAngle(startPointYFlipped, endPointYFlipped);
    }

    public LightBeamSegment(Vector2 startingPoint, double theta, Screen screen, Raylib rlj) {
        this.startingPoint = startingPoint;
        this.theta = theta;
        this.showArrows = true;
        this.isDotted = false;

        ArrayList<Double> cornerAngles = new ArrayList<Double>();
        cornerAngles.add(getAngle(startingPoint, new Vector2(screen.getWidth(), screen.getHeight())));
        cornerAngles.add(getAngle(startingPoint, new Vector2(0                , screen.getHeight())));
        cornerAngles.add(getAngle(startingPoint, new Vector2(0                , 0                 )));
        cornerAngles.add(getAngle(startingPoint, new Vector2(screen.getWidth(), 0                 )));

        if(cornerAngles.get(0) < this.theta && this.theta <= cornerAngles.get(1)) {
            double x = (startingPoint.y - 0) / getSlope(theta) + startingPoint.x;
            this.endingPoint = new Vector2((float)x, (float)0);
        } else if(cornerAngles.get(1) < this.theta && this.theta <= cornerAngles.get(2)) {
            double y = (0 - startingPoint.x)*getSlope(180-theta) + startingPoint.y;
            this.endingPoint = new Vector2((float)0, (float)y);
        } else if(cornerAngles.get(2) < this.theta && this.theta <= cornerAngles.get(3)) {
            double x = (startingPoint.y - screen.getHeight()) * getSlope(270-theta) + startingPoint.x;
            this.endingPoint = new Vector2((float)x, (float)screen.getHeight());
        } else {
            double y = (screen.getWidth() - startingPoint.x)*getSlope(360-theta) + startingPoint.y;
            this.endingPoint = new Vector2((float)screen.getWidth(), (float)y);
        }

        ObjectToRender.simulationScreen = screen;
        ObjectToRender.rlj = rlj;
    }

    public static Vector2 intersection(LightBeamSegment l1, LightBeamSegment l2) {
        return new Vector2();
    }

    public void setIsDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    public void setShowArrows(boolean showArrows) {
        this.showArrows = showArrows;
    }

    public void render(int xAbs, int yAbs) {
        Vector2 absStart = new Vector2(xAbs + startingPoint.x, yAbs + startingPoint.y);
        Vector2 absEnd = new Vector2(xAbs + endingPoint.x, yAbs + endingPoint.y);
        rlj.shapes.DrawLineEx(absStart, absEnd, LINE_THICKNESS, LINE_COLOR);
        if(showArrows) {
            Vector2 p1 = new Vector2(ARROW_SIZE*(float)Math.cos(Math.toRadians(180-ARROW_ANGLE+theta)), 
                                     -ARROW_SIZE*(float)Math.sin(Math.toRadians(180-ARROW_ANGLE+theta)));
            Vector2 p2 = new Vector2(ARROW_SIZE*(float)Math.cos(Math.toRadians(180+ARROW_ANGLE+theta)), 
                                     -ARROW_SIZE*(float)Math.sin(Math.toRadians(180+ARROW_ANGLE+theta)));
            Vector2 middlePoint = new Vector2((absStart.x + absEnd.x)/2, (absStart.y + absEnd.y)/2);
            p1.x += middlePoint.x;
            p1.y += middlePoint.y;
            p2.x += middlePoint.x;
            p2.y += middlePoint.y;
            rlj.shapes.DrawLineEx(middlePoint, p1, LINE_THICKNESS, LINE_COLOR);
            rlj.shapes.DrawLineEx(middlePoint, p2, LINE_THICKNESS, LINE_COLOR);
        }
    }

    public void render() {
        Vector2 absStart = new Vector2(simulationScreen.getBegX() + startingPoint.x, simulationScreen.getBegY() + startingPoint.y);
        Vector2 absEnd = new Vector2(simulationScreen.getBegX() + endingPoint.x, simulationScreen.getBegY() + endingPoint.y);
        rlj.shapes.DrawLineEx(absStart, absEnd, LINE_THICKNESS, LINE_COLOR);
        if(showArrows) {
            Vector2 p1 = new Vector2(ARROW_SIZE*(float)Math.cos(Math.toRadians(180-ARROW_ANGLE+theta)), 
                                     -ARROW_SIZE*(float)Math.sin(Math.toRadians(180-ARROW_ANGLE+theta)));
            Vector2 p2 = new Vector2(ARROW_SIZE*(float)Math.cos(Math.toRadians(180+ARROW_ANGLE+theta)), 
                                     -ARROW_SIZE*(float)Math.sin(Math.toRadians(180+ARROW_ANGLE+theta)));
            Vector2 middlePoint = new Vector2((absStart.x + absEnd.x)/2, (absStart.y + absEnd.y)/2);
            p1.x += middlePoint.x;
            p1.y += middlePoint.y;
            p2.x += middlePoint.x;
            p2.y += middlePoint.y;
            rlj.shapes.DrawLineEx(middlePoint, p1, LINE_THICKNESS, LINE_COLOR);
            rlj.shapes.DrawLineEx(middlePoint, p2, LINE_THICKNESS, LINE_COLOR);
        }
    }

}
