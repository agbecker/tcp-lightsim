package userInterface;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.ray.Ray;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.*;

import static com.raylib.java.core.input.Mouse.MouseButton.MOUSE_BUTTON_LEFT;

import com.raylib.java.Raylib;
import com.raylib.java.text.rText;
import com.raylib.java.text.rText.FontType;
import com.raylib.java.text.Font;
//import com.raylib.java.Raylib;

public abstract class Button {
    protected int begX, begY, width, height;
    protected String label;
    protected Texture2D texture;
    protected boolean isBeingPressed;
    protected static Raylib rlj;

    protected Rectangle rect, shadow;

    public final static Color MEDIUM_BLUE = new Color(0, 146, 255, 255);
    public final static Color DARK_BLUE = new Color(0, 116, 203, 255);
    public final static Color LIGHT_BLUE = new Color(128, 200, 255, 255);
    public final static Color BG_BLUE = new Color(14, 137, 230, 255);
    protected final static int SHADOW_OFFSET = 5;
    protected final static int BORDER_OFFSET = 5;
    protected final static int FONT_SIZE = 30;


    public Button(int x, int y, int width, int height, String label, Raylib rlj) {
        this.begX = x;
        this.begY = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.texture = null;
        this.rlj = rlj;

        this.rect = new Rectangle(x-SHADOW_OFFSET, y-SHADOW_OFFSET, width, height);
        this.shadow = new Rectangle(x, y, width, height);
    }

    public Button(int x, int y, int width, int height, Texture2D texture, Raylib rlj) {
        this.begX = x;
        this.begY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.label = null;
        this.rlj = rlj;

        this.rect = new Rectangle(x-SHADOW_OFFSET, y-SHADOW_OFFSET, width, height);
        this.shadow = new Rectangle(x, y, width, height);
    }

    public void setIsBeingPressed(boolean pressed){
        this.isBeingPressed = pressed;
    }

    public void checkIsBeingPressed() {
        Vector2 cursor = rCore.GetMousePosition();

        // Cria "hitbox" para determinar se o cursor está sobre o botão
        Rectangle button;
        if(!isBeingPressed) {
            button = new Rectangle(rect.getX(), rect.getY(), width+SHADOW_OFFSET, height+SHADOW_OFFSET);
        }

        else {
            button = new Rectangle(shadow.getX(), shadow.getY(), width, height);
        }

        // Se usuário não estiver interagindo
        if(!isBeingPressed && !rCore.IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            return;
        }

        // Se o usuário clicar no botão
        if(!isBeingPressed && rlj.shapes.CheckCollisionPointRec(cursor, button) && rCore.IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            isBeingPressed = true;
            return;
        }

        // Se o usuário soltar o botão
        if(isBeingPressed && !rCore.IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            isBeingPressed = false;
            // Se o cursor estiver sobre o botão ao soltar, ativa a função do botão
            if(rlj.shapes.CheckCollisionPointRec(cursor, button)) {
                function();
            }
        }
    }

    public void render() {
        checkIsBeingPressed();

        // Modo idle do botão
        if(!this.isBeingPressed) {

            rShapes.DrawRectangleRec(this.shadow, DARK_BLUE);
            rShapes.DrawRectangleRec(this.rect, MEDIUM_BLUE);

            if(this.label != null){ // Escreve o texto
                int labelWidth = rlj.text.MeasureText(this.label, FONT_SIZE);
                int labelX = (int) (rect.getX()+(rect.getWidth()-labelWidth)/2);
                int labelY = (int) (rect.getY()+(rect.getHeight()-FONT_SIZE)/2);

                rlj.text.DrawText(this.label, labelX, labelY, FONT_SIZE, DARK_BLUE);                
            }

            return;
        }

        // Modo pressionado
        Rectangle pressed_rect = new Rectangle(this.begX+BORDER_OFFSET, this.begY+BORDER_OFFSET, this.width-2*BORDER_OFFSET, this.height-2*BORDER_OFFSET);
        
        rShapes.DrawRectangleRec(this.shadow, DARK_BLUE);
        rShapes.DrawRectangleRec(pressed_rect, BG_BLUE);
        
        if(this.label != null){ // Escreve o texto
            int labelWidth = rlj.text.MeasureText(this.label, FONT_SIZE);
            int labelX = (int) (pressed_rect.getX()+(pressed_rect.getWidth()-labelWidth)/2);
            int labelY = (int) (pressed_rect.getY()+(pressed_rect.getHeight()-FONT_SIZE)/2);

            rlj.text.DrawText(this.label, labelX, labelY, FONT_SIZE, DARK_BLUE);                
        }
    }

    public Rectangle getShadow(){
        return this.shadow;
    }

    public abstract void function();    
    
}