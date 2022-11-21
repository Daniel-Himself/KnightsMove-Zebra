package com.knights_move.model;
import java.util.*;

public class Board {
    private int boardId;
    private int height;
    private int width;
    private List<Tile> visitedTile;
    private List<Tile> emptyTile;

    public Board(int boardId, int height, int width, List<Tile> visitedTile, List<Tile> emptyTile) {
        this.boardId = boardId;
        this.height = height;
        this.width = width;
        this.visitedTile = new ArrayList<>();
        this.emptyTile = new ArrayList<>();
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
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
        if(tile != null && tile.isVisited()) {
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
        if(tile != null && (emptyTile.size() <= 64) && tile.getType().equals(TypeTile.EMPTY)){
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

}
