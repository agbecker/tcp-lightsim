package core.utils;

public class Clamp {
    // "Clamps" the value x to the min and max values
    public static int clamp(int min, int x, int max) {
        return Math.max(min, Math.min(x, max));
    }
}
