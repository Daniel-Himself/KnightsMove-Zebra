package com.knights_move.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class GameSession {
    private Game game;
    private List<Question> usedQuestion;
    private Player player;
    private int gameLevel;
    private Position playerPosition;
    private Position enemyPosition;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private int currentLevelScore;
    private int totalScoreInGame;
    private Boolean award;

    public GameSession(Game game, List<Question> usedQuestion, Player player, int gameLevel, Position playerPosition, Position enemyPosition, LocalDateTime startTime, LocalDateTime finishTime, int currentLevelScore, int totalScoreInGame, Boolean award) {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
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


    public boolean addTotalScore(){
        if(currentLevelScore != -1) {
            totalScoreInGame += currentLevelScore;
            boolean result = player.addScoreOfPlayer(game, totalScoreInGame);
            if (result)
                return true;
            else
                return false;
        }
       else
           return false;
    }

    //init game -> stage one
    public void initGame(){
        setStartTime(java.time.LocalDateTime.now());
        //the func returns list of init positions.
        Position positionList[] = game.getGameBoard().initPosition();
        int sizeOfBoard = 64;
        Tile tileList[] = new Tile[sizeOfBoard];
        // init empty tile
        for(int i = 0; i < sizeOfBoard ; i++){
            for (Position p: positionList) {
                tileList[i] = new Tile(p, TypeTile.EMPTY, Color.WHITE, false);
                game.getGameBoard().addEmptyTile(tileList[i]);
            }

        }
        System.out.print("position list" + positionList);
        System.out.print("tile List" + tileList);
        //init with first stage
        Board boardOfGame = game.setTilesInLevel(1);

        List<Figure> figurePosition = game.initFigureInStage(1);

    }

    public boolean finishStageInGame() {
        if(startTime != null && finishTime != null) {
            if(finishTime.getMinute() - startTime.getMinute() >= 1 || currentLevelScore >= 15) {
                currentLevelScore = 0;
                return true;
            }
        }
        return false;

    }

}
