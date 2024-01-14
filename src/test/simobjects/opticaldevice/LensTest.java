package test.simobjects.opticaldevice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.raylib.java.raymath.Vector2;

import core.simobjects.opticaldevice.Lens;

public class LensTest {
    
    public static void main(String[] args) {
        Lens lt = new Lens(0, new Vector2(0, 0), false);
        
        System.out.println(lt.getVertex().getX());
        System.out.println(lt.getVertex().getY());
    }

    private Lens l;

    @Before
    public void init() {
        this.l = new Lens(0, new Vector2(0, 0), false);
    }

    @Test
    public void testDefaultFocus() {
        assertEquals(0, this.l.getFocus(), 0);
    }

    @Test
    public void testDefaultHeight() {
        assertEquals(200, this.l.getHeight());
    }

    @Test
    public void testDefaultWidth() {
        assertEquals(30, this.l.getWidth());
    }

    @Test
    public void testDefaultFocalPoint() {
        assertEquals(0, this.l.getFocalPoint().getX(), 0);
        assertEquals(0, this.l.getFocalPoint().getY(), 0);
    }

    @Test
    public void testDefaultVertex() {
        assertEquals(0, this.l.getVertex().getX(), 0);
        assertEquals(0, this.l.getVertex().getY(), 0);
    }

}
