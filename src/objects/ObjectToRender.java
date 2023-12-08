package objects;

import userInterface.*;

public abstract class ObjectToRender {

    private static Updater updater;
    private static StatsScreen statsScreen;

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

    public abstract void render();

    public void toggleUpdate() {
        // Chama setObjectSelected de Updater
    }

    public void toggleStats() {
        // Chama setObjectSelected de StatsScreen
    }

}