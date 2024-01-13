package core.simscreens.editors;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import core.UI.Button;
import core.UI.Slider;
import core.simobjects.opticaldevice.*;

public class UpdaterButton extends Button {

    private Updater updater;
    private String label;
    private boolean isActive;

    public static final int WIDTH = 30, HEIGHT = 30;
    private static final int FONT_SIZE = 18;

    public UpdaterButton(int x, int y, String label, Updater updater) {
        this(x, y, WIDTH, HEIGHT, label, updater);
    }

    public UpdaterButton(int x, int y, int width, int height, String label, Updater updater) {
        super(x, y, width, height, "");
        this.updater = updater;
        this.label = label;
        this.isActive = false;
    }

    @Override
    public void render() {
        // Escreve o texto
        
        int labelX = (int) (shadow.getX()+WIDTH+10);
        int labelY = (int) (shadow.getY()+(rect.getHeight()-FONT_SIZE)/2);

        rlj.text.DrawText(this.label, labelX, labelY, FONT_SIZE, WHITE);   

        if (this.isActive) {
            Rectangle pressed_rect = new Rectangle(this.begX + BORDER_OFFSET, this.begY + BORDER_OFFSET,
                    this.width - 2 * BORDER_OFFSET, this.height - 2 * BORDER_OFFSET);

            rShapes.DrawRectangleRec(this.shadow, LIGHT_BLUE);
            rShapes.DrawRectangleRec(pressed_rect, DARK_BLUE);
            return;
        }

        checkIsBeingPressed();

        // Modo idle do botão
        if (!this.isBeingPressed) {

            rShapes.DrawRectangleRec(this.shadow, DARK_BLUE);
            rShapes.DrawRectangleRec(this.rect, MEDIUM_BLUE);
        }

        // Modo pressionado
        else {
            Rectangle pressed_rect = new Rectangle(this.begX + BORDER_OFFSET, this.begY + BORDER_OFFSET,
                    this.width - 2 * BORDER_OFFSET, this.height - 2 * BORDER_OFFSET);

            rShapes.DrawRectangleRec(this.shadow, DARK_BLUE);
            rShapes.DrawRectangleRec(pressed_rect, BG_BLUE);
        }             
    }



    @Override
    public void function() {
        OpticalDevice deviceCurrent, deviceNew;

        deviceCurrent = updater.getOpticalDevice();

        double focus = Math.abs(deviceCurrent.getFocus());
        Vector2 vertex = deviceCurrent.getVertex();
        boolean displayFocus = deviceCurrent.showsFocus();

        if(label.matches(".*(Côncavo|Divergente).*")) {
            focus = -focus;
        }

        if(label.matches(".*Espelho.*"))
            deviceNew = new Mirror(focus, vertex, displayFocus);
        else
            deviceNew = new Lens(focus, vertex, displayFocus);

        updater.setOpticalDevice(deviceNew);

        updater.setActiveButton(this);
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void adjustSliderValues(Slider slider) {
        if(label.matches(".*(Côncavo|Divergente).*")) {
            slider.setValuesSign(-1);
        }

        else {
            slider.setValuesSign(1);
        }
    }

}
