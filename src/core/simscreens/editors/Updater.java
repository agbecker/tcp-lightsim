package core.simscreens.editors;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import core.UI.Button;
import core.UI.UIElement;
import core.simscreens.Screen;
import core.simscreens.descriptors.SimulationScreen;

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
    private boolean displayUpdaterDescription;
    private SimulationScreen simScreen;
    private Rectangle background, border;

    public Updater(SimulationScreen simScreen) {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF, simScreen);
    }    


    public Updater(int width, int height, int begX, int begY, SimulationScreen simScreen) {
        super(width, height, begX, begY);
        displayUpdaterDescription = true;
        this.simScreen = simScreen;

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

}
