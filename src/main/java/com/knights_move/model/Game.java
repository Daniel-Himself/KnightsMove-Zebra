package com.knights_move.model;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Game {
    private int gameID;
    private Board gameBoard;
    private List<Question> question;
    private LocalDate dateOfGame;


    int position;
    private HashMap<Game, Integer> scoreInGame;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private int currentLevelScore;
    private int totalScoreInGame;
    private Boolean award;


    public Game(int gameID, Board gameBoard, List<Question> question) {
        this.gameID = gameID;
        this.gameBoard = gameBoard;
        this.question = new ArrayList<>();
        this.dateOfGame=java.time.LocalDate.now();
    }
    //construcor for json
    public Game(int gameID,LocalDate date, int position) {
        this.gameID = gameID;
        this.dateOfGame = date;
        this.position=position;
    }

    public Game() {

    }


    public void setScoreInGame(HashMap<Game, Integer> scoreInGame) {
        this.scoreInGame = scoreInGame;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public void setCurrentLevelScore(int currentLevelScore) {
        this.currentLevelScore = currentLevelScore;
    }

    public void setTotalScoreInGame(int totalScoreInGame) {
        this.totalScoreInGame = totalScoreInGame;
    }

    public void setAward(Boolean award) {
        this.award = award;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public int getCurrentLevelScore() {
        return currentLevelScore;
    }

    public int getTotalScoreInGame() {
        return totalScoreInGame;
    }

    public Boolean getAward() {
        return award;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }


    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public List<Question> getQuestion() {
        return Collections.unmodifiableList(question);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", gameBoard=" + gameBoard +
                ", question=" + question;
                }
    public LocalDate getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    //using Factory Design Pattern
    public List<Figure> initFigures(){
        try {
            //init the position of figures At the beginning of the stage
            FigureFactory figureFactory = new FigureFactory();
            Figure horse = (Figure) figureFactory.getFigure("horse");
            List<Figure> listOfFigures = new ArrayList<>();
            listOfFigures.add(horse);
            Figure queen = (Figure) figureFactory.getFigure("queen");
            listOfFigures.add(queen);
            Figure king = (Figure) figureFactory.getFigure("king");
            listOfFigures.add(king);
            return listOfFigures;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //waiting for Question class
        //public void addQuestion
        //removeQuestion
        //editQuestion
    public Board setSpecialTilesInLevel(int gameLevel, Board b){
        try {
            Random rand = new Random();
            if(gameLevel == 1) {
                //random position cannot be 0,0 or 63,63
                //create new 3 jumpingTiles
                Tile[] tiles = new Tile[3];

                for(int i = 0; i < 3; i++){
                    int randomPositionX = rand.ints(1,63).findFirst().getAsInt();
                    int randomPositionY = rand.ints(1,63).findFirst().getAsInt();
                    Position p = new Position(randomPositionX, randomPositionY);
                    tiles[i] = new Tile(p, TypeTile.RANDOMPJUMP, Color.WHITE, false);
                    b.addTilePosition(p, tiles[i]);
                }
                return b;


            }
            if(gameLevel == 2){
                //random position cannot be 0,0 or 63,63
                //create new 3 FORGOTTEN tile
                Tile[] tiles1 = new Tile[3];
                for(int i = 0; i < 3; i++){
                    int randomPositionX = rand.ints(1,63).findFirst().getAsInt();
                    int randomPositionY = rand.ints(1,63).findFirst().getAsInt();
                    Position p = new Position(randomPositionX, randomPositionY);
                    tiles1[i] = new Tile(p, TypeTile.FORGOTTEN, Color.WHITE, false);
                    //gameBoard.removeTilePosition(p)
                    b.addTilePosition(p, tiles1[i]);
                }
                return b;

            }
            if (gameLevel == 3) {
                Tile[] tiles2 = new Tile[2];
                Tile[] tiles3 = new Tile[2];
                for(int i = 0; i < 2; i++){
                    int randomPositionX1 = rand.ints(1,63).findFirst().getAsInt();
                    int randomPositionY = rand.ints(1,63).findFirst().getAsInt();
                    Position p = new Position(randomPositionX1, randomPositionY);
                    tiles2[i] = new Tile(p, TypeTile.FORGOTTEN, Color.WHITE, false);
                    //gameBoard.removeTilePosition(p)
                    b.addTilePosition(p, tiles2[i]);
                }
                for(int i = 0; i < 2; i++){
                    int randomPositionX2 = rand.ints(1,63).findFirst().getAsInt();
                    int randomPositionY = rand.ints(1,63).findFirst().getAsInt();
                    Position p = new Position(randomPositionX2, randomPositionY);
                    tiles3[i] = new Tile(p, TypeTile.RANDOMPJUMP, Color.WHITE, false);
                    //gameBoard.removeTilePosition(p)
                    b.addTilePosition(p, tiles3[i]);
                }
                return b;

            }
            if(gameLevel == 4) {
                Tile[] tiles4 = new Tile[3];
                for(int i = 0; i < 3; i++){
                    int randomPositionX = rand.ints(1,63).findFirst().getAsInt();
                    int randomPositionY = rand.ints(1,63).findFirst().getAsInt();
                    Position p = new Position(randomPositionX, randomPositionY);
                    tiles4[i] = new Tile(p, TypeTile.BLOCKED, Color.RED, false);
                    b.addTilePosition(p, tiles4[i]);
                }
                return b;

            }

            else
                return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//        } catch (JsonMappingException e) {
//            throw new RuntimeException(e);
//        } catch (JsonParseException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return game;
//    }
//




}
