package com.knights_move.model;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Game {
    private int gameID;
    private Board gameBoard;
    private List<Question> question;
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
    }

    public Game() {

    }

    public void setQuestion(List<Question> question) {
        this.question = question;
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

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public List<Question> getQuestion() {
        return Collections.unmodifiableList(question);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", gameBoard=" + gameBoard +
                ", question=" + question +
                '}';
    }
    //using Factory Design Pattern
    public List<Figure> initFigureInStage(int stageNumber){
        try {
            //init the position of figures At the beginning of the stage
            /*Position p = new Position(0,0);
            Figure horse = new Figure(1, p,"horse", 0);*/
            FigureFactory figureFactory = new FigureFactory();
            Figure horse = (Figure) figureFactory.getFigure("horse");
            //System.out.println(horse);
            List<Figure> listOfFigures = new ArrayList<>();
            listOfFigures.add(horse);

            if(stageNumber >= 1 && stageNumber <=2){
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

    public HashMap<Game, Integer> getScoreInGame() {
        return scoreInGame;
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


    public boolean addTotalScore() {
        try {
            if (currentLevelScore != -1) {
                totalScoreInGame += currentLevelScore;
                boolean result = this.addScoreOfPlayer(this, totalScoreInGame);
                if (result)
                    return true;
                else
                    return false;
            } else
                return false;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //init game -> stage one
    public void initGame(){
        try {
            setStartTime(java.time.LocalDateTime.now());
            HashMap<Integer, HashMap<Position, Tile>> tilesPositionInBoard = new HashMap<>();
            //Board b = new Board(game.getGameID(),tilesPositionInBoard);
            //the func returns list of init positions.
            Position[] positionList = this.getGameBoard().initPosition();
            int sizeOfBoard = 64;
            Tile[] tileList = new Tile[sizeOfBoard];
            // init empty tile
            for(int i = 0; i < sizeOfBoard ; i++){
                for (Position p: positionList) {
                    tileList[i] = new Tile(p, TypeTile.EMPTY, Color.WHITE, false);
                    this.getGameBoard().addEmptyTile(tileList[i]);
                }

            }
            System.out.print("position list" + positionList);
            System.out.print("tile List" + tileList);
            //init with first stage
            Board boardOfGame = this.setSpecialTilesInLevel(1, this.getGameBoard());

            List<Figure> figurePosition = this.initFigureInStage(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public boolean finishStageInGame() {
        try {
            if(startTime != null && finishTime != null) {
                if(finishTime.getMinute() - startTime.getMinute() >= 1 || currentLevelScore >= 15) {
                    currentLevelScore = 0;
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //public boolean addPlayer()
    //public removePlayer

    //public int changeSpeed(){}




    public Game fromJson(JSONObject obj){
        JSONArray jsonArray=(JSONArray)((JSONObject)obj).get("questions");
        ArrayList<Question> quesArray= new ArrayList<>();
        for(Object object:jsonArray) {
            quesArray.add((Question) object);
        }
        int gameID= (int) obj.get("gameID");
        Board Boardgame= (Board) obj.get("gameBoard");
        Game game= new Game(gameID,Boardgame,quesArray);
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
