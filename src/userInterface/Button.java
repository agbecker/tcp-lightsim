package userInterface;
import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.*;
//import com.raylib.java.Raylib;

public class Button {
    protected int begX, begY, width, height;
    protected String label;
    protected Texture2D texture;
    protected boolean isBeingPressed;

    protected Rectangle rect, shadow;

    final static Color MEDIUM_BLUE = new Color(0, 146, 255, 255);
    final static Color DARK_BLUE = new Color(0, 116, 203, 255);
    final static Color LIGHT_BLUE = new Color(128, 200, 255, 255);
    final static int SHADOW_OFFSET = 5;
    final static int BORDER_OFFSET = 5;


    public Button(int x, int y, int width, int height, String label) {
        this.begX = x;
        this.begY = y;
        this.width = width;
        this.height = height;
        this.label = label;

        this.rect = new Rectangle(x-SHADOW_OFFSET, y-SHADOW_OFFSET, width, height);
        this.shadow = new Rectangle(x, y, width, height);
    }

    public Button(int x, int y, int width, int height, Texture2D texture) {
        this.begX = x;
        this.begY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    public void setIsBeingPressed(boolean pressed){
        this.isBeingPressed = pressed;
    }

    public void render() {

        // Modo idle do botão
        if(!this.isBeingPressed) {
            rShapes.DrawRectangleRec(this.shadow, DARK_BLUE);
            rShapes.DrawRectangleRec(this.rect, MEDIUM_BLUE);
            return;
        }

        // Modo pressionado
        Rectangle pressed_rect = new Rectangle(this.begX+BORDER_OFFSET, this.begY+BORDER_OFFSET, this.width-2*BORDER_OFFSET, this.height-2*BORDER_OFFSET);
        
        rShapes.DrawRectangleRec(this.shadow, DARK_BLUE);
        rShapes.DrawRectangleRec(pressed_rect, LIGHT_BLUE);
        
    }

    public Rectangle getShadow(){
        return this.shadow;
    }
    
    
}