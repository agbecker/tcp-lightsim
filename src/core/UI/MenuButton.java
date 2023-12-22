package core.UI;

import java.text.ParseException;

import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

public class MenuButton extends Button {

    public MenuButton(int x, int y, int width, int height, String label, Raylib rlj) {
        super(x,y,width,height,label,rlj);
    }

    public MenuButton(int x, int y, int width, int height, Texture2D texture, Raylib rlj) {
        super(x,y,width,height,texture,rlj);
    }


    /* Função de teste puramente, mudar depois*/
    @Override
    public void function() {
        int intLabel;

        try {
            intLabel = Integer.parseInt(label);
        }

        catch (NumberFormatException e){
            label = "1";
            return;
        };

        label = Integer.toString(intLabel+1);
    }
}
