package test.UI;

import core.UI.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

public class UITest {

    // Descomentar caso queira testar elementos da UI

    @Test
        public void sliderLimitsTest() {
            Slider slider = new Slider(0, 10, new Vector2(50,50), 100, "Teste");

            slider.setCurrentValue(-500);
            assertEquals(0, slider.getCurrentValue(), 0.01);

            slider.setCurrentValue(2000);
            assertEquals(10, slider.getCurrentValue(), 0.01);

            slider.setCurrentValue(4.25);
            assertEquals(4.25, slider.getCurrentValue(), 0.01);
        }

    public static void main(String[] args) {
        Raylib rlj = UIElement.rlj;
        Slider slider = new Slider(-40, 28, new Vector2(400, 300), 200, "");

        

        while(!rlj.core.WindowShouldClose()){


            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.RAYWHITE);
            slider.render();

            rlj.core.EndDrawing();
        }
        
    }
    
}
