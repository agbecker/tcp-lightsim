package core.simscreens.editors;

import com.raylib.java.raymath.Vector2;

import core.UI.Button;
import core.simobjects.opticaldevice.*;

public class UpdaterButton extends Button{

    Updater updater;

    public UpdaterButton(int x, int y, int width, int height, String label, Updater updater) {
        super(x, y, width, height, label);
        this.updater = updater;
    }


    @Override
    public void function() {

        System.out.println(this.label);

        /*

        OpticalDevice device;

        double focus = Math.abs(Updater.getFocus());
        Vector2 vertex = Updater.getDeviceVertex();
        boolean displayFocus = Updater.getDisplayFocus();

        // Se for côncavo ou divergente, o foco é negativo
        if(label.matches(".*(Côncavo|Divergente).*")) {
            focus = -focus;
        }

        // Cria o dispositivo a usar
        if(label.matches(".*Espelho.*"))
            device = new Mirror(focus, vertex, displayFocus);

        else
            device = new Lens(focus, vertex, displayFocus);

        // Envia para o Updater
        updater.setOpticalDevice(device); */

    }
}
