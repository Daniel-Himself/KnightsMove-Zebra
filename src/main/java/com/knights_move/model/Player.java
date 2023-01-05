package com.knights_move.model;
import java.util.*;


public class Player {
    private String userName;

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


    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(userName, player.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
