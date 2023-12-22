package test.UI;

import com.raylib.java.Raylib;

import core.UI.UIElement;

public class SimulationFrames {
    
    public static void main(String[] args ) {
        Raylib rlj = UIElement.rlj;
        while(!rlj.core.WindowShouldClose()) {
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(UIElement.BG_BLUE);
            rlj.shapes.DrawRectangleLines(20, 20, 750, 450, UIElement.WHITE);
            rlj.shapes.DrawRectangleLines(790, 20, 390, 450, UIElement.WHITE);
            rlj.shapes.DrawRectangleLines(20, 500, 180, 180, UIElement.WHITE);
            rlj.shapes.DrawRectangleLines(220, 500, 690, 180, UIElement.WHITE);
            rlj.shapes.DrawRectangleLines(930, 500, 250, 180, UIElement.WHITE);
            rlj.core.EndDrawing();
        }
    }

}
