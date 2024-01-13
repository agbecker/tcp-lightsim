package core.simobjects.lightbeam;

import java.util.ArrayList;
import java.lang.Math;
import static core.utils.Geometry.*;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.Raylib;

import core.UI.UIElement;
import core.simscreens.Screen;
import core.simscreens.descriptors.SimulationScreen;

public class LightBeamSegment {

    private Vector2 startingPoint, endingPoint;
    private double theta;
    private boolean isDashed;
    private boolean showArrows;
    private double segmentSize;
    
    private final int LINE_DASH_SIZE = 5;
    private final int ARROW_SIZE = 10;
    private final int ARROW_ANGLE = 30;
    private final int LINE_THICKNESS = 2;
    private final Color LINE_COLOR = UIElement.WHITE;

    public LightBeamSegment(Vector2 startingPoint, Vector2 endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.showArrows = true;
        this.isDashed = false;
        this.theta = getAngle(startingPoint, endingPoint);
        this.segmentSize = getDistance(startingPoint, endingPoint);
    }

    public LightBeamSegment(Vector2 startingPoint, double theta) {
        this.startingPoint = startingPoint;
        this.theta = theta;
        this.showArrows = true;
        this.isDashed = false;


        ArrayList<Double> cornerAngles = new ArrayList<Double>();
        cornerAngles.add(getAngle(startingPoint, new Vector2(SimulationScreen.WIDTH_DEF, 0                 )));
        cornerAngles.add(getAngle(startingPoint, new Vector2(0                , 0                 )));
        cornerAngles.add(getAngle(startingPoint, new Vector2(0                , SimulationScreen.HEIGHT_DEF)));
        cornerAngles.add(getAngle(startingPoint, new Vector2(SimulationScreen.WIDTH_DEF, SimulationScreen.HEIGHT_DEF)));

        //System.out.printf("%f %f %f %f%n", cornerAngles.get(0), cornerAngles.get(1), cornerAngles.get(2), cornerAngles.get(3));

        if(cornerAngles.get(0) < this.theta && this.theta <= cornerAngles.get(1)) {
            double x = (startingPoint.y - 0) / getSlope(theta) + startingPoint.x;
            this.endingPoint = new Vector2((float)x, (float)0);
        } else if(cornerAngles.get(1) < this.theta && this.theta <= cornerAngles.get(2)) {
            double y = (0 - startingPoint.x)*getSlope(180-theta) + startingPoint.y;
            this.endingPoint = new Vector2((float)0, (float)y);
        } else if(cornerAngles.get(2) < this.theta && this.theta <= cornerAngles.get(3)) {
            double x = (startingPoint.y - SimulationScreen.HEIGHT_DEF) * getSlope(270-theta) + startingPoint.x;
            this.endingPoint = new Vector2((float)x, (float)SimulationScreen.HEIGHT_DEF);
        } else {
            double y = (SimulationScreen.WIDTH_DEF - startingPoint.x)*getSlope(360-theta) + startingPoint.y;
            this.endingPoint = new Vector2((float)SimulationScreen.WIDTH_DEF, (float)y);
        }
        this.segmentSize = getDistance(startingPoint, endingPoint);
    }

    public Vector2 getStartingPoint() {
        return startingPoint;
    }

    public Vector2 getEndingPoint() {
        return endingPoint;
    }

    public double getSegmentSize() {
        return segmentSize;
    }

    public double getTheta() {
        return theta;
    }

    public void setIsDashed(boolean isDashed) {
        this.isDashed = isDashed;
    }

    public void setShowArrows(boolean showArrows) {
        this.showArrows = showArrows;
    }

    public static Vector2 intersection(LightBeamSegment l1, LightBeamSegment l2, boolean ignoreBounds) {
        double thetaRad = Math.toRadians(l1.getTheta());
        double thetaRadPrime = Math.toRadians(l2.getTheta());

        //System.out.printf("theta:      %f, thetaRad:      %f%n", l1.getTheta(), thetaRad);
        //System.out.printf("thetaPrime: %f, thetaRadPrime: %f%n", l2.getTheta(), thetaRadPrime);

        // r = <x0, y0> + dirMultiple * <cos(theta), sin(theta)>
        // rPrime = <x0Prime, y0Prime> + dirPrimeMultiple * <cos(thetaPrime), sin(thetaPrime)>

        double x0 = l1.getStartingPoint().x;
        double x0Prime = l2.getStartingPoint().x;
        double y0 = -l1.getStartingPoint().y;        // Invert y axis
        double y0Prime = -l2.getStartingPoint().y;

        Vector2 dir = new Vector2((float)Math.cos(thetaRad), (float)Math.sin(thetaRad));
        Vector2 dirPrime = new Vector2((float)Math.cos(thetaRadPrime), (float)Math.sin(thetaRadPrime));

        double dirMultiple = y0Prime - y0 - Math.tan(thetaRadPrime)*(x0Prime - x0);
        dirMultiple /= Math.sin(thetaRad) - Math.cos(thetaRad)*Math.tan(thetaRadPrime);

        double dirPrimeMultiple = y0Prime - y0 - Math.tan(thetaRad)*(x0Prime - x0);
        dirPrimeMultiple /= Math.cos(thetaRadPrime)*Math.tan(thetaRad) - Math.sin(thetaRadPrime);

        Vector2 dirScaled = new Vector2((float)(dir.x*dirMultiple), 
                                        (float)(dir.y*dirMultiple));
        Vector2 dirPrimeScaled = new Vector2((float)(dirPrime.x*dirPrimeMultiple), 
                                             (float)(dirPrime.y*dirPrimeMultiple));

        //System.out.printf("dir:      (%f, %f), dirScaled:      (%f, %f)%n", dir.x, dir.y, dirScaled.x, dirScaled.y);
        //System.out.printf("dirPrime: (%f, %f), dirPrimeScaled: (%f, %f)%n", dirPrime.x, dirPrime.y, dirPrimeScaled.x, dirPrimeScaled.y);

        double epsilon = 0.05;
        boolean returnIntersection = (
            (Math.abs(thetaRad - thetaRadPrime) > epsilon) &&
            (dirMultiple > -epsilon && dirPrimeMultiple > -epsilon) && 
            (ignoreBounds || (getSize(dirScaled) <= l1.getSegmentSize() && getSize(dirPrimeScaled) <= l2.getSegmentSize()))
        );
                                     

        if(returnIntersection) {
            return new Vector2((float)(x0 + dirScaled.x), -(float)(y0 + dirScaled.y));
        } else {
            return null;
        }
    }

    public void render() {
        render(SimulationScreen.BEGX_DEF, SimulationScreen.BEGY_DEF);
    }

    public void render(int xAbs, int yAbs) {
        Raylib rlj = UIElement.rlj;
        Vector2 absStart = new Vector2(xAbs + startingPoint.x, yAbs + startingPoint.y);
        Vector2 absEnd = new Vector2(xAbs + endingPoint.x, yAbs + endingPoint.y);
        if(isDashed) {
            int nReps = (int)(segmentSize/(2*LINE_DASH_SIZE));
            float deltaX = LINE_DASH_SIZE*(float)Math.cos(Math.toRadians(theta));
            float deltaY = -LINE_DASH_SIZE*(float)Math.sin(Math.toRadians(theta));
            Vector2 begDash = new Vector2(absStart.x, absStart.y);
            Vector2 endDash = new Vector2(absStart.x+deltaX, absStart.y+deltaY);
            for(int i = 0; i < nReps; i++) {
                rlj.shapes.DrawLineEx(begDash, endDash, LINE_THICKNESS, LINE_COLOR);
                begDash.x += 2*deltaX; begDash.y += 2*deltaY;
                endDash.x += 2*deltaX; endDash.y += 2*deltaY;
            }
        } else {
            rlj.shapes.DrawLineEx(absStart, absEnd, LINE_THICKNESS, LINE_COLOR);
        }
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

    public void unloadTexture() {}

    public boolean getIsDashed() {
        return this.isDashed;
    }

}
