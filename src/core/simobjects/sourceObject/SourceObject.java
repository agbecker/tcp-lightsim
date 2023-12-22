package core.simobjects.sourceObject;

import com.raylib.java.raymath.Vector2;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.lightbeam.LightBeam;
import core.simobjects.opticaldevice.OpticalDevice;

public class SourceObject extends ObjectToRender implements UIElement {

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
    
    public void render(int xAbs, int yAbs) {
        
    }

    public String toString() {
        String text =   "OBJETO FONTE" + 
                        "\nAltura: " + String.format("%.2f", this.height) + " cm" +
                        "\nDistância ao vértice: " + String.format("%.2f", Math.abs(this.getDistanceToDevice())) + " cm\n";

        String image;
        if(!generatesImage)
            image = "Não gera imagem";

        else
            image = getImageStats();

        return text + image;
    }

    private String getImageStats() {
        String textTypeOfImage, textHeight, textDistance;

        SourceObject image = generateImage();

        double distance = image.getDistanceToDevice();
        double height = image.getHeight();

        // Acha se a imagem é real ou virtual
        // Se as duas distâncias tiverem o mesmo sinal, então a imagem é virtual
        if(distance * this.getDistanceToDevice() >= 0)
            textTypeOfImage = "Imagem Virtual";
        
        else
            textTypeOfImage = "Imagem Real";

        // Acha a altura da imagem e se está invertida
        textHeight = "\nAltura da imagem: " + String.format("%.2f", height) + " cm ";

        if(height > 0)
            textHeight += "(Direita)";
        else
            textHeight += "(Invertida)";

        // Acha a distância ao vértice
        textDistance = "\nDistância da imagem ao vértice: " + String.format("%.2f", Math.abs(image.getDistanceToDevice())) + " cm";

        return textTypeOfImage + textHeight + textDistance;

    }

    public double getHeight() {
        return this.height;
    }

    public double getDistanceToDevice() {
        Vector2 deviceVertex = this.opticalDevice.getVertex();

        return deviceVertex.getX() - this.vertex.getX();
    }

}
