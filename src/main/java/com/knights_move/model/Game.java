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


    public Game(int gameID, Board gameBoard) {
        this.gameID = gameID;
        this.gameBoard = gameBoard;
        this.question = new ArrayList<>();
        this.dateOfGame=java.time.LocalDate.now();
    }
    //constructor for json
    public Game(int gameID,LocalDate date) {
        this.gameID = gameID;
        this.dateOfGame = date;
    }

    public HashMap<Game, Integer> getScoreInGame() {
        return scoreInGame;
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
            FigureFactory figureFactory = new FigureFactory();
            Figure horse = (Figure) figureFactory.getFigure("horse");
            List<Figure> listOfFigures = new ArrayList<>();
            listOfFigures.add(horse);
            if(gameBoard.getBoardId() ==  1 || gameBoard.getBoardId() ==2){
                Figure queen = (Figure) figureFactory.getFigure("queen");
                listOfFigures.add(queen);
            }
            else {
                Figure king = (Figure) figureFactory.getFigure("king");
                listOfFigures.add(king);
            }
            return listOfFigures;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void setQuestionTiles() {
        try {
            int questionLimit = 3;
            int count = 0;
            ArrayList<Position> randomPositions = gameBoard.generateRandomPositions(questionLimit);
            if(this.getGameBoard().getBoardId() == 1) {
                Collections.shuffle(question);
            }
            for(Position p : randomPositions) {
                Tile specialTile = gameBoard.getTileByPosition(p);
                specialTile.setTileQuestion(question.get(count));
                System.out.println("question tile ->" + specialTile);
                count++;
            }
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
                int count = 0;
                for(Position p : randomPositions) {
                    Tile specialTile = gameBoard.getTileByPosition(p);
                    specialTile.setTileColor(Color.WHITE);
                    if(count < 2){
                        specialTile.setType(TypeTile.FORGOTTEN);
                        System.out.println("forgotten ->" + specialTile);
                        count++;
                    }
                    else {
                        specialTile.setType(TypeTile.RANDOMPJUMP);
                        System.out.println("random ->" + specialTile);
                    }
                }
            } // todo figure out duplicates
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