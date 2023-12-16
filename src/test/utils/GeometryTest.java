package test.utils;

import static core.utils.Geometry.*;
import org.junit.*;

import com.raylib.java.raymath.Vector2;

import static org.junit.Assert.*;

public class GeometryTest {

    @BeforeClass
    public static void start() {
        System.out.println("Tests started!");
    }

    @Test
    public void getAngleTest() {
        assertEquals(0, getAngle(new Vector2(0,0), new Vector2(1,0)), 0.1);
        assertEquals(45, getAngle(new Vector2(0,0), new Vector2(1,1)), 0.1);
        assertEquals(90, getAngle(new Vector2(0,0), new Vector2(0,1)), 0.1);
        assertEquals(135, getAngle(new Vector2(0,0), new Vector2(-1,1)), 0.1);
        assertEquals(180, getAngle(new Vector2(0,0), new Vector2(-1,0)), 0.1);
        assertEquals(225, getAngle(new Vector2(0,0), new Vector2(-1,-1)), 0.1);
        assertEquals(270, getAngle(new Vector2(0,0), new Vector2(0,-1)), 0.1);
        assertEquals(315, getAngle(new Vector2(0,0), new Vector2(1,-1)), 0.1);
    }

    @Test
    public void getSlopeTest() {
        assertEquals(0, getSlope(0), 0.1);
        assertEquals(1, getSlope(45), 0.1);
        assertEquals(1.633123935319537E16, getSlope(90), 0.1);
        assertEquals(-1, getSlope(135), 0.1);
        assertEquals(0, getSlope(180), 0.1);
        assertEquals(1, getSlope(225), 0.1);
        assertEquals(-1.633123935319537E16, getSlope(270), 0.1);
        assertEquals(-1, getSlope(315), 0.1);
    }

    @AfterClass
    public static void close() {
        System.out.println("Tests ended!");
    }

}
