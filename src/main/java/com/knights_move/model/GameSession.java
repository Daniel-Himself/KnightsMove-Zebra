package com.knights_move.model;
import java.time.LocalDate;
import java.util.*;

public class GameSession {
    private Game game;
    private List<Question> usedQuestion;
    private Player player;
    private int gameLevel;
    private Position playerPosition;
    private Position enemyPosition;
    private LocalDate startTime;
    private LocalDate finishTime;
    private int currentLevelScore;
    private int totalScoreInGame;
    private Boolean award;

    public GameSession(Game game, List<Question> usedQuestion, Player player, int gameLevel, Position playerPosition, Position enemyPosition, LocalDate startTime, LocalDate finishTime, int currentLevelScore, int totalScoreInGame, Boolean award) {
        this.game = game;
        this.usedQuestion = new ArrayList<>();
        this.player = player;
        this.gameLevel = gameLevel;
        this.playerPosition = playerPosition;
        this.enemyPosition = enemyPosition;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.currentLevelScore = currentLevelScore;
        this.totalScoreInGame = totalScoreInGame;
        this.award = award;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Question> getUsedQuestion() {
        return usedQuestion;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Position playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Position getEnemyPosition() {
        return enemyPosition;
    }

    public void setEnemyPosition(Position enemyPosition) {
        this.enemyPosition = enemyPosition;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
        this.finishTime = finishTime;
    }

    public int getCurrentLevelScore() {
        return currentLevelScore;
    }

    public void setCurrentLevelScore(int currentLevelScore) {
        this.currentLevelScore = currentLevelScore;
    }

    public int getTotalScoreInGame() {
        return totalScoreInGame;
    }

    public void setTotalScoreInGame(int totalScoreInGame) {
        this.totalScoreInGame = totalScoreInGame;
    }

    public Boolean getAward() {
        return award;
    }

    public void setAward(Boolean award) {
        this.award = award;
    }


    //add used qus
    //add total score per player

   /* public void InitiateGame() {

    }*/

    public boolean addTotalScore(){
        if(totalScoreInGame != -1) {
            boolean result = player.addScoreOfPlayer(game, totalScoreInGame);
            if (result)
                return true;
            else
                return false;
        }
       else
           return false;
    }

}
