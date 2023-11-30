package objects;

public abstract class ObjectToRender {

    protected Vector2 position;
    // protected Sprite sprite

    public ObjectToRender(double x_ini, double y_ini /*, Sprite sprite */){
        setPosition(x_ini, y_ini);
    }

    public void setPosition(double x, double y){
        this.position.setCoords(x,y);
    }
    
}
