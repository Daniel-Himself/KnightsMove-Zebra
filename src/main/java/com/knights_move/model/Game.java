package com.knights_move.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Game {
    private int gameID;
    private Board gameBoard;
    private List<Question> question;
    private LocalDate dateOfGame;

    private HashMap<Game, Integer> scoreInGame;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private int currentLevelScore = 0;

    private int totalScoreInGame;
    private Boolean award;


    public Game(int gameID, Board gameBoard, List<Question> question) {
        this.gameID = gameID;
        this.gameBoard = gameBoard;
        this.question = new ArrayList<>();
        this.dateOfGame=java.time.LocalDate.now();
    }
    //construcor for json
    public Game(int gameID,LocalDate date) {
        this.gameID = gameID;
        this.dateOfGame = date;
    }


    public HashMap<Game, Integer> getScoreInGame() {
        return scoreInGame;
    }

    public void setScoreInGame(HashMap<Game, Integer> scoreInGame) {
        this.scoreInGame = scoreInGame;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


    public Game() {

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

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public List<Question> getQuestion() {
        return Collections.unmodifiableList(question);
    }

    public LocalDate getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    //using Factory Design Pattern
    public List<Figure> initFigureInStage(){
        try {
            //init the position of figures At the beginning of the stage
            /*Position p = new Position(0,0);
            Figure horse = new Figure(1, p,"horse", 0);*/
            FigureFactory figureFactory = new FigureFactory();
            Figure horse = (Figure) figureFactory.getFigure("horse");
            //System.out.println(horse);
            List<Figure> listOfFigures = new ArrayList<>();
            listOfFigures.add(horse);

            if(gameBoard.getBoardId() ==  1 && gameBoard.getBoardId() ==2){
                /*Position p2 = new Position(63,63);
                Figure queen = new Figure(3, p2,"queen", 0);*/
                Figure queen = (Figure) figureFactory.getFigure("queen");
                listOfFigures.add(queen);
            }
            else {
                /*Position p1 = new Position(63,63);
                Figure king = new Figure(2, p1,"king", 0);*/
                Figure king = (Figure) figureFactory.getFigure("king");
                listOfFigures.add(king);
            }
            return listOfFigures;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    //waiting for Question class
    //public void addQuestion
    //removeQuestion
    //editQuestion
  /*  public Board setSpecialTilesInLevel(int gameLevel, Board b){
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

    }*/
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
    //set Special tiles in level by given num of tiles - daniela
    public void setSpecialTilesInLevel(int numOfTiles) {
        try {
            ArrayList<Position> randomPositions = gameBoard.generateRandomPositions(numOfTiles);
            if(gameBoard.getBoardId() == 1) {
                for(Position p : randomPositions) {
                    Tile specialTile = gameBoard.getTileByPosition(p);
                    specialTile.setTileColor(Color.WHITE);
                    specialTile.setType(TypeTile.RANDOMPJUMP);
                    System.out.println("random ->" + specialTile);
                }
            }
            if(gameBoard.getBoardId() == 2) {
                for(Position p : randomPositions) {
                    Tile specialTile = gameBoard.getTileByPosition(p);
                    specialTile.setTileColor(Color.WHITE);
                    specialTile.setType(TypeTile.FORGOTTEN);
                    System.out.println("forgotten ->" + specialTile);
                }
            }
            if(gameBoard.getBoardId() == 3) {
                for(Position p : randomPositions) {
                    int count = 0;
                    while(count < 2){ //todo need to be changed according to board.getNumOfForgotten/random
                        Tile specialTile = gameBoard.getTileByPosition(p);
                        specialTile.setTileColor(Color.WHITE);
                        specialTile.setType(TypeTile.FORGOTTEN);
                        randomPositions.remove(p); //todo check if ensure unique numbers without duplicates
                        System.out.println("forgotten ->" + specialTile);
                    }
                }
                for(Position p : randomPositions) {
                    Tile specialTile = gameBoard.getTileByPosition(p);
                    specialTile.setTileColor(Color.WHITE);
                    specialTile.setType(TypeTile.RANDOMPJUMP);
                    System.out.println("random ->" + specialTile);
                }
            }
            if (gameBoard.getBoardId() == 4) {
                for(Position p : randomPositions) {
                    Tile specialTile = gameBoard.getTileByPosition(p);
                    specialTile.setTileColor(Color.RED);
                    specialTile.setType(TypeTile.BLOCKED);
                    System.out.println("blocked ->" + specialTile);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", gameBoard=" + gameBoard +
                ", question=" + question +
                ", dateOfGame=" + dateOfGame +
                '}';
    }





}