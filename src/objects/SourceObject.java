package objects;



public class SourceObject extends ObjectToRender {

    // Atributos
    final double INITIAL_HEIGHT = 10;

    private Vector2 lightSource; // Ponto no objeto de onde originam os raios de luz
    private double height; // Altura do objeto


    // Construtor
    public SourceObject(double x_ini, double y_ini){
        super(x_ini, y_ini);
        this.lightSource = new Vector2();
        setHeight(INITIAL_HEIGHT);
    }

    // Métodos

    public void setHeight(double height){
        this.height = height;
        this.lightSource.setCoords(this.position.getX(), this.position.getY() + height);
    }

    public void adjustHeight(double delta){
        this.setHeight(this.height + delta);
    }


    
}
