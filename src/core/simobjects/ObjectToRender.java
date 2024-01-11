package core.simobjects;

import static com.raylib.java.core.input.Mouse.MouseButton.MOUSE_BUTTON_LEFT;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;

import core.UI.UIElement;
import core.simscreens.Screen;
import core.simscreens.descriptors.StatsScreen;
import core.simscreens.editors.Updater;

public abstract class ObjectToRender implements UIElement {

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

    public void checkSelection(int xAbs, int yAbs) {
        Raylib rlj = UIElement.rlj;
        Vector2 cursor = rCore.GetMousePosition();
        Rectangle hitboxAbs = new Rectangle(hitbox.x+xAbs, hitbox.y+yAbs, hitbox.width, hitbox.height);
        if(isClickable && rlj.shapes.CheckCollisionPointRec(cursor, hitboxAbs) && rlj.core.IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            toggleUpdate(this);
            toggleStats(this);
        }
    }

    public void toggleUpdate(ObjectToRender object) {
        updater.setObjectSelected(object);
    }

    public void toggleStats(ObjectToRender object) {
        statsScreen.setObjectSelected(object);
    }

    public abstract void render();

    public abstract void unloadTexture();

}
