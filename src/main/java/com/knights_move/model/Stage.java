package com.knights_move.model;
import java.util.*;

public class Stage {
    private int stageNum;
    private int typeOfStage;
    private int numOfForgottenTiles;
    private List<Figure> figures;
    public Stage(int stageNum, int typeOfStage, int numOfForgottenTiles, List<Figure> figures) {
        this.stageNum = stageNum;
        this.typeOfStage = typeOfStage;
        this.numOfForgottenTiles = numOfForgottenTiles;
        this.figures = new ArrayList<>();
    }

    public Stage() {

    }

    public int getStageNum() {
        return stageNum;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public List<Figure> getFigures() {
        return Collections.unmodifiableList(figures);
    }

    //add figure to stage
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

    @Override
    public String toString() {
        return "Stage{" +
                "stageNum=" + stageNum +
                ", typeOfStage=" + typeOfStage +
                ", numOfForgottenTiles=" + numOfForgottenTiles +
                ", figures=" + figures +
                '}';
    }
}





