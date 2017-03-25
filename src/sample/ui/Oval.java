package sample.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.FontSmoothingType;
import sample.struct.PCB;

import java.util.ArrayList;

/**
 * Created by kangfumei on 2017/3/25.
 */
public class Oval extends Canvas{
    private ArrayList<PCB> p;
    private GraphicsContext graphicsContext;

    public Oval(ArrayList<PCB> p, double width, double height){
        super(width, height);
        this.p = p;
        graphicsContext = getGraphicsContext2D();
        graphicsContext.setFontSmoothingType(FontSmoothingType.LCD);
        draw(graphicsContext);
    }

    private void draw(GraphicsContext gc){

    }
}
