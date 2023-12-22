package main.windows;

public class WindowManager {
    
    private Window currWindow;

    public WindowManager(Window currWindow) {
        this.currWindow = currWindow;
    }
 
    protected void setCurrWindow(Window window) {
        currWindow = window;
    }

    public void showWindow() {
        currWindow.render();
    }

}
