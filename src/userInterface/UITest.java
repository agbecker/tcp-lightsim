package userInterface;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.core.rCore;

import static com.raylib.java.core.input.Mouse.MouseButton.MOUSE_BUTTON_LEFT;

public class UITest {

    public static void main(String[] args) {
        Raylib rlj = new Raylib(800, 600, "teste");
        Button but = new Button(15, 15, 300, 60, "Olá");

        Vector2 mousepoint = new Vector2(0,0);

        while(!rlj.core.WindowShouldClose()){

            mousepoint = rCore.GetMousePosition();
            //but.setIsBeingPressed(false);

            if(rlj.shapes.CheckCollisionPointRec(mousepoint, but.getShadow())
                && rlj.core.IsMouseButtonDown(MOUSE_BUTTON_LEFT)
            ) {
                but.setIsBeingPressed(true);
            }

            else{
                but.setIsBeingPressed(false);
            }

            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.RAYWHITE);
            but.render();
            rlj.core.EndDrawing();
        }
        
    }
    
}
