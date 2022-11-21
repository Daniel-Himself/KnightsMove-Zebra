package com.knights_move.model;
import java.util.*;

public class Stage {
    private int stageNum;
    private int typeOfStage;
    private int  numOfForgottenTiles;
    private HashMap<Position,Tile> tilePositions;

    public Stage(int stageNum, int typeOfStage, int numOfForgottenTiles, HashMap<Position, Tile> tilePositions) {
        this.stageNum = stageNum;
        this.typeOfStage = typeOfStage;
        this.numOfForgottenTiles = numOfForgottenTiles;
        this.tilePositions = new HashMap<>();
    }

    public int getStageNum() {
        return stageNum;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public int getTypeOfStage() {
        return typeOfStage;
    }

    public void setTypeOfStage(int typeOfStage) {
        this.typeOfStage = typeOfStage;
    }

    public int getNumOfForgottenTiles() {
        return numOfForgottenTiles;
    }

    public void setNumOfForgottenTiles(int numOfForgottenTiles) {
        this.numOfForgottenTiles = numOfForgottenTiles;
    }

    public HashMap<Position, Tile> getTilePositions() {
        return tilePositions;
    }
    //all tiles positions
    public boolean addTilePosition(Position p, Tile t){
        if(p != null && t != null && !tilePositions.containsKey(p))
        {
            //position range of x -> 0 till 63, y -> 0 till 63.
            if(p.getX() < 64 && p.getY() <64) {
                tilePositions.put(p,t);
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    public boolean removeTilePosition(Position p){
        if(p != null && tilePositions.containsKey(p)) {
            tilePositions.remove(p);
            return true;
        }
        return false;
    }
}
