package com.knights_move.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    void setSpecialTilesInLevel() {
        // public List<Tile> setSpecialTilesInLevel(int numOfTiles)
        //int gameID, Board gameBoard, List<Question> question
        //int boardId, int numOfForgottenTiles, int numOfBlockedTiles, int numOfRandomJumpTiles
        Board b1 = new Board(1, 0, 0, 0);
        Game game = new Game(1,b1, null);
        List<Tile> setSpecialTilesInLevel = game.setSpecialTilesInLevel(3);

        for(Tile t : setSpecialTilesInLevel) {
            //System.out.println(t);
            assertTrue(t.getType() == TypeTile.RANDOMPJUMP);
        }

        Board b2 = new Board(2, 0, 0, 0);
        game.setGameBoard(b2);
        List<Tile> setSpecialTilesInLevel1 = game.setSpecialTilesInLevel(3);

        for(Tile t : setSpecialTilesInLevel1) {
            System.out.println(t);
            assertTrue(t.getType() == TypeTile.FORGOTTEN);
        }

        Board b3 = new Board(3, 0, 0, 0);
        game.setGameBoard(b3);
        List<Tile> setSpecialTilesInLevel2 = game.setSpecialTilesInLevel(3);

        for(Tile t : setSpecialTilesInLevel2) {
            //System.out.println(t);
            assertTrue(t.getType() == TypeTile.FORGOTTEN || t.getType() == TypeTile.RANDOMPJUMP);
        }

        Board b4 = new Board(4, 0, 0, 0);
        game.setGameBoard(b4);
        List<Tile> setSpecialTilesInLevel4 = game.setSpecialTilesInLevel(3);

        for(Tile t : setSpecialTilesInLevel4) {
            //System.out.println(t);
            assertTrue(t.getType() ==  TypeTile.BLOCKED);
        }
        //should be null, boardID rage between 1-4.
        Board b5 = new Board(5, 0, 0, 0);
        game.setGameBoard(b5);
        List<Tile> setSpecialTilesInLevel5 = game.setSpecialTilesInLevel(3);

       assertTrue(setSpecialTilesInLevel5 == null);



    }

    @Test
    void initFigures() {
        //List<Figure> initFigures()
        Board b1 = new Board(1, 0, 0, 0);
        Game game = new Game(1,b1, null);
        List<Figure> figures = game.initFigures();
        for(Figure f: figures) {
            System.out.println(f.getClass().toString());
            assertTrue(f.getClass().toString().contains("Horse") || f.getClass().toString().contains( "Queen") || f.getClass().toString().contains("King"));
        }

        for(Figure f: figures) {
            assertFalse(f.getClass().toString().contains("Figure"));
        }

    }

}