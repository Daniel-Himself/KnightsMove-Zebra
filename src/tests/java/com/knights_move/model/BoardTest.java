package com.knights_move.model;

import org.junit.jupiter.api.Test;

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
    void removeVisitedTile() {
        //should return true. tile is added to the list and not null.
        Position p1 = new Position(1,1);
        Tile tile1 = new Tile(p1, TypeTile.EMPTY, Color.WHITE, true);
        board.addVisitedTile(tile1);
        boolean removeVisitedTile = board.removeVisitedTile(tile1);
        assertTrue(removeVisitedTile);
        assertTrue(tile1.isVisited() == false);

        //should return false. tile is not in the list therefore it cannot be removed.
        Position p2 = new Position(2,1);
        Tile tile2 = new Tile(p2, TypeTile.EMPTY, Color.WHITE, true);
        //board.addVisitedTile(tile1);
        boolean removeVisitedTile1 = board.removeVisitedTile(tile2);
        assertFalse(removeVisitedTile1);

        //should return false. tile is null.
        boolean removeVisitedTile2 = board.removeVisitedTile(null);
        assertFalse(removeVisitedTile2);
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


}