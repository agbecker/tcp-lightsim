package core.simobjects;

import core.simscreens.Screen;
import core.simscreens.descriptors.StatsScreen;
import core.simscreens.editors.Updater;

public abstract class ObjectToRender {

    private static Updater updater;
    private static StatsScreen statsScreen;

    private static Screen simulationScreen;
    private static boolean enableSetSimulationScreen = true;

    public ObjectToRender() {

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

}
