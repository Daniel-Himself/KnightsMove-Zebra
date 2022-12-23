package com.knights_move.controller;

import com.knights_move.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private boolean initialized = false;
    private int turn = 1;

    private int sec = 60;
    private Timeline timeline;
    private Game game;
    private Board board;
    private Tile tile;
    private Figure horse;
    private Figure queen;
    private Figure king;
    @FXML
    void initialize() {
        visible(true, true,true, true, true, true, false);
        figureGrid.setVisible(false);
        boardGrid.setGridLinesVisible(true);

        startBtn.setOnAction(event -> {
            if (!initialized) initGrid();
            setFiguresOnBoard();
            visible(true,true,true,true,false,true,true);
            timeline = initTimer();
        });

        endGameBtn.setOnAction(event -> {
            clearGrid();
        });

    }

    private void clearGrid() {
        visible(true,true,true,true,true,true,false);
        msgTxt.setText("Game lasted "+(60-sec)+" seconds");
        if(timeline != null){
            timeline.stop();
            timeArea.setText("Timer");
            sec = 60;
        }
        figureGrid.add(horseImg,0,0);
        figureGrid.add(queenImg,0,1);
        ObservableList<Node> children = boardGrid.getChildren();
        for (Node node : children) {
            Button btn = (Button)node;
            btn.getStyleClass().removeAll("vbox");
        }
        Button btnHorseImg = (Button)getNodeByRowColumnIndex(horse.getPosition().getX(),horse.getPosition().getY(),boardGrid);
    }

    public void visible(boolean scoreLbl1, boolean timeArea1, boolean levelLbl1, boolean scoreTxt1, boolean startBtn1, boolean boardGrid1, boolean endGameBtn1) {
        scoreLbl.setVisible(scoreLbl1);
        timeArea.setVisible(timeArea1);
        levelLbl.setVisible(levelLbl1);
        scoreTxt.setVisible(scoreTxt1);
        startBtn.setVisible(startBtn1);
        boardGrid.setVisible(boardGrid1);
        endGameBtn.setVisible(endGameBtn1);
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

    public void setFiguresOnBoard() {
        msgTxt.setText("Game started");
        Button btnHorse = (Button)getNodeByRowColumnIndex(0,0, boardGrid);
        btnHorse.setGraphic(horseImg);
        System.out.println("image in figure grid: "+figureGrid.getChildren()); //todo - fix here
        btnHorse.getStyleClass().add("vbox");
        Button btnQueen = (Button)getNodeByRowColumnIndex(0,7, boardGrid);
        btnQueen.setGraphic(queenImg);
        btnQueen.getStyleClass().add("vbox");

    }

    private void initGrid(){
        boardGrid.setGridLinesVisible(false);
        initFigures();
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
                button.setId(i +","+ j);
                boardGrid.add(button, j, i);
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
        initialized = true;
    }

    private void initFigures(){
        FigureFactory figureFactory = new FigureFactory();
        horse = (Figure) figureFactory.getFigure("horse");
        queen = (Figure) figureFactory.getFigure("queen");
        System.out.println("new horse: "+horse);
        System.out.println("new queen: "+queen);
        board = new Board(1, 0, null, new ArrayList<Tile>(), null, null);
        game = new Game(1, board, null);
    }

    private void moveFigure(Button button){
        if(turn == 1){
            button.setGraphic(horseImg);
            Position horseNewPos = new Position(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
            horse.setPosition(horseNewPos);
            System.out.println("horse: "+horseNewPos);
            button.getStyleClass().add("vbox");
            turn++;
            if(turn == 2){
                Position queenCurrPosition = queen.getPosition();
                System.out.println("new queen: "+queen);

                Position queenNextPosition = queen.move(horse.getPosition(), queenCurrPosition);
                Button nextNode = (Button)getNodeByRowColumnIndex(queenNextPosition.getX(),queenNextPosition.getY(), boardGrid);
                msgTxt.setText("horse : " + horse.getPosition().getX()+" "+ horse.getPosition().getX()+"  queen : "+queenNextPosition.getX()+" "+queenNextPosition.getY());
                nextNode.setGraphic(queenImg);
                nextNode.getStyleClass().add("vbox");
                turn--;
            }
        }
        System.out.println("image in figure grid: "+figureGrid.getChildren());

    }

    private Timeline initTimer(){
        timeArea.setText("Time: "+sec);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            sec--;
            if(sec < 10){
                timeArea.setText("Time: 0"+sec);
            }
            else {
                timeArea.setText("Time: "+sec);
            }
            if(sec == 0){
                timeArea.setText("Game over!");
            }
        }));
        timeline.setCycleCount(60);
        timeline.play();
        return timeline;
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }



}