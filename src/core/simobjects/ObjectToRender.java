package core.simobjects;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

import core.simscreens.Screen;
import core.simscreens.descriptors.StatsScreen;
import core.simscreens.editors.Updater;

public abstract class ObjectToRender {

    private static Updater updater;
    private static StatsScreen statsScreen;

    private static Screen simulationScreen;
    private static boolean enableSetSimulationScreen = true;

    private Rectangle hitbox;
    private boolean isClickable;

    public ObjectToRender(Rectangle hitbox, boolean isClickable) {
        this.hitbox = hitbox;
        this.isClickable = isClickable;
    }

    public int getObjectWidth() {
        return (int)hitbox.width;
    }
    public int getObjectHeight() {
        return (int)hitbox.height;
    }

    public static Screen getSimulationScreen() {
        return simulationScreen;
    }

    public static void setSimulationScreen(Screen simulationScreen) {
        if(enableSetSimulationScreen) {
            ObjectToRender.simulationScreen = simulationScreen;
        }
    }
    public static void setSimulationScreenPermanently(Screen simulationScreen) {
        setSimulationScreen(simulationScreen);
        enableSetSimulationScreen = false;
    }

    public static void setUpdater(Updater updater) {
        ObjectToRender.updater = updater;
    }
    public Updater getUpdater() {
        return updater;
    }

    public static void setStatsScreen(StatsScreen statsScreen) {
        ObjectToRender.statsScreen = statsScreen;
    }
    public StatsScreen getStatsScreen() {
        return statsScreen;
    }

    public void toggleUpdate() {
        // Chama setObjectSelected de Updater
    }

    public void toggleStats() {
        // Chama setObjectSelected de StatsScreen
    }

    public abstract void render();

    public abstract void unloadTexture();

}
