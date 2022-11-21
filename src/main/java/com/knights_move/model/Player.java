package com.knights_move.model;
import java.util.*;


public class Player {
    private int playerID;
    private String userName;
    private String password;
    private HashMap<Integer, Tile> stepHistory;
    //gameID, garde
    private HashMap<Game, Integer> scoreInGame;

    public Player(int playerID, String userName, String password, HashMap<Integer, Tile> stepHistory,HashMap<Integer, Integer> scoreInGame) {
        this.playerID = playerID;
        this.userName = userName;
        this.password = password;
        this.stepHistory = new HashMap <>();
        this.scoreInGame = new HashMap<>();
    }

    public HashMap<Game, Integer> getScoreInGame() {
        return scoreInGame;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<Integer, Tile> getStepHistory() {
        return stepHistory;
    }

    public void setStepHistory(HashMap<Integer, Tile> stepHistory) {
        this.stepHistory = stepHistory;
    }

    //save user scores by game
    public boolean addScoreOfPlayer (Game game, int totalGrade){
        if(game != null && totalGrade != -1 && !scoreInGame.containsKey(game)){
            scoreInGame.put(game,totalGrade);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", stepHistory=" + stepHistory +
                '}';
    }
}
