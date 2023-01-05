package com.knights_move.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {
    Board board = new Board(1, 0,0,0);
    //public boolean addVisitedTile(Tile tile)
    //Tile(Position tilePosition, TypeTile type, Color tileColor, boolean isVisited)
    @Test
    void addVisitedTile() {
        Position p = new Position(1,1);
        Tile tile = new Tile(p, TypeTile.EMPTY, Color.WHITE, false);
        //boolean canAttack = queen.canAttack(queen.getPosition(), opponentPosition);
        boolean addVisited = board.addVisitedTile(tile);
        assertFalse(addVisited);

        Position p1 = new Position(1,1);
        Tile tile1 = new Tile(p1, TypeTile.EMPTY, Color.WHITE, true);
        //boolean canAttack = queen.canAttack(queen.getPosition(), opponentPosition);
        boolean addVisited1 = board.addVisitedTile(tile1);
        assertTrue(addVisited1);

        //Position p1 = new Position(1,1);
        //Tile tile2 = null;
        //boolean canAttack = queen.canAttack(queen.getPosition(), opponentPosition);
        boolean addVisited2 = board.addVisitedTile(null);
        assertFalse(addVisited2);



    }
    @Test
    void removeEmptyTile() {
        Position p = new Position(1,1);
        Tile tile = new Tile(p, TypeTile.EMPTY, Color.WHITE, true);
        board.addEmptyTile(tile);
        Boolean removeEmptyTile = board.removeEmptyTile(tile);
        assertTrue(removeEmptyTile);

        p.setX(5);
        p.setY(3);
        tile.setTilePosition(p);
        Boolean removeEmptyTile0 = board.removeEmptyTile(tile);
        assertFalse(removeEmptyTile0);

        p.setX(2);
        p.setY(3);
        tile.setType(TypeTile.FORGOTTEN);
        tile.setTilePosition(p);
        Boolean removeEmptyTile1 = board.removeEmptyTile(tile);
        assertFalse(removeEmptyTile1);


        Boolean removeEmptyTile2 = board.removeEmptyTile(null);
        assertFalse(removeEmptyTile2);

    }

    //public Tile getTileByPosition(Position p)
    @Test
    void getTileByPosition() {
        //test case 1
        Position p1 = new Position(1,1);
        Tile tile1 = new Tile(p1, TypeTile.EMPTY, Color.WHITE, true);
        board.getTileList().add(tile1);
        Tile getTileByPosition = board.getTileByPosition(p1);
        assertTrue(getTileByPosition != null);
        assertTrue(getTileByPosition.getTilePosition() == p1);

        //test case 2 - should return true(null)  -> the object is not in the list.
        Position p2 = new Position(2,2);
        Tile getTileByPosition1 = board.getTileByPosition(p2);
        assertTrue(getTileByPosition1 == null);
    }

    // public ArrayList<Position> generateRandomPositions(int n)
    @Test
    void generateRandomPositions() {
        ArrayList<Position> p1 = board.generateRandomPositions(8);
        System.out.println(p1);

    }


}