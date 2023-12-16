package test.UI;

import core.UI.*;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.core.rCore;

import static com.raylib.java.core.input.Mouse.MouseButton.MOUSE_BUTTON_LEFT;

public class UITest {

    // Descomentar caso queira testar elementos da UI

    /*public static void main(String[] args) {
        Raylib rlj = new Raylib(800, 600, "teste");
        Slider slider = new Slider(-40, 28, new Vector2(400, 300), 200, rlj);

        //slider.updateKnobPosition(400);
        
        Vector2 mousepoint = new Vector2(0,0);

        while(!rlj.core.WindowShouldClose()){

            mousepoint = rCore.GetMousePosition();
            

            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.RAYWHITE);
            slider.render();

            rlj.core.EndDrawing();
        }
        
    }*/
    
}