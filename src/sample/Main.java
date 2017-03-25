package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.struct.PCB;

public class Main extends Application {
    private Timer timer = new Timer();
    private GridPane gridPane;
    private Scene scene;
    private Text timeText;
    private Text nowText;
    private Button createButton;
    private Group ovalGroup;
    private Canvas canvas;
    private TextField testName,textRate,textTime;
    private PCB proccess;
    @Override
    public void start(Stage primaryStage) throws Exception{
        gridPane = new GridPane();
        gridPane.setHgap(12);
        gridPane.setVgap(12);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        scene = new Scene(gridPane,540,320);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Virtual PCB");

        init();

        gridPane.add(timeText,2,5);
        gridPane.add(nowText, 5,12);
        gridPane.add(createButton,8,5);
        gridPane.add(testName,2,10);
        gridPane.add(textRate,5,10);
        gridPane.add(textTime,8,10);
        gridPane.add(ovalGroup,2,0,10,4);
        ovalGroup.getChildren().add(canvas);

        createButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> scene.setCursor(Cursor.HAND));
        createButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> scene.setCursor(Cursor.DEFAULT));
        createButton.setOnAction(event -> {
            PCB pcb = new PCB(
                    testName.getText(),
                    Integer.parseInt(textRate.getText()),
                    Integer.parseInt(textTime.getText()),
                    timer.getTime());
            if(proccess==null) proccess=pcb;
            else pcb.setNext(proccess);
            proccess = pcb;

            proccess = proccess.merge_sort(proccess);

            proccess.show();

            System.out.println("<<<<<<<<<>>>>>>>>");
        });

        timer.start();
        primaryStage.show();
    }

    public void init(){
        proccess = null;

        timeText = new Text("0");
        timeText.setFill(Color.WHITE);
        timeText.setId("timeText");

        nowText = new Text("");
        nowText.setFill(Color.WHITE);
        nowText.setId("nowText");

        createButton = new Button("Create");
        createButton.setId("createButton");

        testName = new TextField();
        testName.setPromptText("Name");

        textRate = new TextField();
        textRate.setPromptText("Rate");

        textTime = new TextField();
        textTime.setPromptText("Time");

        canvas = new Canvas(360,60);

        ovalGroup = new Group();

    }

    public void draw(){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0,360,60);
        if(proccess!=null){
            PCB h = proccess;
            int i=0;
            while (h!=null){
                graphicsContext.save();
                graphicsContext.setStroke(Color.WHITE);
                graphicsContext.setFill(Color.WHITE);
                graphicsContext.fillArc(i*54,0,32,32,0,
                        360*h.getRtime()/h.getNtime(),
                        ArcType.OPEN);
                graphicsContext.fillText(h.getName(),i*54,54);
                graphicsContext.restore();

                h = h.getNext();
                i++;
            }
        }

    }

    /**
     *
     * @getTime 返回当前时间
     *
     */

    public class Timer extends Thread{
        private int time;

        Timer(){
            time = 0;
            setDaemon(true);
        }

        int getTime(){
            return time;
        }

        @Override
        public void run(){
            while (true){
                time+=1;
                Platform.runLater(()->{
                    timeText.setText(String.valueOf(time));
                    if(proccess!=null){
                        if(proccess.runOver()){
                            proccess = proccess.getNext();
                        }else{
                            proccess.setRtime();
                        }
                        nowText.setText(proccess.getName());
                        draw();
                    }
                });
                try {
                    sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

