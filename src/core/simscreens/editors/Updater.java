package core.simscreens.editors;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import core.UI.Button;
import core.UI.UIElement;
import core.simscreens.Screen;
import core.simscreens.descriptors.SimulationScreen;

public class Updater extends Screen {
    
    private static final int BEGX_DEF = 220;
    private static final int BEGY_DEF = 500;
    private static final int WIDTH_DEF = 690;  
    private static final int HEIGHT_DEF = 180;

    private static final int BORDER_WIDTH = SCREEN_BORDER_WIDTH;

    private static final int BUTTON_WIDTH = 400;
    private static final int BUTTON_HEIGHT = 60;
    private static final int BUTTON_OFFSET = 20;


    private UpdaterButton addDeviceButtons[];
    private Button testButton;

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
        String labels[] = {"Espelho Côncavo", "Espelho Convexo", "Lente Convergente", "Lente Divergente"};

        /*for(int i = 0; i<4; i++) {
            //System.out.println(i);
            //addDeviceButtons[i] = new UpdaterButton(begX+BUTTON_OFFSET, begY+(i+1)*BUTTON_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, labels[i], this);
            addDeviceButtons[i] = new UpdaterButton(begX, begY, BUTTON_WIDTH, BUTTON_HEIGHT, labels[i], this);
            System.err.println("Fez botão");
        }*/

        this.testButton = new UpdaterButton(begX, begY, BUTTON_WIDTH, BUTTON_HEIGHT, "Espelho Convexo", this);
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

        this.testButton.render();

    }

}
