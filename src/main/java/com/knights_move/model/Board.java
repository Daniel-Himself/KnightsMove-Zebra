package com.knights_move.model;
import java.util.*;

import static com.knights_move.model.TypeTile.RANDOMPJUMP;

public class Board {
    private int boardId;
    private int numOfForgottenTiles;
    private int numOfBlockedTiles;
    private int numOfRandomJumpTiles;
    private List<Tile> visitedTile;
    private List<Tile> emptyTile;
    private List<Tile> tileList;
    private HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard;
    private HashMap<Position,Tile> tilePositions;

    public Board(int boardId, int numOfForgottenTiles, int numOfBlockedTiles, int numOfRandomJumpTiles) {
        this.boardId = boardId;
        this.numOfRandomJumpTiles = numOfRandomJumpTiles;
        this.numOfBlockedTiles = numOfBlockedTiles;
        this.numOfForgottenTiles = numOfForgottenTiles;
        this.emptyTile = new ArrayList<>();
        this.tileList = new ArrayList<>();
        this.visitedTile = new ArrayList<>();
        this.tilesPositionInBoard = new HashMap<>();
        this.tilePositions = new HashMap<>();

    }

    public Board(int boardId, HashMap<Integer, HashMap<Position, Tile>> tilesPositionInBoard) {
        this.boardId = boardId;
        this.tilesPositionInBoard = new HashMap<>();
        this.tilePositions = new HashMap<>();
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }


    public HashMap<Integer, HashMap<Position, Tile>> getTilesPositionInBoard() {
        return tilesPositionInBoard;
    }

    public List<Tile> getVisitedTile() { return visitedTile;}

    public Tile getTileByPosition(Position p){
        for(Tile t: getTileList()){
            if(t.getTilePosition().equals(p)){
                return t;
            }
        }
        return null;
    }

    public List<Tile> getEmptyTile() {
        return emptyTile;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", visitedTile=" + visitedTile +
                ", emptyTile=" + emptyTile +
                ", tilesPositionInBoard=" + tilesPositionInBoard +
                ", tilePositions=" + tilePositions +
                '}';
    }

    private ArrayList<Integer> getRandoms(int num){
        ArrayList list = new ArrayList();
        Random rand = new Random();
        int pick;
        for (int j = 0; j < num; j++) {
            pick = rand.nextInt(64);
            list.add(pick);
        }
  //      System.out.println(list);
        return list;
    }

    public ArrayList<Position> generateRandomPositions(int n){
        ArrayList<Integer> tiles = getRandoms(n);
        ArrayList<Position> positions = new ArrayList<>();
        for (int num : tiles) {
            int y = num % 10;
            int x = (num - y) / 10;
            //todo from 0-63 to 0,0-7,7
            positions.add(new Position(x,y));
      //      System.out.println(x + " "+y);
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
                //position range of x -> 0 till 63, y -> 0 till 63.
                if(p.getX() < 64 && p.getY() <64) {
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

    // for each board that belong to game ->  HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard
    // if the key is already in the map, override with new value.
    public boolean addTilesPositionToBoard (Position p, Tile t){
        try {
            if(p != null && t != null){
                boolean result = addTilePosition(p,t);
                if(result){
                    tilesPositionInBoard.put(boardId, tilePositions);
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

    //init positions values.
    public Position[] initPosition(){
        try {
            int sizeOfBoard = 64;
            Position[] positionList = new Position[64];
            for(int i = 0; i < sizeOfBoard ; i++){
                for(int j = 0 ; j < sizeOfBoard ; j++){
                    positionList[i] = new Position(i, j);
                }
            }
            return positionList;

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
}
