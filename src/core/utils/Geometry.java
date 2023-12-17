package core.utils;

import java.lang.Math;

import com.raylib.java.raymath.Vector2;

public class Geometry {
    
    public static double getAngle(Vector2 p1, Vector2 p2) {
        // Inversão do eixo y, já que ele cresce para baixo
        double angle = Math.toDegrees(Math.atan2((-p2.y) - (-p1.y), p2.x - p1.x));
        if(angle < 0) angle += 360;
        return angle;
    }

    public static double getSlope(double angle) {
        if(angle > 180) angle -= 360;
        return Math.tan(Math.toRadians(angle));
    }

}
