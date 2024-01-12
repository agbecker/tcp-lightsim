package core.simscreens.editors;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import core.UI.Button;
import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.opticaldevice.OpticalDevice;
import core.simscreens.Screen;

public class Updater extends Screen {
    
    private static final int BEGX_DEF = 20;
    private static final int BEGY_DEF = 500;
    private static final int WIDTH_DEF = 880;  
    private static final int HEIGHT_DEF = 180;

    private static final int BORDER_WIDTH = SCREEN_BORDER_WIDTH;

    private static final int BUTTON_OFFSET = 12;

    private UpdaterButton buttonConcave, buttonConvex, buttonConvergent, buttonDivergent;

    private UpdaterButton activeButton;

    // Atributos
    private ObjectToRender objectSelected;
    private boolean displayUpdaterDescription;

    private Rectangle background, border;

    public Updater() {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF);
    }

    public Updater(int width, int height, int begX, int begY) {
        super(width, height, begX, begY);
        displayUpdaterDescription = true;

        // Cria caixa do updater
        background = new Rectangle(begX, begY, width, height);
        border = new Rectangle(begX-BORDER_WIDTH, begY-BORDER_WIDTH, width+2*BORDER_WIDTH, height+2*BORDER_WIDTH);

        
        // Cria botões de adicionar dispositivo
        this.buttonConcave = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET, "Espelho Côncavo", this);
        this.buttonConvex = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET*2 + UpdaterButton.HEIGHT, "Espelho Convexo", this);
        this.buttonConvergent = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET*3 + UpdaterButton.HEIGHT*2, "Lente Convergente", this);
        this.buttonDivergent = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET*4 + UpdaterButton.HEIGHT*3, "Lente Divergente", this);

        // Por default, usa um espelho côncavo
        this.activeButton = buttonConcave;
        setActiveButton(activeButton);
    }

    public void setObjectSelected(ObjectToRender objectSelected) {
        //System.out.println("Objeto alterado no updater");
        this.objectSelected = objectSelected;
    }

    void updateParameters() {
        // Atualiza os parâmetros do objectSelected
    }


    // Versão simples do render
    public void render() {

        rShapes.DrawRectangleRec(border, DARK_BLUE);
        rShapes.DrawRectangleRec(background, BG_BLUE);

        /*for(UpdaterButton button : this.addDeviceButtons) {
            button.render();
        }*/

        this.buttonConcave.render();
        this.buttonConvex.render();
        this.buttonConvergent.render();
        this.buttonDivergent.render();

    }

    // Temporário
    public void setOpticalDevice(OpticalDevice device) {
        this.objectSelected = device;
        
    }

    public void setActiveButton(UpdaterButton button) {
        this.activeButton.setActive(false);
        this.activeButton = button;
        this.activeButton.setActive(true);
    }

    public static int getFocus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFocus'");
    }

    public static Vector2 getDeviceVertex() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeviceVertex'");
    }

    public static boolean getDisplayFocus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDisplayFocus'");
    }

    public void setOpticalDevice(OpticalDevice device) {
        simulationscreen.setOpticalDevice(device);
    }

}
