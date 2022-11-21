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


    /*public void setVisitedTile(List<Tile> visitedTile) {
        this.visitedTile = visitedTile;
    }*/

    public List<Tile> getEmptyTile() {
        return Collections.unmodifiableList(emptyTile);
    }

    /*public void setEmptyTile(List<Tile> emptyTile) {
        this.emptyTile = emptyTile;
    }*/

    public boolean addVisitedTile(Tile tile){
        if(tile != null && tile.isVisited() && !visitedTile.contains(tile)) {
            visitedTile.add(tile);
            return true;
        }
        else
            return false;
    }

    public boolean removeVisitedTile(Tile tile){
        if(tile != null && visitedTile.contains(tile)) {
            tile.setVisited(false);
            visitedTile.remove(tile);
            return true;
        }
        else
            return false;
    }

    public boolean addEmptyTile(Tile tile){
        //64 tiles in board
        //add to list only if type is empty
        if(tile != null && (emptyTile.size() <= 64) && tile.getType().equals(TypeTile.EMPTY) && !emptyTile.contains(tile)){
            emptyTile.add(tile);
            return true;
        }
        else
            return false;
    }

    public boolean removeEmptyTile(Tile tile){
        if(tile != null && emptyTile.contains(tile)){
            emptyTile.remove(tile);
            return true;
        }
        else
            return false;
    }

    public HashMap<Position, Tile> getTilePositions() {
        return tilePositions;
    }
    //all tiles positions in each stage
    public boolean addTilePosition(Position p, Tile t){
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
    }

    public boolean removeTilePosition(Position p){
        if(p != null && tilePositions.containsKey(p)) {
            tilePositions.remove(p);
            return true;
        }
        return false;
    }

    // for each board that belong to game ->  HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard
    public boolean addTilesPositionToBoard (Position p, Tile t){
        if(!tilesPositionInBoard.containsKey(boardId)) {
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
        }
        return false;
    }

}
