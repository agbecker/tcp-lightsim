package core.UI;
import static com.raylib.java.core.input.Mouse.MouseButton.MOUSE_BUTTON_LEFT;

import com.raylib.java.Raylib;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import core.simscreens.editors.Updater;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;

public class Slider implements UIElement {

    private double minValue;
    private double maxValue;
    private double percent;
    private String minLabel, maxLabel;

    private boolean wasBeingHeld;
    private double lastMouseX;
    private boolean valueHasChanged;

    private double trackLeftX;
    private double trackRightX;
    private double trackCenterY;

    private static Raylib rlj = UIElement.rlj;
    private Rectangle rect, shadow;
    private Rectangle track;

    private static final int RECT_WIDTH = 24*2;
    private static final int RECT_HEIGHT = 12*2;
    private static final int SHADOW_HEIGHT = 6*2;
    private static final int TRACK_HEIGHT = 4*2;
    private static final int FONT_SIZE = RECT_HEIGHT;
    private static final int LABEL_GAP = 10;
    private static final int TEXT_OFFSET_LEFT = 280;

    private String settingLabel;


    public Slider(double min, double max, Vector2 trackCenterPoint, double trackWidth, String label) {
        this.settingLabel = label;
        
        minValue = min;
        maxValue = max;
        percent = 50;
        setLabels();

        trackLeftX = trackCenterPoint.getX() - trackWidth/2;
        trackRightX = trackCenterPoint.getX() + trackWidth/2;
        trackCenterY = trackCenterPoint.getY();

        wasBeingHeld = false;
        valueHasChanged = false;

        int rectX = (int) (trackCenterPoint.getX() - RECT_WIDTH/2);
        int rectY = (int) (trackCenterY + TRACK_HEIGHT/2 - RECT_HEIGHT);
        rect = new Rectangle(rectX, rectY, RECT_WIDTH, RECT_HEIGHT);
        shadow = new Rectangle(rectX, rectY+RECT_HEIGHT, RECT_WIDTH, SHADOW_HEIGHT);
        track = new Rectangle((int) trackLeftX, (int) (trackCenterY-(TRACK_HEIGHT/2)), (int) (trackRightX-trackLeftX), TRACK_HEIGHT);
    }

    public void render() {
        checkIsBeingDragged();

        rShapes.DrawRectangleRec(this.track, DARK_BLUE);
        rShapes.DrawRectangleRec(this.shadow, DARK_PURPLE);
        rShapes.DrawRectangleRec(this.rect, LIGHT_PURPLE);

        int minLabelX = (int) trackLeftX - LABEL_GAP - rlj.text.MeasureText(minLabel, FONT_SIZE);
        int maxLabelX = (int) trackRightX + LABEL_GAP;
        int labelY = (int) trackCenterY - FONT_SIZE/2;

        rlj.text.DrawText(minLabel, minLabelX, labelY, FONT_SIZE, DARK_PURPLE);
        rlj.text.DrawText(maxLabel, maxLabelX, labelY, FONT_SIZE, DARK_PURPLE);

        String currentValueLabel = String.format("%.1f", getCurrentValue());
        int currentLabelWidth = rlj.text.MeasureText(currentValueLabel, FONT_SIZE);
        int currentLabelX = (int) rect.getX() + (RECT_WIDTH - currentLabelWidth)/2;
        int currentLabelY = (int) shadow.getY() + SHADOW_HEIGHT + LABEL_GAP;
        rlj.text.DrawText(currentValueLabel, currentLabelX, currentLabelY, FONT_SIZE, DARK_PURPLE);

        rlj.text.DrawText(this.settingLabel, (int) (trackLeftX - TEXT_OFFSET_LEFT), (int) trackCenterY - FONT_SIZE/2, FONT_SIZE, WHITE);
    }

    public double getCurrentValue() {
        return (maxValue - minValue)*percent/100 + minValue;
    }

    public void setCurrentValue(double newValue) {
        double newPercent = (newValue - minValue)/(maxValue - minValue);

        updateKnobPosition(trackLeftX + newPercent*(trackRightX - trackLeftX - rect.width));        
    }

    public void updateKnobPosition(double newX) {
        
        if(trackLeftX <= newX && newX <= trackRightX - RECT_WIDTH){
            rect.setX((float) newX);
            shadow.setX((float) newX);
        }

        if (newX < trackLeftX) {
            rect.setX((float) trackLeftX);
            shadow.setX((float) trackLeftX);
        }

        if (newX > trackRightX) {
            rect.setX((float) trackRightX - RECT_WIDTH);
            shadow.setX((float) trackRightX - RECT_WIDTH);
        }

        percent = 100*((rect.getX() -trackLeftX)/((trackRightX - RECT_WIDTH) - trackLeftX));
        setHasChanged(true);
        
    }

    public void checkIsBeingDragged() {

        Vector2 cursor = rCore.GetMousePosition();
        Rectangle knob = new Rectangle(rect.getX(), rect.getY(), RECT_WIDTH, RECT_HEIGHT+SHADOW_HEIGHT);

        // Se não estiver sendo selecionado, nada muda
        if(!wasBeingHeld && !rlj.shapes.CheckCollisionPointRec(cursor, knob)) {
            return;
        }

        // Se o usuário acabou de clicar no slider pela primeira vez,
        // informa que deverá mudar e armazena a posição que o slider estava inicialmente
        if(!wasBeingHeld && rlj.shapes.CheckCollisionPointRec(cursor, knob) && rCore.IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            wasBeingHeld = true;
            lastMouseX = cursor.getX();
            return;
        }

        // Se o usuário mexeu o slider, atualiza sua posição
        if(wasBeingHeld && rCore.IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            double dx = cursor.getX() - lastMouseX;
            updateKnobPosition(knob.getX() + dx);
            lastMouseX = cursor.getX();

            // "Satura" a última posição do mouse nos extremos do trilho do slider
            if(lastMouseX < track.getX()) {
                lastMouseX = track.getX();
            }

            if(lastMouseX > track.getX() + track.getWidth()) {
                lastMouseX = knob.getX();
            }

            return;
        }

        // Se o usuário soltou o mouse
        if(wasBeingHeld && !rCore.IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            wasBeingHeld = false;
        }   
    }

    public void setValuesSign(int sign) {
        // Verifica se precisa mudar
        if (sign > 0 && this.maxValue > 0 ||
                sign < 0 && this.minValue < 0) {

            System.out.println("Sign: "+sign+"| Comparação: "+maxValue+" ou "+minValue);
            return;
        }

        double currentValue = getCurrentValue();
        double currentMax = this.maxValue;
        double currentMin = this.minValue;

        this.minValue = -currentMax;
        this.maxValue = -currentMin;
        this.setCurrentValue(-currentValue);
        this.setLabels();
    }

    public void setLabels() {
        this.minLabel = Double.toString(minValue);
        this.maxLabel = Double.toString(maxValue);
    }

    public void setHasChanged(boolean state) {
        this.valueHasChanged = state;
    }

    public boolean hasChanged() {
        return this.valueHasChanged;
    }
}
