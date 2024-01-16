package main.windows;

public class WindowManager {
    
    private Window currWindow;

    public WindowManager(Window initialWindow) {
        this.currWindow = initialWindow;
    }

    protected void setCurrWindow(Window window) {
        currWindow = window;
    }

    public void showWindow() {
        currWindow.render();
    }

}
