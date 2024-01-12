package core.simscreens.editors;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import core.UI.Button;
import core.UI.Slider;
import core.UI.UIElement;
import core.simobjects.opticaldevice.OpticalDevice;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.Screen;
import core.simscreens.descriptors.SimulationScreen;

public class Updater extends Screen {
    
    private static final int BEGX_DEF = 20;
    private static final int BEGY_DEF = 500;
    private static final int WIDTH_DEF = 880;  
    private static final int HEIGHT_DEF = 180;

    private static final int BORDER_WIDTH = SCREEN_BORDER_WIDTH;
    private static final int BUTTON_OFFSET = 12;

    private static final int SLIDER_WIDTH = 240;
    private static final int SLIDER_BEGX = WIDTH_DEF*3/4;
    private static final int SLIDER_OFFSET = 30;

    private static final int MIN_SOURCE_X = 100;
    private static final int MAX_SOURCE_X = 500;
    private static final int MIN_SOURCE_HEIGHT = 50;
    private static final int MAX_SOURCE_HEIGHT = 150;
    private static final int MIN_FOCUS = 0;
    private static final int MAX_FOCUS = 200;


    private UpdaterButton buttonConcave, buttonConvex, buttonConvergent, buttonDivergent;
    private UpdaterButton activeButton;

    private Slider sliderDistance, sliderHeight, sliderFocus;

    // Atributos
    private SimulationScreen simScreen;
    private Rectangle background, border;

    public Updater(SimulationScreen simScreen) {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF, simScreen);
    }    


    public Updater(int width, int height, int begX, int begY, SimulationScreen simScreen) {
        super(width, height, begX, begY);
        this.simScreen = simScreen;

        // Cria caixa do updater
        background = new Rectangle(begX, begY, width, height);
        border = new Rectangle(begX-BORDER_WIDTH, begY-BORDER_WIDTH, width+2*BORDER_WIDTH, height+2*BORDER_WIDTH);

        
        // Cria botões de adicionar dispositivo
        this.buttonConcave = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET, "Espelho Côncavo", this);
        this.buttonConvex = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET*2 + UpdaterButton.HEIGHT, "Espelho Convexo", this);
        this.buttonConvergent = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET*3 + UpdaterButton.HEIGHT*2, "Lente Convergente", this);
        this.buttonDivergent = new UpdaterButton(begX+BUTTON_OFFSET, begY + BUTTON_OFFSET*4 + UpdaterButton.HEIGHT*3, "Lente Divergente", this);

        OpticalDevice device = simScreen.getDevice();
        SourceObject source = simScreen.getSource();

        // Cria sliders
        this.sliderDistance = new Slider(MIN_SOURCE_X, MAX_SOURCE_X, new Vector2(SLIDER_BEGX, BEGY_DEF + SLIDER_OFFSET), SLIDER_WIDTH, "Posição da fonte");
        this.sliderDistance.setCurrentValue(source.getX());
        this.sliderHeight = new Slider(MIN_SOURCE_HEIGHT, MAX_SOURCE_HEIGHT, new Vector2(SLIDER_BEGX, BEGY_DEF + 3*SLIDER_OFFSET), SLIDER_WIDTH, "Altura da fonte");
        this.sliderHeight.setCurrentValue(source.getHeight());
        this.sliderFocus = new Slider(-MAX_FOCUS, -MIN_FOCUS, new Vector2(SLIDER_BEGX, BEGY_DEF + 5*SLIDER_OFFSET), SLIDER_WIDTH, "Foco do dispositivo");
        this.sliderFocus.setCurrentValue(device.getFocus());

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

        this.buttonConcave.render();
        this.buttonConvex.render();
        this.buttonConvergent.render();
        this.buttonDivergent.render();

        this.sliderDistance.render();
        this.sliderHeight.render();
        this.sliderFocus.render();

    }

    public void setActiveButton(UpdaterButton button) {
        this.activeButton.setActive(false);
        this.activeButton = button;
        this.activeButton.setActive(true);
    }

    public OpticalDevice getOpticalDevice() {
        return simScreen.getOpticalDevice();
    }

    public void setOpticalDevice(OpticalDevice device) {
        simScreen.setOpticalDevice(device);
    }

}
