package core.simscreens.descriptors;

import core.UI.UIElement;
import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
import core.simobjects.opticaldevice.OpticalDevice;
import core.simobjects.sourceObject.SourceObject;

import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

public class StatsScreen implements UIElement {
    
    public static final int BEGX_DEF = 790;
    public static final int BEGY_DEF = 20;
    public static final int WIDTH_DEF = 390;
    public static final int HEIGHT_DEF = 450;

    private static final int BORDER_WIDTH = SCREEN_BORDER_WIDTH;
    private static final int TITLE_FONT_SIZE = 28;
    private static final int FONT_SIZE = 18;
    private static final int TEXT_OFFSET_FROM_BORDER = 10;

    public static final double CONVERSION_RATE = 0.01; // 1 m/10 pixels

    // Atributos
    private SimulationScreen simScreen;

    private Rectangle border, background;

    public StatsScreen(SimulationScreen simScreen) {
        background = new Rectangle(BEGX_DEF, BEGY_DEF, WIDTH_DEF, HEIGHT_DEF);
        border = new Rectangle(BEGX_DEF-BORDER_WIDTH, BEGY_DEF-BORDER_WIDTH, WIDTH_DEF+2*BORDER_WIDTH, HEIGHT_DEF+2*BORDER_WIDTH);
        this.simScreen = simScreen;
    }

    public void render() {

        // Desenha janela em si
        rShapes.DrawRectangleRec(border, LIGHT_BLUE);
        rShapes.DrawRectangleRec(background, DARK_BLUE);

        // Escreve o título
        int offsetY = TEXT_OFFSET_FROM_BORDER;
        rlj.text.DrawText("Informações da simulação", BEGX_DEF + TEXT_OFFSET_FROM_BORDER, BEGY_DEF + offsetY, TITLE_FONT_SIZE, WHITE);

        // Escreve as informações
        offsetY = offsetY + (int) (1.6*TITLE_FONT_SIZE);
        String infoText = getStatsFromSimulation();
        rlj.text.DrawText(infoText, BEGX_DEF + TEXT_OFFSET_FROM_BORDER, BEGY_DEF + offsetY, FONT_SIZE, WHITE);

    }

    private String getStatsFromSimulation() {

        SourceObject source = simScreen.getSource();
        SourceObject image = source.getImage();
        OpticalDevice device = simScreen.getDevice();

        String infoSource = getSourceInfo(source);
        String infoDevice = getDeviceInfo(device);
        String infoImage = getImageInfo(image, device);

        String text = infoSource + "\n" + infoDevice + "\n" + infoImage;
        
        return text;

    }

    private String getSourceInfo(SourceObject source) {
        double distance = source.getDistanceToDevice() * CONVERSION_RATE;
        double height = source.getHeight() * CONVERSION_RATE;

        String distanceStr = "Distância da fonte ao dispositivo: " + String.format("%.2f", distance) + " m\n";
        String heightStr = "Altura da fonte: " + String.format("%.2f", height) + " m";

        return distanceStr + heightStr;

    }

    private String getDeviceInfo(OpticalDevice device) {
        double focus = device.getFocus() * CONVERSION_RATE;
        String type;
        if(device instanceof Mirror) {
            type = "Espelho ";

            if(focus < 0)
                type += "Côncavo";
            else
                type += "Convexo";
        }
        else {
            type = "Lente ";

            if(focus < 0)
                type += "Divergente";
            else
                type += "Convergente";
        }
        type +="\n";

        String focusStr = String.format("%.2f", focus) + " m";

        return type + focusStr;

    }

    private String getImageInfo(SourceObject image, OpticalDevice device) {
        double distance = device.getVertex().x - image.getX();
        double height = image.getHeight() * CONVERSION_RATE;
        
        String type = "Imagem ";

        if (distance > 0 && device instanceof Mirror ||
            distance < 0 && device instanceof Lens) {
                type += "real, ";
            }
        else
            type += "virtual, ";

        if(height < 0)
            type += "invertida\n";
        else
            type += "direita\n";

        String distanceStr = "Distância da imagem ao dispositivo: " + String.format("%.2f", Math.abs(distance)) + " m\n";
        String heightStr = "Altura da imagem: " + String.format("%.2f", height) + " m";

        return type + distanceStr + heightStr;

    }

}
