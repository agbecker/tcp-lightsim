package main.windows;

import core.UI.Button;

public class WindowButton extends Button {

    private WindowManager manager;
    private Window toWindow;

    public WindowButton(int x, int y, int width, int height, String label, WindowManager manager, Window toWindow) {
        super(x,y,width,height,label);
        this.manager = manager;
        this.toWindow = toWindow;
    }

    @Override
    public void function() {
        manager.setCurrWindow(toWindow);
    }
}
