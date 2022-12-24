package com.knights_move.model;

import java.util.*;

public class Board {
    private int boardId;
    private int numOfForgottenTiles;
    private List<Tile> visitedTile;
    private List<Tile> emptyTile;
    private HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard;
    private HashMap<Position,Tile> tilePositions;

    public Board(int boardId, int numOfForgottenTiles, List<Tile> visitedTile, List<Tile> emptyTile , HashMap<Integer,HashMap<Position,Tile>> tilesPositionInBoard, HashMap<Position, Tile> tilePositions) {
        this.boardId = boardId;
        this.numOfForgottenTiles = numOfForgottenTiles;
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
        ArrayList<Integer> position = getRandoms(n);
        //ArrayList<Integer> positionY = getRandoms(n);
        ArrayList<Position> positions = new ArrayList<>();
        int x, y;
        //int maxValueY = 8;
        /*int x = num / 10000;
int y = num % 10000;*/
        for(int num: position) {
             /*x = (int)(num % 100) / 10;
             y = 7 - (int)(num % 100) / 10;*/
            //8 is mac value of x and y
            x = num / 8;
            y = num % 8;
            positions.add(new Position(x,y));
            System.out.println("myRandomTiles"+ x + " "+y);
            System.out.println(position.size());

        }
      /*  for(int num: positionY) {
            y = (int)(num % 100) / 10;
        }*/
       /* for (int num : tiles) {
            // int tens = (int)(number % 100) / 10;
            int y = num % 10;
            int x = (num - y) / 10;
            //todo from 0-63 to 0,0-7,7
            positions.add(new Position(x,y));
            System.out.println(x + " "+y);
        }*/
        //System.out.println("size"+ positions.size());

        System.out.println(position);
        System.out.println(positions);
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

}
