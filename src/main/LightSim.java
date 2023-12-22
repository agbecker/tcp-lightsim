package main;

import com.raylib.java.Raylib;

import core.UI.UIElement;
import main.windows.MenuWindow;
import main.windows.WindowManager;

public class LightSim {
    
    public static void main(String[] args) {
        MenuWindow menu = new MenuWindow();
        WindowManager manager = new WindowManager(menu);
        menu.setManager(manager);
        
        Raylib rlj = UIElement.rlj;
        while(!rlj.core.WindowShouldClose()) {
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(UIElement.BG_BLUE);
            manager.showWindow();
            rlj.core.EndDrawing();
        }
    }

}
