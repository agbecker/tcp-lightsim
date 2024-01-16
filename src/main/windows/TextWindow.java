package main.windows;

import com.raylib.java.Raylib;

import core.UI.UIElement;

public class TextWindow extends Window {

    private String displayText;
    private WindowButton menuButton;
    private Raylib rlj = UIElement.rlj;

    public TextWindow(WindowManager manager, Window origin, String displayText) {
        super(manager);
        this.displayText = displayText;
        this.assignButtons(origin);
    }

    private void assignButtons(Window origin) {
        menuButton = new WindowButton(
            980, 500, 200, 180, new String("Menu"),
            super.getManager(), origin
        );
    }


    public void render() {
        String text;

        if(this.displayText == "about")
            text = textAbout();

        else
            text = textHowTo();

        rlj.text.DrawText(text, 100, 50, 20, UIElement.WHITE);
        menuButton.render();

    }

    
    private String textHowTo() {
        return "~~ Como usar o LightSim ~~\n\n" +

                "O LightSim é um simulador de óptica de lentes e espelhos esféricos.\n" +
                "Ao acessar a tela de simulação pelo botão \"Simular!\" do menu,\n" +
                "você verá uma janela com a simulação no canto superior esquerdo. Esta janela\n" +
                "tem três elementos dispostos sobre um eixo: um objeto fonte, um dispositivo óptico\n" +
                "e a imagem do objeto gerada pelo dispositivo.\n\n" +

                "À direita da janela de simulação há uma janela com informações sobre a simulação,\n" +
                "informando dados numéricos e qualitativos sobre o que está sendo simulado.\n\n" +

                "Na porção inferior da tela você encontrará o painel Updater. Ele conta com quatro\n" +
                "botões que você pode clicar para alterar o tipo de dispositivo óptico usado na\n" +
                "simulação, e três sliders que você pode segurar e arrastar com o mouse para alterar\n" +
                "valores na simulação. Verifique as alterações em tempo real nas janelas\n" +
                "de simulação e de informações!"     
        
        ;
    }

    private String textAbout() {
        return "LightSim - Simulador da óptica de lentes e espelhos\n\n" +
                
                "Este software foi desenvolvido como trabalho prático da disciplina \n" +
                "INF01120 - Técnicas de Construção de Programas no semestre letivo de 2023/2\n\n" +

                "Alunos responsáveis: \n" +
                "Álvaro Guglielmin Becker\nEnzo Borges Segala\n" +
                "João Raphael Fontoura Dorneles\nVíctor Lucas Rosada Canesin\n\n" + 

                "Professor: Dennis Giovani Balreira"
        ;
    }
    
}
