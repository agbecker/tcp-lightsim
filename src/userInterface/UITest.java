package userInterface;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.core.rCore;

import static com.raylib.java.core.input.Mouse.MouseButton.MOUSE_BUTTON_LEFT;

public class UITest {

    public static void main(String[] args) {
        Raylib rlj = new Raylib(800, 600, "teste");
        Button but = new Button(150, 150, 300, 60, "Simular", rlj);
        Button but2 = new Button(150, 250, 300, 60, "Como usar", rlj);
        Button[] buttons = {but, but2};

        Vector2 mousepoint = new Vector2(0,0);

        while(!rlj.core.WindowShouldClose()){

            mousepoint = rCore.GetMousePosition();

            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Button.BG_BLUE);

            for(Button b : buttons) {
                if(rlj.shapes.CheckCollisionPointRec(mousepoint, b.getShadow())
                && rCore.IsMouseButtonDown(MOUSE_BUTTON_LEFT)
                ) {
                    b.setIsBeingPressed(true);
                }

                else{
                    b.setIsBeingPressed(false);
                }

                b.render();

            }

            rlj.core.EndDrawing();
        }
        
    }
    
}
