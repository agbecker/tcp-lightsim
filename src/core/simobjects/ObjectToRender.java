package core.simobjects;

import com.raylib.java.Raylib;

import core.simscreens.Screen;
import core.simscreens.descriptors.StatsScreen;
import core.simscreens.editors.Updater;

public abstract class ObjectToRender {

    private static Updater updater;
    private static StatsScreen statsScreen;

    protected static Screen simulationScreen;
    protected static Raylib rlj;

    public ObjectToRender() {

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

    public abstract void render(int xAbs, int yAbs);

    public void toggleUpdate() {
        // Chama setObjectSelected de Updater
    }

    public void toggleStats() {
        // Chama setObjectSelected de StatsScreen
    }

}
