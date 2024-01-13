package core.simscreens.editors;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import core.UI.Slider;
import core.UI.UIElement;
import core.simobjects.opticaldevice.OpticalDevice;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.descriptors.SimulationScreen;

public class Updater implements UIElement {
    
    private static final int BEGX_DEF = 20;
    private static final int BEGY_DEF = 500;
    private static final int WIDTH_DEF = 930;  
    private static final int HEIGHT_DEF = 180;

    private static final int BORDER_WIDTH = 10;
    private static final int BUTTON_OFFSET = 12;

    private static final int SLIDER_WIDTH = 240;
    private static final int SLIDER_BEGX = (int) (WIDTH_DEF*3.9/5);
    private static final int SLIDER_OFFSET = 30;

    private static final int MIN_SOURCE_X = 0;
    private static final int MAX_SOURCE_X = 300;
    private static final int MIN_SOURCE_HEIGHT = 50;
    private static final int MAX_SOURCE_HEIGHT = 200;
    private static final int MIN_FOCUS = 5;
    private static final int MAX_FOCUS = 200;


    private UpdaterButton buttonConcave, buttonConvex, buttonConvergent, buttonDivergent;
    private UpdaterButton activeButton;

    private Slider sliderDistance, sliderHeight, sliderFocus;

    // Atributos
    private SimulationScreen simScreen;
    private Rectangle background, border;

    public Updater(SimulationScreen simScreen) {
        this.simScreen = simScreen;

        // Cria caixa do updater
        background = new Rectangle(BEGX_DEF, BEGY_DEF, WIDTH_DEF, HEIGHT_DEF);
        border = new Rectangle(BEGX_DEF-BORDER_WIDTH, BEGY_DEF-BORDER_WIDTH, WIDTH_DEF+2*BORDER_WIDTH, HEIGHT_DEF+2*BORDER_WIDTH);

        
        // Cria botões de adicionar dispositivo
        this.buttonConcave = new UpdaterButton(BEGX_DEF+BUTTON_OFFSET, BEGY_DEF + BUTTON_OFFSET, "Espelho Côncavo", this);
        this.buttonConvex = new UpdaterButton(BEGX_DEF+BUTTON_OFFSET, BEGY_DEF + BUTTON_OFFSET*2 + UpdaterButton.HEIGHT, "Espelho Convexo", this);
        this.buttonConvergent = new UpdaterButton(BEGX_DEF+BUTTON_OFFSET, BEGY_DEF + BUTTON_OFFSET*3 + UpdaterButton.HEIGHT*2, "Lente Convergente", this);
        this.buttonDivergent = new UpdaterButton(BEGX_DEF+BUTTON_OFFSET, BEGY_DEF + BUTTON_OFFSET*4 + UpdaterButton.HEIGHT*3, "Lente Divergente", this);

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

        // Verifica mudança nos valores
        if(sliderDistance.hasChanged()) {
            sliderDistance.setHasChanged(false);
            setNewDistance();
        }

        if(sliderHeight.hasChanged()) {
            sliderHeight.setHasChanged(false);
            setNewHeight();
        }

        if(sliderFocus.hasChanged()) {
            sliderFocus.setHasChanged(false);
            setNewFocus();
        }

    }

    public void setActiveButton(UpdaterButton button) {
        this.activeButton.setActive(false);
        this.activeButton = button;
        this.activeButton.setActive(true);
        activeButton.adjustSliderValues(sliderFocus);
    }

    public OpticalDevice getOpticalDevice() {
        return simScreen.getOpticalDevice();
    }

    public void setOpticalDevice(OpticalDevice device) {
        simScreen.setOpticalDevice(device);
    }

    public void setNewDistance() {
        simScreen.getSource().setPosition(sliderDistance.getCurrentValue());
    }

    public void setNewHeight() {
        simScreen.getSource().setHeight(sliderHeight.getCurrentValue());
    }

    public void setNewFocus() {
        simScreen.getOpticalDevice().setFocus(sliderFocus.getCurrentValue());
    }

}
