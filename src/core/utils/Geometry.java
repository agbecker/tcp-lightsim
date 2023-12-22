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

    public static double getDistance(Vector2 p1, Vector2 p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static double getSize(Vector2 v) {
        return Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.y, 2));
    }

}
