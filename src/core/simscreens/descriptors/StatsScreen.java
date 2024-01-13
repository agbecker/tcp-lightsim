package core.simscreens.descriptors;

import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

import core.UI.UIElement;
import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
import core.simobjects.sourceObject.SourceObject;

import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.rText;

public class StatsScreen implements UIElement {
    
    public static final int BEGX_DEF = 790;
    public static final int BEGY_DEF = 20;
    public static final int WIDTH_DEF = 390;
    public static final int HEIGHT_DEF = 450;

    private static final int BORDER_WIDTH = SCREEN_BORDER_WIDTH;
    private static final int FONT_SIZE = 18;
    private static final int TEXT_OFFSET_FROM_BORDER = 10;

    // Atributos
    private String infoText;
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

        //getGeneralStats();

        // Imprime informações na janela
        //rlj.text.DrawText(infoText, (int)background.getX(), (int)background.getY(), FONT_SIZE, WHITE);

    }

    private String getGeneralStats() {

        int numSource = 0;
        int numLenses = 0;
        int numMirrors = 0;
        int numImages = 0;

        return  "DETALHES DA SIMULAÇÃO" + 
                "\nNúmero de objetos fonte: " + numSource + 
                "\nNúmero de lentes: " + numLenses +
                "\nNúmero de espelhos: " + numMirrors +
                "\nNúmero de imagens geradas: " + numImages;

    }

}
