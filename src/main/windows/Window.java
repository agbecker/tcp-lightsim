package main.windows;

import core.UI.UIElement;

public abstract class Window implements UIElement {

    private WindowManager manager;
    private Window origin;

    public Window() {}
    public Window(WindowManager manager) {
        this.manager = manager;
    }
    public Window(WindowManager manager, Window origin) {
        this.manager = manager;
        this.origin = origin;
    }

    public WindowManager getManager() {
        return manager;
    }
    public void setManager(WindowManager manager) {
        this.manager = manager;
    }

    public Window getOrigin() {
        return origin;
    }

}