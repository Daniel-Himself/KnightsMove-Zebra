package com.knights_move.model;
import java.util.*;


public class Player {
    private int playerID;
    private String userName;
    private String password;
    private HashMap<Integer, Tile> stepHistory;

    public Player(int playerID, String userName, String password, HashMap<Integer, Tile> stepHistory) {
        this.playerID = playerID;
        this.userName = userName;
        this.password = password;
        this.stepHistory = new HashMap <Integer, Tile>();
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
}
