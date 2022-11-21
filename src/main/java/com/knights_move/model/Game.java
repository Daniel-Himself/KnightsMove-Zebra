package com.knights_move.model;
import java.util.*;

public class Game {
    private int gameID;
    private Board gameBoard;
    private Stage stageGame;
    private List<Question> question;




    public Game(int gameID, Board gameBoard, Stage stageGame, List<Question> question) {
        this.gameID = gameID;
        this.gameBoard = gameBoard;
        this.stageGame = stageGame;
        this.question = new ArrayList<>();
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Stage getStageGame() {
        return stageGame;
    }

    public void setStageGame(Stage stageGame) {
        this.stageGame = stageGame;
    }

    public List<Question> getQuestion() {
        return Collections.unmodifiableList(question);
    }
    //waiting for Question class
        //public void addQuestion
        //removeQuestion
        //editQuestion


}
