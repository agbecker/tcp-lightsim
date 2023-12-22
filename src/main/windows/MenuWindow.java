package main.windows;

import com.raylib.java.Raylib;

import core.UI.UIElement;

public class MenuWindow extends Window {
    
    private Raylib rlj = UIElement.rlj;

    WindowButton simButton = new WindowButton(
        UIElement.RLJ_WIDTH/2, 400, 200, 100, new String("Simular!"),
        super.getManager(), this
    );

    WindowButton manualButton = new WindowButton(
        UIElement.RLJ_WIDTH/2, 400, 200, 100, new String("Simular!"),
        super.getManager(), this
    );

    public MenuWindow(WindowManager manager) {
        super(manager);
        // Definir modos de operação (se necessário)
    }

    public void render() {
        rlj.text.DrawText("LightSim", UIElement.RLJ_WIDTH/2, 100, 20, DARK_BLUE);
        simButton.render();
    }

}
