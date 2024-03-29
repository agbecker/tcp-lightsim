package core.simobjects.opticaldevice;

import com.raylib.java.Raylib;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import core.UI.UIElement;
import core.simscreens.descriptors.SimulationScreen;

public class Lens extends OpticalDevice {

    final private static String CONVERGENT_TEXTURE = "../../../../resources/textures/convergent-lens.png";
    final private static String DIVERGENT_TEXTURE = "../../../../resources/textures/divergent-lens.png";
    private Texture2D convergentTexture;
    private Texture2D divergentTexture;

    public Lens(double focus, Vector2 vertex, boolean displayFocus) {
        super(focus, vertex, displayFocus);
        this.convergentTexture = rTextures.LoadTexture(CONVERGENT_TEXTURE);
        this.divergentTexture = rTextures.LoadTexture(DIVERGENT_TEXTURE);
    }

    public void render() {
        render(SimulationScreen.BEGX_DEF, SimulationScreen.BEGY_DEF);
    }

    public void render(int xAbs, int yAbs) {
        Raylib rlj = UIElement.rlj;
        rlj.shapes.DrawRectangle(
            xAbs+(int)vertex.x-this.getWidth()/2, 
            yAbs+(int)vertex.y-this.getHeight()/2, 
            this.getWidth(), 
            this.getHeight(), 
            WHITE
        );
        
        // Se o foco é negativo, a lente é divergente, e o foco encontra-se à sua esquerda
        if(focus < 0) {
            rlj.textures.DrawTexture(divergentTexture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        } else {
            rlj.textures.DrawTexture(convergentTexture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        }
        if(displayFocus) {
            rlj.shapes.DrawCircle(xAbs+(int)vertex.x+(int)focus, yAbs+(int)vertex.y, 5, WHITE);
            rlj.text.DrawText("F", xAbs+(int)vertex.x+(int)focus-3, yAbs+(int)vertex.y + 7, 10, WHITE);
        }
    }

    public Vector2 getFocalPoint() {
        return new Vector2((float)(vertex.x+focus), (float)vertex.y);
    }

    public void unloadTexture() {
        rlj.textures.UnloadTexture(convergentTexture);
        rlj.textures.UnloadTexture(divergentTexture);
    }

}
