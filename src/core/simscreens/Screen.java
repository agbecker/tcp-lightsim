package core.simscreens;

public abstract class Screen {
    
    private int width, height, begX, begY;

    public Screen(int width, int height, int begX, int begY) {
        this.width = width;
        this.height = height;
        this.begX = begX;
        this.begY = begY;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getBegX() {
        return begX;
    }
    public int getBegY() {
        return begY;
    }

    public abstract void render();

}
