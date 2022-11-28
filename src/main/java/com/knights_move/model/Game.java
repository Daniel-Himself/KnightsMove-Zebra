package com.knights_move.model;
import javafx.geometry.Pos;
import org.codehaus.jackson.JsonParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.*;

public class Game {
    private int gameID;
    private Board gameBoard;
    private Stage stageGame;
    private List<Question> question;

    public Game(int gameID, Board gameBoard, Stage stageGame, List<Question> question) {
        this.gameID = gameID;
        this.gameBoard = gameBoard;
        this.stageGame = stageGame;
        this.question = new ArrayList<>();
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

    public Stage getStageGame() {
        return stageGame;
    }

    public void setStageGame(Stage stageGame) {
        this.stageGame = stageGame;
    }

    public List<Question> getQuestion() {
        return Collections.unmodifiableList(question);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", gameBoard=" + gameBoard +
                ", stageGame=" + stageGame +
                ", question=" + question +
                '}';
    }

    public List<Figure> initFigureInStage(int stageNumber){
        try {
            //init the position of figures At the beginning of the stage
            Position p = new Position(0,0);
            Figure horse = new Figure(1, p,"horse", 0);
            List<Figure> listOfFigures = new ArrayList<>();
            listOfFigures.add(horse);

            if(stageNumber >= 1 && stageNumber <=2){
                Position p2 = new Position(63,63);
                Figure queen = new Figure(3, p2,"queen", 0);
                listOfFigures.add(queen);
            }
            else {
                Position p1 = new Position(63,63);
                Figure king = new Figure(2, p1,"king", 0);
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
    public Game fromJson(JSONObject obj){
        JSONArray jsonArray=(JSONArray)((JSONObject)obj).get("questions");
        ArrayList<Question> quesArray= new ArrayList<>();
        for(Object object:jsonArray) {
            quesArray.add((Question) object);
        }
        int gameID= (int) obj.get("gameID");
        Board Boardgame= (Board) obj.get("gameBoard");
        Stage stageGame= (Stage) obj.get("stage");
        Game game= new Game(gameID,Boardgame,stageGame,quesArray);
        return game;
    }
    /**
    /**
     * second method for convert json to java object, another option for do it
     * @return
     */
    public Game fromJson(){
        ObjectMapper mapper=new ObjectMapper();
        Game game;
        try{
            //convert JSON string from file to Object
             game=mapper.readValue("Games.json", Game.class);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    /**
     * java object to JSON
     */
    public void toJson(Game game)
    {
        ObjectMapper mapper=new ObjectMapper();
        try{
            mapper.writeValue(new File("Games.json"),game);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
