package com.knights_move.ui_controllers;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PlayController {


    @FXML
    private ImageView queenImg;

    @FXML
    private Label scoreTxt;

    @FXML
    private GridPane boardGrid;

    @FXML
    private Label levelLbl;

    @FXML
    private Button startBtn;

    @FXML
    private Button endGameBtn;

    @FXML
    private ImageView kingImg;

    @FXML
    private GridPane figureGrid;

    @FXML
    private Label scoreLbl;

    @FXML
    private AnchorPane pnlHome;

    @FXML
    private Text timeArea;

    @FXML
    private Text msgTxt;

    @FXML
    private ImageView horseImg;
    private int min = 2;
    private int sec = 60;

    Timeline timeline;

    @FXML
    void initialize() {

        visible(false, false,false, false, true, false, false);
        figureGrid.setVisible(false);
        startBtn.setOnAction(event -> {
            visible(true,true,true,true,false,true,true);
            initGrid();
            timeline = initTimer();
        });

        endGameBtn.setOnAction(event -> {
            visible(false,false,false,false,true,false,false);
            if(timeline != null){
                timeline.stop();
                min = 2;
                sec = 60;
            }
            figureGrid.add(horseImg,0,0);
            figureGrid.add(queenImg,0,1);

        });

        boardGrid.setOnDragDropped(event -> {

            //msgTxt.setText();
        });
    }

    public void visible(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g) {
        scoreLbl.setVisible(a);
        timeArea.setVisible(b);
        levelLbl.setVisible(c);
        scoreTxt.setVisible(d);
        startBtn.setVisible(e);
        boardGrid.setVisible(f);
        endGameBtn.setVisible(g);
    }
    private void initGrid(){

        int count = 0;
        double s = 38; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(s, s, s, s);
                if (count % 2 == 0)
                    r.setFill(Color.rgb(247,247,247));
                else r.setFill(Color.rgb(93,204,196));
                boardGrid.add(r, j, i);
                count++;
            }
        }
        boardGrid.add(horseImg,0,0);
        boardGrid.add(queenImg,0,7);
    }

    private Timeline initTimer(){
        timeArea.setText("0"+String.valueOf(min)+":"+String.valueOf(sec));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            sec--;
            if(sec == 0 && min > 0){
                min--;
                sec = 59;
            }
            if(sec < 10){
                timeArea.setText("0"+String.valueOf(min)+":0"+String.valueOf(sec));
            }
            else {
                timeArea.setText("0"+String.valueOf(min) + ":" + String.valueOf(sec));
            }
            if(sec == 0 && min == 0){
                timeArea.setText("Game over!");
            }
        }));
        timeline.setCycleCount(180);
        timeline.play();
        return timeline;
    }



}