package core.simobjects.sourceObject;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.lightbeam.LightBeam;
import core.simobjects.opticaldevice.OpticalDevice;
import core.simscreens.Screen;

public class SourceObject extends ObjectToRender implements UIElement {

    // Atributos
    private static final double INITIAL_HEIGHT = 100;
    private static final double WIDTH_HEIGHT_RATIO = 30.0/100;
    private static final String TEXTURE_DEFAULT = "../../../../resources/textures/arrow.png";
    private static final Vector2 VERTEX_DEFAULT = new Vector2(100,225);

    private Texture2D texture;
    private Vector2 vertex;
    private boolean generatesImage;
    private double height; // Altura do objeto
    private double width;
    private Vector2 lightSource; // Ponto no objeto de onde originam os raios de luz
    private LightBeam beamParallel, beamVertex, beamFocus;
    private SourceObject image;

    // Construtor
    public SourceObject() {
        this(false, TEXTURE_DEFAULT, VERTEX_DEFAULT, INITIAL_HEIGHT);
    }

    public SourceObject(Vector2 vertex) {
        this(false, TEXTURE_DEFAULT, vertex, INITIAL_HEIGHT);
    }

    public SourceObject(boolean generatesImage, String texturePath, Vector2 vertex, double height) {
        this.generatesImage = generatesImage;
        Image textureImage = rTextures.LoadImage(texturePath);
        this.texture = rTextures.LoadTextureFromImage(textureImage);
        rTextures.UnloadImage(textureImage);
        this.vertex = vertex;
        this.height = height;
        this.width = height*WIDTH_HEIGHT_RATIO;
        this.lightSource = new Vector2((float)(vertex.x + width/2), (float)(vertex.y - height));
    }

    // Métodos
    public void setGeneratesImage(boolean generatesImage) {
        this.generatesImage = generatesImage;
    }

    public void setHeight(double height){
        this.height = height;
        //this.lightSource.setCoords(this.position.getX(), this.position.getY() + height);
    }

    public void adjustHeight(double delta){
        this.setHeight(this.height + delta);
    }

    public SourceObject generateImage(OpticalDevice opticalDevice) {
        // Método que gera a imagem do objeto
        this.image = new SourceObject();
        return image;
    }
    
    public void render() {
        Screen simulationScreen = ObjectToRender.getSimulationScreen();
        render(simulationScreen.getBegX(), simulationScreen.getBegY());
    }
    
    public void render(int xAbs, int yAbs) {
        Raylib rlj = UIElement.rlj;
        rlj.shapes.DrawRectangle(xAbs+(int)vertex.x, yAbs+(int)vertex.y-(int)height, (int)width, (int)height, WHITE);
        rlj.textures.DrawTexture(texture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        if(generatesImage) {
            if(beamParallel != null) beamParallel.render();
            if(beamVertex != null) beamVertex.render();
            if(beamFocus != null) beamFocus.render();
            if(image != null) image.render();
        }
    }

    public void unloadTexture() {
        rlj.textures.UnloadTexture(texture);
    }

}
