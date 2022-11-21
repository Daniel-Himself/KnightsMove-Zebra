package com.knights_move.model;
import java.util.*;

public class Stage {
    private int stageNum;
    private int typeOfStage;
    private int  numOfForgottenTiles;




    public Stage(int stageNum, int typeOfStage, int numOfForgottenTiles) {
        this.stageNum = stageNum;
        this.typeOfStage = typeOfStage;
        this.numOfForgottenTiles = numOfForgottenTiles;


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






}
