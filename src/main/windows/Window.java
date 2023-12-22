package main.windows;

import core.UI.UIElement;

public abstract class Window implements UIElement {

    private WindowManager manager;
    public Window() {}
    public Window(WindowManager manager) {
        this.manager = manager;
    }

    public WindowManager getManager() {
        return manager;
    }
    public void setManager(WindowManager manager) {
        this.manager = manager;
    }

}