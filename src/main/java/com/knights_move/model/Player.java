package com.knights_move.model;
import java.util.*;


public class Player {
    private String userName;
    private HashMap<Game, Integer> scoreInGame;

    //same userName as sysData, used for get the attribute of player
    public Player(String userName)
    {
        this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // update all game and score
    public void setScoreInGame(HashMap<Game, Integer> scoreInGame) {
        this.scoreInGame = scoreInGame;
    }
    //update score in game
    public void setScoreInGame(Game g, Integer score)
    {
        if(getScoreInGame()==null)
        {
            scoreInGame=new HashMap<>();
        }
        getScoreInGame().put(g,score);
    }

    public int getScoreInGame(Game game) {
        return scoreInGame.get(game);
    }
    public HashMap<Game,Integer> getScoreInGame() {
        return scoreInGame;
    }


    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                ", positionInGame=" + scoreInGame +
                '}';
    }
}
