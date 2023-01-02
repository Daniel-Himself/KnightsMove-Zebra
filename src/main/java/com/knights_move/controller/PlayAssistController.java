package com.knights_move.controller;

import com.knights_move.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;

// Splitted controller of Play game logic with assist methods
public class PlayAssistController {

    private Timeline timeline;
    private int sec = 60;
    static int kingSec;


    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
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
    public static void setSpecialTilesByLevel(Game game, GridPane boardGrid){

        int level = game.getGameBoard().getBoardId();
        int numOfTiles = game.getGameBoard().getNumOgSpecialTilesByLevel(level);
        System.out.println("Level -> : "+level+"    Num of tiles -> : "+numOfTiles);
        game.setSpecialTilesInLevel(numOfTiles);
     // case of blocked tiles -> colored red
        for (Tile t: game.getGameBoard().getTileList()) {
            if(t.getTileColor().equals(Color.RED)){
                Button button = (Button)getNodeByRowColumnIndex(t.getTilePosition().getX(),t.getTilePosition().getY(), boardGrid);
                button.getStyleClass().removeAll("button");
                button.getStyleClass().removeAll("greenTile");
                button.getStyleClass().removeAll("whiteTile");
                button.getStyleClass().add("redTile");
            }
        }
    }
    public static void setNextQuestion(Game game, GridPane boardGrid){
        // case of question tiles -> colored blue
        for (Tile t: game.getGameBoard().getTileList()) {
            if(t.getTilePosition()!= new Position(0,0) && t.getTileColor().equals(Color.BLUE)){
                Button button = (Button)getNodeByRowColumnIndex(t.getTilePosition().getX(),t.getTilePosition().getY(), boardGrid);
                button.getStyleClass().removeAll("button");
                button.getStyleClass().removeAll("greenTile");
                button.getStyleClass().removeAll("whiteTile");
                button.getStyleClass().removeAll("vbox");
                button.getStyleClass().add("blueTile");
                break;
            }
        }
    }

    //returns random number in range 0-7 -> used while generating random Jump destination, next question
    public static int generateRandomPosition(){
        Random rand = new Random();
        int pick = rand.nextInt(8);
        return pick;
    }

    public static void disappear(Text lable, ImageView image, int duration){
        PauseTransition pause = new PauseTransition(Duration.seconds(duration));
        if(lable != null){
            pause.setOnFinished(e -> lable.setText(null));
        }
        if(image != null){
            pause.setOnFinished(e ->
                    image.setVisible(false));
        }
        ;
        pause.play();
    }

    public static void clearBoard(Board board){
        board.getVisitedTile().clear();
        board.getTileList().clear();
        board.getEmptyTile().clear();
        board.getLastThreeScoreChange().clear();
        board.getLastThreePositions().clear();
        board.getLastThreeScoreChange().add(0);
        board.getLastThreePositions().add(new Position(0,0));

    }

    public static void checkAnswerForRadio(Question question, int answer, Text msgTxt, Game game, Pane questionPane, RadioButton answerRadio, GridPane boardGrid){
        answerRadio.setSelected(false);
        boardGrid.setDisable(false);
        questionPane.setVisible(false);
        if(question.checkAnswer(answer)){
            msgTxt.setText("Correct answer: +"+question.getLevel()+" points to score");
            game.setCurrentLevelScore(game.getCurrentLevelScore() + question.getLevel());
        }
        else
            msgTxt.setText("Wrong answer");
    }





}
