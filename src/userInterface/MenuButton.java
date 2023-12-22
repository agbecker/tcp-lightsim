package userInterface;

import java.text.ParseException;

import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

public class MenuButton extends Button{

    public MenuButton(int x, int y, int width, int height, String label, Raylib rlj) {
        super(x,y,width,height,label,rlj);
    }

    public MenuButton(int x, int y, int width, int height, Texture2D texture, Raylib rlj) {
        super(x,y,width,height,texture,rlj);
    }


<<<<<<< Updated upstream
=======
    /* Função de teste puramente, mudar depois*/
    @Override
    public void function() {
>>>>>>> Stashed changes
        int intLabel;

        try {
            intLabel = Integer.parseInt(label);
        }

<<<<<<< Updated upstream
        catch (Exception e) {
            intLabel = 1;
            label = Integer.toString(intLabel);
=======
        catch (NumberFormatException e){
            label = "1";
>>>>>>> Stashed changes
            return;
        };

        label = Integer.toString(intLabel+1);
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    }
}
