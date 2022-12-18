package com.knights_move.model;
import java.util.*;

public class Board {
    private int boardId;
    private int height;
    private int width;
    private List<Tile> visitedTile;
    private List<Tile> emptyTile;
    private HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard;
    private HashMap<Position,Tile> tilePositions;

    public Board(int boardId, int height, int width, List<Tile> visitedTile, List<Tile> emptyTile , HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard, HashMap<Position, Tile> tilePositions) {
        this.boardId = boardId;
        this.height = height;
        this.width = width;
        this.visitedTile = new ArrayList<>();
        this.emptyTile = new ArrayList<>();
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


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<Tile> getVisitedTile() {
        return Collections.unmodifiableList(visitedTile);
    }

    public List<Tile> getEmptyTilesList() {
        return emptyTile;
    }

    public List<Tile> getEmptyTile() {
        return Collections.unmodifiableList(emptyTile);
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", height=" + height +
                ", width=" + width +
                ", visitedTile=" + visitedTile +
                ", emptyTile=" + emptyTile +
                ", tilesPositionInBoard=" + tilesPositionInBoard +
                ", tilePositions=" + tilePositions +
                '}';
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

}
