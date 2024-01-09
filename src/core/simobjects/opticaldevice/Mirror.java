package core.simobjects.opticaldevice;

import com.raylib.java.Raylib;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simscreens.Screen;

public class Mirror extends OpticalDevice implements UIElement {

    final private static String CONCAVE_TEXTURE = "../../../../resources/textures/concave-mirror.png";
    final private static String CONVEX_TEXTURE = "../../../../resources/textures/convex-mirror.png";
    private boolean isConvex;
    private Texture2D concaveTexture;
    private Texture2D convexTexture;

    public Mirror(double focus, Vector2 vertex, boolean displayFocus, boolean isConvex) {
        super(focus, vertex, displayFocus);
        this.isConvex = isConvex;
        this.concaveTexture = rTextures.LoadTexture(CONCAVE_TEXTURE);
        this.convexTexture = rTextures.LoadTexture(CONVEX_TEXTURE);
    }

    public void render() {
        Screen simulationScreen = ObjectToRender.getSimulationScreen();
        render(simulationScreen.getBegX(), simulationScreen.getBegY());
    }

    public void render(int xAbs, int yAbs) {
        Raylib rlj = UIElement.rlj;
        rlj.shapes.DrawRectangle(xAbs+(int)vertex.x-WIDTH_DEF/2, yAbs+(int)vertex.y-HEIGHT_DEF/2, WIDTH_DEF, HEIGHT_DEF, WHITE);
        if(isConvex) {
            rlj.textures.DrawTexture(convexTexture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        } else {
            rlj.textures.DrawTexture(concaveTexture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        }
        if(displayFocus) {
            int sign = isConvex ? -1 : 1;
            rlj.shapes.DrawCircle(xAbs+(int)vertex.x+sign*(int)focus, yAbs+(int)vertex.y, 5, WHITE);
            rlj.text.DrawText("F", xAbs+(int)vertex.x+sign*(int)focus-3, yAbs+(int)vertex.y + 7, 10, WHITE);
        }
    }

    public void unloadTexture() {
        rlj.textures.UnloadTexture(concaveTexture);
        rlj.textures.UnloadTexture(convexTexture);
    }

}
