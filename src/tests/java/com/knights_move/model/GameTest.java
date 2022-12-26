package com.knights_move.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    @Test
    void initFigureInStage() {
    }

    @Test
    void setSpecialTilesInLevel() {
        // public List<Tile> setSpecialTilesInLevel(int numOfTiles)
        //int gameID, Board gameBoard, List<Question> question
        //int boardId, int numOfForgottenTiles, int numOfBlockedTiles, int numOfRandomJumpTiles
        Board b1 = new Board(1, 0, 0, 0);
        Game game = new Game(1,b1, null);
        List<Tile> setSpecialTilesInLevel = game.setSpecialTilesInLevel(3);

        for(Tile t : setSpecialTilesInLevel) {
            System.out.println(t);
            assertTrue(t.getType() == TypeTile.RANDOMPJUMP);
        }

        //assertTrue(setSpecialTilesInLevel.size() == 3);
        //assertTrue(setSpecialTilesInLevel.);

    }

    @Test
    void initFigures() {
    }

    @Test
    void testSetSpecialTilesInLevel() {
    }
}