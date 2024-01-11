package core.simscreens.descriptors;

import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
import core.simobjects.sourceObject.SourceObject;
import core.simscreens.Screen;

import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.rText;

public class StatsScreen extends Screen {
    
    private static final int BEGX_DEF = 790;
    private static final int BEGY_DEF = 20;
    private static final int WIDTH_DEF = 390;
    private static final int HEIGHT_DEF = 450;

    //private static final int BORDER_WIDTH = UIElement.SCREEN_BORDER_WIDTH;
    private static final int BORDER_WIDTH = 0;

    // Atributos
    private ObjectToRender objectSelected;
    private boolean displaySimulationStats;
    private String infoText;

    private Rectangle border, background;

    public StatsScreen() {
        this(WIDTH_DEF, HEIGHT_DEF, BEGX_DEF, BEGY_DEF);
    }

    public StatsScreen(int width, int height, int begX, int begY) {
        super(width, height, begX, begY);
        displaySimulationStats = true;

        background = new Rectangle(begX, begY, width, height);
        border = new Rectangle(begX-BORDER_WIDTH, begY-BORDER_WIDTH, width+2*BORDER_WIDTH, height+2*BORDER_WIDTH);
    }

    public void setObjectSelected(ObjectToRender objectSelected) {
        System.out.println("Objeto alterado na StatsScreen");
        this.objectSelected = objectSelected;
    }

    void updateParameters() {
        // Atualiza os parâmetros do objectSelected
    }

    public void render() {

        // Desenha janela em si
        rShapes.DrawRectangleRec(border, LIGHT_BLUE);
        rShapes.DrawRectangleRec(background, DARK_BLUE);

        // Seleciona informações a mostrar
        if(this.objectSelected == null) {
            infoText  = getGeneralStats();
        }

        else {
            infoText = getStatsFromObject();
        }

        // Imprime informações na janela
        //rlj.text.DrawText(infoText, (int)background.getX(), (int)background.getY(), FONT_SIZE, WHITE);

    }

    private String getGeneralStats() {

        //int numSource = SimulationScreen.getNumSourceObjects();
        //int numLenses = SimulationScreen.getNumLenses();
        //int numMirrors = SimulationScreen.getNumMirrors();
        //int numImages = SimulationScreen.getNumImages();

        //return  "DETALHES DA SIMULAÇÃO" + 
        //        "\nNúmero de objetos fonte: " + numSource + 
        //        "\nNúmero de lentes: " + numLenses +
        //        "\nNúmero de espelhos: " + numMirrors +
        //        "\nNúmero de imagens geradas: " + numImages;

        return "";

    }

    private String getStatsFromObject() {
        return this.objectSelected.toString();
    }

}
