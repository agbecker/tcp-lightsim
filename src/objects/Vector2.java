package objects;

public class Vector2 {

    private double x;
    private double y;

    public Vector2(double x, double y){
        this.setCoords(x, y);
    }

    public Vector2(){
        this.setCoords(0, 0);
    }

    public void setCoords(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    
}
