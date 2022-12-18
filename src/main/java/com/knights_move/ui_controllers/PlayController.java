package com.knights_move.ui_controllers;

import com.knights_move.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

import static com.knights_move.model.TypeTile.EMPTY;
import static com.knights_move.model.TypeTile.RANDOMPJUMP;

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
    private Text timeArea;

    @FXML
    private Text msgTxt;

    @FXML
    private ImageView horseImg;

    private int turn = 1;

    private int sec = 60;
    Timeline timeline;
    Game game;
    Board board;
    Tile tile;
    Stage stage;
    Horse horse;    /* fix with @Daniela how to build figures from factory */
    Queen queen;    /* fix with @Daniela how to build figures from factory */
    King king;      /* fix with @Daniela how to build figures from factory */
    @FXML
    void initialize() {
        visible(true, true,true, true, true, true, false);
        figureGrid.setVisible(false);
        initGrid();
        System.out.println("id: "+boardGrid.getChildren().get(3).getId());

        startBtn.setOnAction(event -> {
            initGrid();
            visible(true,true,true,true,false,true,true);
            timeline = initTimer();
        });

        endGameBtn.setOnAction(event -> {
            initGrid();
            visible(true,true,true,true,true,true,false);
            if(timeline != null){
                timeline.stop();
                timeArea.setText("Timer");
                sec = 60;
            }
            figureGrid.add(horseImg,0,0);
            figureGrid.add(queenImg,0,1);
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
    private ArrayList<Integer> getRandoms(int num){
        ArrayList list = new ArrayList();
        Random rand = new Random();
        int pick;
        for (int j = 0; j < num; j++) {
            pick = rand.nextInt(64);
            list.add(pick);
        }
        System.out.println(list);
        return list;
    }

    private void initGrid(){
        board = new Board(1, 0, 0, null, new ArrayList<Tile>(), null, null);
        stage = new Stage(1, 0, 0, null);
        game = new Game(1, board, stage, null);
        int count = 0;
        double s = 38; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Button button = new Button();
                button.setOnAction(event -> {
                    moveFigure(button);
                });
                tile = new Tile(new Position(i,j),EMPTY,null,null,false);
                board.getEmptyTilesList().add(tile);
                button.setPrefSize(s, s);
                button.getStyleClass().removeAll("button");
                if (count % 2 == 0) {
                    button.getStyleClass().add("whiteTile");
                }
                else {
                    button.getStyleClass().add("greenTile");
                }
                if(i == 0 && j == 0){
                    button.setGraphic(horseImg);
                    button.getStyleClass().add("vbox");
                }
                if(i == 0 && j == 7){
                    button.setGraphic(queenImg);
                    button.getStyleClass().add("vbox");
                }
                button.setId(String.valueOf(i)+","+String.valueOf(j));
                boardGrid.add(button, j, i);
  //              System.out.println(button.idProperty());
                count++;
            }

        }
        ArrayList<Integer> jumps = getRandoms(3);
        for (int num : jumps) {
            int y = num % 10;
            int x = (num - y) % 10;
            board.getEmptyTilesList().get(num).setType(RANDOMPJUMP);
            System.out.println(num);
            System.out.println(board.getEmptyTilesList().get(num).getType());
        }
    }

    private void moveFigure(Button button){
        if(turn == 1){
            button.setGraphic(horseImg);
            turn++;
        }
        else{
            button.setGraphic(queenImg);
            turn--;
        }
        button.getStyleClass().add("vbox");
        //boardGrid.getChildren().add(horseImg,button.)
        System.out.println("button "+button.getId());
    }

    private Timeline initTimer(){
        timeArea.setText("Time: "+String.valueOf(sec));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            sec--;
            if(sec < 10){
                timeArea.setText("Time: 0"+String.valueOf(sec));
            }
            else {
                timeArea.setText("Time: "+String.valueOf(sec));
            }
            if(sec == 0){
                timeArea.setText("Game over!");
            }
        }));
        timeline.setCycleCount(60);
        timeline.play();
        return timeline;
    }



}