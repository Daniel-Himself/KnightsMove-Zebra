package com.knights_move.controller;

import com.knights_move.model.Board;
import com.knights_move.model.Game;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
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
    public static void setSpecialTilesByLevel(Game game, Board board){
        if(board.getBoardId() == 1){
            game.setSpecialTilesInLevel(board.getNumOfRandomJumpTiles());
        }
        else if(board.getBoardId() == 2){
            game.setSpecialTilesInLevel(board.getNumOfForgottenTiles());
        }
        else if(board.getBoardId() == 3){
            game.setSpecialTilesInLevel(board.getNumOfForgottenTiles() + board.getNumOfRandomJumpTiles());
        }
        else if(board.getBoardId() == 4){
            game.setSpecialTilesInLevel(board.getNumOfBlockedTiles());
        }
    }
    //returns random number in range 0-7 -> used while generating random Jump destination
    public static int generateRandomJumpPosition(){
        Random rand = new Random();
        int pick = rand.nextInt(8);
        return pick;
    }

}
