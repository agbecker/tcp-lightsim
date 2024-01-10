package core.UI;

import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

public class ToolboxButton extends Button{

    public ToolboxButton(int x, int y, int width, int height, String label) {
        super(x,y,width,height,label);
    }

    public ToolboxButton(int x, int y, int width, int height, Texture2D texture) {
        super(x,y,width,height,texture);
    }

    public void function() {

    }
}
