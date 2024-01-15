package test.simobjects.opticaldevice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.raylib.java.raymath.Vector2;

import core.simobjects.opticaldevice.Mirror;

public class MirrorTest {

    private Mirror m;

    @Before
    public void init() {
        this.m = new Mirror(0, new Vector2(0, 0), false);
    }

    @Test
    public void testDefaultFocus() {
        assertEquals(0, this.m.getFocus(), 0);
    }

    @Test
    public void testDefaultHeight() {
        assertEquals(200, this.m.getHeight());
    }

    @Test
    public void testDefaultWidth() {
        assertEquals(30, this.m.getWidth());
    }

    @Test
    public void testDefaultFocalPoint() {
        assertEquals(0, this.m.getFocalPoint().getX(), 0);
        assertEquals(0, this.m.getFocalPoint().getY(), 0);
    }

    @Test
    public void testDefaultVertex() {
        assertEquals(0, this.m.getVertex().getX(), 0);
        assertEquals(0, this.m.getVertex().getY(), 0);
    }

}
