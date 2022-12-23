package com.knights_move.model;
import java.util.*;


public class Player {
    private String userName;
    private HashMap<Game, Integer> scoreInGame;


    public Player(String userName)
    {
        this.userName=userName;
    }
    public HashMap<Game, Integer> getScoreInGame() {
        return scoreInGame;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //save user scores by game
    public boolean addScoreOfPlayer (Game game, int totalGrade){
        try {
            if(game != null && totalGrade != -1 && !scoreInGame.containsKey(game)){
                scoreInGame.put(game,totalGrade);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
