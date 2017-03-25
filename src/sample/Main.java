package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import sample.struct.PCB;
import sample.utli.Timer;

import java.util.ArrayList;

public class Main extends Application {
    private Timer timer = new Timer();
    private GridPane gridPane;
    private Scene scene;
    private Text timeText;
    private Button createButton;
    private TextField testName,textRate,textTime;
    private ArrayList proccess = new ArrayList<PCB>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        gridPane = new GridPane();
        gridPane.setHgap(12);
        gridPane.setVgap(12);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setGridLinesVisible(true);
        scene = new Scene(gridPane,480,320);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Virtual PCB");

        init();

        gridPane.add(timeText,2,5);
        gridPane.add(createButton,8,5);
        gridPane.add(testName,2,10);
        gridPane.add(textRate,5,10);
        gridPane.add(textTime,8,10);

        createButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> scene.setCursor(Cursor.HAND));
        createButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> scene.setCursor(Cursor.DEFAULT));
        createButton.setOnAction(event -> {
            PCB pcb = new PCB(testName.getText(),Integer.parseInt(textRate.getText()),Integer.parseInt(textTime.getText()));
            pcb.show();
        });

        timer.start();
        primaryStage.show();
    }

    public void init(){
        timeText = new Text("0");
        timeText.setFill(Color.WHITE);
        timeText.setId("timeText");

        createButton = new Button("Create");
        createButton.setId("createButton");

        testName = new TextField();
        testName.setPromptText("Name");

        textRate = new TextField();
        textRate.setPromptText("Rate");

        textTime = new TextField();
        textTime.setPromptText("Time");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public class Timer extends Thread{
        private long time;

        public Timer(){
            time = 0;
            setDaemon(true);
        }

        public long getTime(){
            return time;
        }

        @Override
        public void run(){
            while (true){
                time+=1;
                Platform.runLater(()->{
                    timeText.setText(String.valueOf(time));
                });
                try {
                    sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}

