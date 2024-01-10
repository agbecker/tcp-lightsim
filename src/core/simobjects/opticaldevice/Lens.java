package core.simobjects.opticaldevice;

import com.raylib.java.Raylib;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;

public class Lens extends OpticalDevice {

    final private static String CONVERGENT_TEXTURE = "../../../../resources/convergent-lens.png";
    final private static String DIVERGENT_TEXTURE = "../../../../resources/divergent-lens.png";
    private Texture2D convergentTexture;
    private Texture2D divergentTexture;

    public Lens(double focus, Vector2 vertex, boolean displayFocus) {
        super(focus, vertex, displayFocus);
        this.convergentTexture = rTextures.LoadTexture(CONVERGENT_TEXTURE);
        this.divergentTexture = rTextures.LoadTexture(DIVERGENT_TEXTURE);
    }

    public void render() {
        Screen simulationScreen = ObjectToRender.getSimulationScreen();
        render(simulationScreen.getBegX(), simulationScreen.getBegY());
    }

    public void render(int xAbs, int yAbs) {
        Raylib rlj = UIElement.rlj;
        rlj.shapes.DrawRectangle(xAbs+(int)vertex.x-WIDTH_DEF/2, yAbs+(int)vertex.y-HEIGHT_DEF/2, WIDTH_DEF, HEIGHT_DEF, WHITE);
        
        // Se o foco é negativo, a lente é divergente, e o foco encontra-se do mesmo lado do objeto
        if(focus < 0) {
            rlj.textures.DrawTexture(divergentTexture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        } else {
            rlj.textures.DrawTexture(convergentTexture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        }
        if(displayFocus) {
            rlj.shapes.DrawCircle(xAbs+(int)vertex.x+(int)focus, yAbs+(int)vertex.y, 5, WHITE);
            rlj.text.DrawText("F", xAbs+(int)vertex.x+(int)focus-3, yAbs+(int)vertex.y + 7, 10, WHITE);
            // F: foco para objetos que se encontram à esquerda da lente
            rlj.shapes.DrawCircle(xAbs+(int)vertex.x-(int)focus, yAbs+(int)vertex.y, 5, WHITE);
            rlj.text.DrawText("F'", xAbs+(int)vertex.x-(int)focus-5, yAbs+(int)vertex.y + 7, 10, WHITE);
            // F': foco para objetos que se encontram à direita da lente
        }
    }

    public Vector2 getFocalPoint(Vector2 sourceVertex) {
        Vector2 focalPoint;
        // Accounts for objects placed on both sides of the device
        if(sourceVertex.x < vertex.x) {
            focalPoint = new Vector2((float)(vertex.x+focus), (float)vertex.y);
        } else {
            focalPoint = new Vector2((float)(vertex.x-focus), (float)vertex.y);
        }
        return focalPoint;
    }

    public void unloadTexture() {
        rlj.textures.UnloadTexture(convergentTexture);
        rlj.textures.UnloadTexture(divergentTexture);
    }

}
