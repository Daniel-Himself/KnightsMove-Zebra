package com.knights_move.controller;

import com.knights_move.model.Board;
import com.knights_move.model.Game;
import com.knights_move.model.TypeTile;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Random;

// Splitted controller of Play game logic with assist methods
public class PlayAssistController {

    private Timeline timeline;
    private int sec = 60;


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
    public static void setSpecialTilesByLevel(Game game){

        int level = game.getGameBoard().getBoardId();
        int numOfTiles = game.getGameBoard().getNumOgSpecialTilesByLevel(level);
        System.out.println("Level -> : "+level+"    Num of tiles -> : "+numOfTiles);
        game.setSpecialTilesInLevel(numOfTiles);
    }
    //returns random number in range 0-7 -> used while generating random Jump destination
    public static int generateRandomJumpPosition(){
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
            pause.setOnFinished(e -> image.setVisible(false));
        }
        ;
        pause.play();
    }




}
