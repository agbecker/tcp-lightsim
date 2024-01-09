package core.simobjects.sourceObject;

import static core.utils.Geometry.*;

import com.raylib.java.Raylib;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;
import java.lang.Math;

import core.UI.UIElement;
import core.simobjects.ObjectToRender;
import core.simobjects.lightbeam.LightBeam;
import core.simobjects.lightbeam.LightBeamSegment;
import core.simobjects.opticaldevice.Lens;
import core.simobjects.opticaldevice.Mirror;
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

    public SourceObject(Vector2 vertex, double height) {
        this(false, TEXTURE_DEFAULT, vertex, height);
    }

    public SourceObject(boolean generatesImage, String texturePath, Vector2 vertex, double height) {
        this.generatesImage = generatesImage;
        Image textureImage = rTextures.LoadImage(texturePath);
        this.texture = rTextures.LoadTextureFromImage(textureImage);
        rTextures.UnloadImage(textureImage);
        this.vertex = vertex;
        this.height = height;
        this.width = Math.abs(height)*WIDTH_HEIGHT_RATIO;
        this.lightSource = new Vector2((float)(vertex.x + width/2), (float)(vertex.y - height));
        this.beamParallel = new LightBeam(lightSource);
        this.beamVertex = new LightBeam(lightSource);
        this.beamFocus = new LightBeam(lightSource);
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
        generatesImage = true;
        beamParallel.addSegment(new Vector2(opticalDevice.getVertex().x, lightSource.y));

        // Se o foco é positivo, o raio (real) segue o foco. 
        // Caso contrário, o prolongamento do raio (virtual) segue o foco.
        if(opticalDevice.getFocus() > 0) {
            double theta = getAngle(new Vector2(opticalDevice.getVertex().x, lightSource.y), opticalDevice.getFocalPoint());
            beamParallel.addSegment(theta);
        } else {
            double theta = getAngle(new Vector2(opticalDevice.getVertex().x, lightSource.y), opticalDevice.getFocalPoint());
            beamParallel.addSegment((theta+180)%360);
        }

        beamVertex.addSegment(opticalDevice.getVertex());
        double theta = getAngle(lightSource, opticalDevice.getVertex());
        if(opticalDevice instanceof Lens) {
            beamVertex.addSegment(theta);
        } else if (opticalDevice instanceof Mirror) {
            beamVertex.addSegment((180-theta+360)%360);
        }

        Vector2 imagePoint = LightBeam.findImagePoint(beamParallel, beamVertex);
        if(imagePoint != null) {
            //System.out.printf("%f %f%n", imagePoint.x, imagePoint.y);
            double estimatedHeight = this.vertex.y-imagePoint.y;
            double estimatedWidth = Math.abs(estimatedHeight)*WIDTH_HEIGHT_RATIO;
            image = new SourceObject(new Vector2(imagePoint.x-(float)estimatedWidth/2, this.vertex.y), estimatedHeight);
            // Accounts for the size of the object
        } else {
            // Imagem é virtual, devem ser gerados os prolongamentos
            LightBeamSegment beamParallelVirtual = new LightBeamSegment(
                beamParallel.lastSegment().getStartingPoint(), 
                (beamParallel.lastSegment().getTheta()+180)%360
            );
            LightBeamSegment beamVertexVirtual = new LightBeamSegment(
                beamVertex.lastSegment().getStartingPoint(), 
                (beamVertex.lastSegment().getTheta()+180)%360
            );
            Vector2 virtualIntersection = LightBeamSegment.intersection(beamParallelVirtual, beamVertexVirtual, true);
            if(virtualIntersection != null) {
                beamParallelVirtual.setIsDashed(true); 
                beamParallelVirtual.setShowArrows(false);
                beamParallel.addSegment(beamParallelVirtual);
                beamVertexVirtual.setIsDashed(true); 
                beamVertexVirtual.setShowArrows(false);
                beamVertex.addSegment(beamVertexVirtual);
                double estimatedHeight = this.vertex.y-virtualIntersection.y;
                double estimatedWidth = Math.abs(estimatedHeight)*WIDTH_HEIGHT_RATIO;
                image = new SourceObject(new Vector2(virtualIntersection.x-(float)estimatedWidth/2, this.vertex.y), estimatedHeight);
            } else {
                return null;
            }
        }

        return image;
    }
    
    public void render() {
        Screen simulationScreen = ObjectToRender.getSimulationScreen();
        render(simulationScreen.getBegX(), simulationScreen.getBegY());
    }
    
    public void render(int xAbs, int yAbs) {
        Raylib rlj = UIElement.rlj;
        if(height < 0) {
            rlj.shapes.DrawRectangle(xAbs+(int)vertex.x, yAbs+(int)vertex.y, (int)width, -(int)height, WHITE);
        } else {
            rlj.shapes.DrawRectangle(xAbs+(int)vertex.x, yAbs+(int)vertex.y-(int)height, (int)width, (int)height, WHITE);
        }
        rlj.textures.DrawTexture(texture, xAbs+(int)vertex.x, yAbs+(int)vertex.y, WHITE);
        if(generatesImage) {
            if(beamParallel != null) beamParallel.render(xAbs, yAbs);
            if(beamVertex != null) beamVertex.render();
            if(beamFocus != null) beamFocus.render();
        }
    }

    public void unloadTexture() {
        rlj.textures.UnloadTexture(texture);
    }

}
