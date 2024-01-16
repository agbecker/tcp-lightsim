package test.simobjects.sourceObject;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.raylib.java.raymath.Vector2;

import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
import core.simobjects.opticaldevice.OpticalDevice;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.descriptors.SimulationScreen;

public class SourceObjectTest {

    private SourceObject so1, so2;
    private OpticalDevice m1, m2, l1, l2;

    @Before
    public void init() {
        int y = new SimulationScreen().getAxisHeight();

        so1 = new SourceObject();
        so2 = new SourceObject(new Vector2(0, y));

        m1 = new Mirror(100, new Vector2(100, y), true);
        m2 = new Mirror(-100, new Vector2(250, y), true);
        l1 = new Lens(100, new Vector2(10, y), true);
        l2 = new Lens(-100, new Vector2(390, y), true);
    }

    @Test
    public void testDefaultVertexPosition() {
        assertEquals(100, this.so1.getX(), 0);
    }

    @Test
    public void testDistanceToDevice() {
        this.so1.setOpticalDevice(this.m1);
        assertEquals(0, this.so1.getDistanceToDevice(), 0);
        
        this.so2.setOpticalDevice(m2);
        assertEquals(250, this.so2.getDistanceToDevice(), 0);

        this.so1.setOpticalDevice(this.l1);
        assertEquals(-90, this.so1.getDistanceToDevice(), 0);
        
        this.so2.setOpticalDevice(l2);
        assertEquals(390, this.so2.getDistanceToDevice(), 0);
    }

    @Test
    public void testDefaultHeight() {
        assertEquals(100, this.so1.getHeight(), 0);
    }

    @Test
    public void testDefaultImage() {
        assertEquals(null, this.so1.getImage());
    }

}
