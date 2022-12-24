package com.knights_move.model;
import java.util.*;


public class Player {
    private String userName;
    private HashMap<Game, Integer> positionInGame;

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
    public void setPositionInGame(HashMap<Game, Integer> scoreInGame) {
        this.positionInGame = scoreInGame;
    }
    //update score in game
    public void setPositionInGame(Game g, Integer score)
    {
        if(getPositionInGame()==null)
        {
            positionInGame=new HashMap<>();
        }
        getPositionInGame().put(g,score);
    }

    public int getPositionInGame(Game game) {
        return positionInGame.get(game);
    }
    public HashMap<Game,Integer> getPositionInGame() {
        return positionInGame;
    }


    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                ", positionInGame=" + positionInGame +
                '}';
    }
}
