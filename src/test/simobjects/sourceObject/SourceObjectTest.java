package test.simobjects.sourceObject;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.raylib.java.raymath.Vector2;

import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
import core.simobjects.sourceObject.SourceObject;

public class SourceObjectTest {

    private SourceObject so;

    @Before
    public void init() {
        this.so = new SourceObject();
    }

    @Test
    public void testDefaultVertexPosition() {
        assertEquals(100, this.so.getX(), 0);
    }

    @Test
    public void testDefaultDistanceToDevice() {
        this.so.setOpticalDevice(new Lens(0, new Vector2(0, 0), false));
        assertEquals(-100, this.so.getDistanceToDevice(), 0);
        this.so.setOpticalDevice(new Lens(0, new Vector2(100, 100), false));
        assertEquals(0, this.so.getDistanceToDevice(), 0);

        this.so.setOpticalDevice(new Lens(0, new Vector2(0, 0), true));
        assertEquals(-100, this.so.getDistanceToDevice(), 0);
        this.so.setOpticalDevice(new Lens(0, new Vector2(100, 100), true));
        assertEquals(0, this.so.getDistanceToDevice(), 0);

        this.so.setOpticalDevice(new Mirror(0, new Vector2(0, 0), false));
        assertEquals(-100, this.so.getDistanceToDevice(), 0);
        this.so.setOpticalDevice(new Mirror(0, new Vector2(100, 100), false));
        assertEquals(0, this.so.getDistanceToDevice(), 0);

        this.so.setOpticalDevice(new Mirror(0, new Vector2(0, 0), true));
        assertEquals(-100, this.so.getDistanceToDevice(), 0);
        this.so.setOpticalDevice(new Mirror(0, new Vector2(100, 100), true));
        assertEquals(0, this.so.getDistanceToDevice(), 0);
    }

    @Test
    public void testDefaultHeight() {
        assertEquals(100, this.so.getHeight(), 0);
    }

    @Test
    public void testDefaultImage() {
        assertEquals(null, this.so.getImage());
    }

}
