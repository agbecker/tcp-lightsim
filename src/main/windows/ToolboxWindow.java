package main.windows;

import core.simscreens.utils.ToolboxButton;

public class ToolboxWindow extends Window {
    
    private WindowButton closeButton;
    private ToolboxButton sourceObjectButton,
                          mirrorButton,
                          lensButton;

    private void assignButtons(Window origin) {
        closeButton = new WindowButton(
            930, 500, 250, 180, new String("Voltar"),
            super.getManager(), origin
        );
    }

    public ToolboxWindow(WindowManager manager, Window origin) {
        super(manager, origin);
        assignButtons(origin);
        sourceObjectButton = new ToolboxButton();
        mirrorButton = new ToolboxButton();
        lensButton = new ToolboxButton();
    }

    public void render() {
        closeButton.render();
        sourceObjectButton.render();
        mirrorButton.render();
        lensButton.render();
    }

}
