package main.windows;

import core.UI.Button;

public class WindowButton extends Button {

    private WindowManager manager;
    private Window myWindow;

    public WindowButton(int x, int y, int width, int height, String label, WindowManager manager, Window myWindow) {
        super(x,y,width,height,label);
        this.manager = manager;
        this.myWindow = myWindow;
    }

    @Override
    public void function() {
        manager.setCurrWindow(myWindow);
    }
}
