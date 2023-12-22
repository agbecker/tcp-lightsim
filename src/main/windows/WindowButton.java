package main.windows;

import core.UI.Button;
import core.UI.UIElement;

public class WindowButton extends Button implements UIElement {

    public WindowButton(int x, int y, int width, int height, String label) {
        super(x,y,width,height,label);
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
