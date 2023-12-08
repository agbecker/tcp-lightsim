package objects;

import java.util.Vector;

public class SourceObject extends ObjectToRender {

    // Atributos
    private final double INITIAL_HEIGHT = 10;
    private final String TEXTURE_DEFAULT = "";
    private final Vector2 VERTEX_DEFAULT = new Vector2(0,0);

    private String texturePath;
    private OpticalDevice opticalDevice;
    private Vector2 vertex;
    private boolean generatesImage;
    private double height; // Altura do objeto
    private double width;
    private Vector2 lightSource; // Ponto no objeto de onde originam os raios de luz
    private LightBeam beamParallel, beamVertex, beamFocus;

    // Construtor
    public SourceObject(OpticalDevice opticalDevice, boolean generatesImage) {

    }

    // Métodos
    public void setGeneratesImage(boolean generatesImage) {
        this.generatesImage = generatesImage;
    }

    public void setDevice(OpticalDevice device) {
        this.opticalDevice = device;
    }

    public void setHeight(double height){
        this.height = height;
        //this.lightSource.setCoords(this.position.getX(), this.position.getY() + height);
    }

    public void adjustHeight(double delta){
        this.setHeight(this.height + delta);
    }

    public SourceObject generateImage() {
        // Método que gera a imagem do objeto
        return new SourceObject(opticalDevice, generatesImage);
    }
    
    public void render() {
        
    }

}
