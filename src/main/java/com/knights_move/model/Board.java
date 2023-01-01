package com.knights_move.model;

import java.util.*;

public class Board {
    private int boardId; //equal to level number
    private int numOfForgottenTiles;
    private int numOfRandomJumpTiles;
    int numOfBlockedTiles;
    private List<Tile> visitedTile;
    private List<Tile> emptyTile;
    private List<Tile> tileList;
    private LinkedList<Position> lastThreePositions;
    private LinkedList<Integer> lastThreeScoreChange;
    private HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard;
    private HashMap<Position,Tile> tilePositions;


    //todo - fix board controller constructors
    public Board(int boardId, int numOfForgottenTiles, int numOfBlockedTiles, int numOfRandomJumpTiles) {
        //boardID represents level number in game. for each level in game(range 1 - 4) -> new board.
        if(this.boardId <= 1 && this.boardId <= 4) {
            this.boardId = boardId;
        }
        else
            this.boardId = -1;
        this.numOfRandomJumpTiles = numOfRandomJumpTiles;
        this.numOfForgottenTiles = numOfForgottenTiles;
        this.numOfBlockedTiles = numOfBlockedTiles;
        this.emptyTile = new ArrayList<>();
        this.tileList = new ArrayList<>();
        this.visitedTile = new ArrayList<>();
        this.tilesPositionInBoard = new HashMap<>();
        this.tilePositions = new HashMap<>();
        this.lastThreePositions = new LinkedList<>();
        this.lastThreeScoreChange = new LinkedList<>();
    }

    public Board(int boardId) {
        this.boardId = boardId;
        if(this.boardId <= 1 && this.boardId <= 4) {
            this.boardId = boardId;
        }
        else this.boardId = -1;
        this.emptyTile = new ArrayList<>();
        this.tileList = new ArrayList<>();
        this.visitedTile = new ArrayList<>();
        this.tilesPositionInBoard = new HashMap<>();
        this.tilePositions = new HashMap<>();
        this.lastThreePositions = new LinkedList<>();
        this.lastThreeScoreChange = new LinkedList<>();
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public LinkedList<Position> getLastThreePositions() {
        return lastThreePositions;
    }

    public LinkedList<Integer> getLastThreeScoreChange() {
        return lastThreeScoreChange;
    }
    public HashMap<Integer, HashMap<Position, Tile>> getTilesPositionInBoard() {
        return tilesPositionInBoard;
    }

    public List<Tile> getVisitedTile() { return visitedTile;}

    public int getNumOgSpecialTilesByLevel(int level){
        if(level == 1 || level == 2){
            return 3;
        }
        if(level == 3){
            return 4;
        }
        else{
            return 8;
        }
    }

    public Tile getTileByPosition(Position p){
        for(Tile t: getTileList()){
            if(t.getTilePosition().equals(p)){
                return t;
            }
        }
        return null;
    }

    //keep tracking last 3 horse positions
    public void updateLastThreePositions(Position position){
        if(lastThreePositions.size() < 3){
            lastThreePositions.add(position);
        }
        else{
            lastThreePositions.removeFirst();
            lastThreePositions.add(position);
        }
    }

    public void updateLastThreeScoreChange(int score){
        if(lastThreeScoreChange.size() < 3){
            lastThreeScoreChange.add(score);
        }
        else{
            lastThreeScoreChange.removeFirst();
            lastThreeScoreChange.add(score);
        }
    }

    public List<Tile> getEmptyTile() {
        return emptyTile;
    }

    private ArrayList<Integer> getRandoms(int num){
        ArrayList list = new ArrayList();
        Random rand = new Random();
        int pick;
        for (int j = 0; j < num; j++) {
            pick = rand.nextInt(64);
            if(!list.contains(pick))
                list.add(pick);
            else
                num++;
        }
        System.out.println(list.size());
        return list;
    }

    public ArrayList<Position> generateRandomPositions(int n){
        ArrayList<Integer> position = getRandoms(n);
        ArrayList<Position> positions = new ArrayList<>();
        int x, y;
        for(int num: position) {
            x = num / 8;
            y = num % 8;
            positions.add(new Position(x,y));
        }
        return positions;
    }

    public boolean addVisitedTile(Tile tile){
        try {
            if(tile != null && tile.isVisited() && !visitedTile.contains(tile)) {
                visitedTile.add(tile);
                return true;
            }
            else
                return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean removeVisitedTile(Tile tile){
        try {
            if(tile != null && visitedTile.contains(tile)) {
                tile.setVisited(false);
                visitedTile.remove(tile);
                System.out.println("visited tile" + tile.getTilePosition() + "removed from visitedTile list");
                System.out.println("visitedTile list" + visitedTile);
                return true;
            }
            else
                return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean addEmptyTile(Tile tile){
        try {
            //64 tiles in board
            //add to list only if type is empty
            if(tile != null && (emptyTile.size() <= 64) && tile.getType().equals(TypeTile.EMPTY) && !emptyTile.contains(tile)){
                emptyTile.add(tile);
                return true;
            }
            else
                return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean removeEmptyTile(Tile tile){
        try {
            if(tile != null && emptyTile.contains(tile)){
                emptyTile.remove(tile);
                return true;
            }
            else
                return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public HashMap<Position, Tile> getTilePositions() {
        return tilePositions;
    }
    //all tiles positions in each stage
    public boolean addTilePosition(Position p, Tile t){
        try {
            if(p != null && t != null && !tilePositions.containsKey(p))
            {
                //position range of x -> 0 till 7, y -> 0 till 7.
                if(p.getX() < 8 && p.getY() < 8) {
                    tilePositions.put(p,t);
                    return true;
                }
                else
                    return false;
            }
            else
                return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean removeTilePosition(Position p){
        try {
            if(p != null && tilePositions.containsKey(p)) {
                tilePositions.remove(p);
                return true;
            }
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public void setTileList(List<Tile> tileList) {
        this.tileList = tileList;
    }

    public int getNumOfForgottenTiles() {
        return numOfForgottenTiles;
    }

    public void setNumOfForgottenTiles(int numOfForgottenTiles) {
        this.numOfForgottenTiles = numOfForgottenTiles;
    }

    public int getNumOfRandomJumpTiles() {
        return numOfRandomJumpTiles;
    }

    public void setNumOfRandomJumpTiles(int numOfRandomJumpTiles) {
        this.numOfRandomJumpTiles = numOfRandomJumpTiles;
    }

    public void setNumOfBlockedTiles(int numOfBlockedTiles) {
        this.numOfBlockedTiles = numOfBlockedTiles;
    }

    public int getNumOfBlockedTiles() {
        return numOfBlockedTiles;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", visitedTile=" + visitedTile +
                ", lastThreePositions=" + lastThreePositions +
                ", lastThreeScoreChange=" + lastThreeScoreChange +
                '}';
    }
}
