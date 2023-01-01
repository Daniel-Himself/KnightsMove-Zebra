package com.knights_move.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    @Test
    void initFigureInStage() {
        Board board = new Board(1, 0, 0, 0);
        Game game = new Game(1,board);
        List<Figure> initFigureInStage1 = game.initFigureInStage();
        for(Figure f : initFigureInStage1) {
            assertTrue(f.getClass().toString().contains("Horse") ||f.getClass().toString().contains("Queen"));
            assertFalse(f.getClass().toString().contains("King"));
        }

        board.setBoardId(2);
        game.setGameBoard(board);
        List<Figure> initFigureInStage2 = game.initFigureInStage();

        for(Figure f : initFigureInStage2) {
            assertTrue(f.getClass().toString().contains("Horse") ||f.getClass().toString().contains("Queen"));
            assertFalse(f.getClass().toString().contains("King"));
        }

        board.setBoardId(3);
        game.setGameBoard(board);
        List<Figure> initFigureInStage3 = game.initFigureInStage();

        for(Figure f : initFigureInStage3) {
            assertTrue(f.getClass().toString().contains("Horse") ||f.getClass().toString().contains("King"));
            assertFalse(f.getClass().toString().contains("Queen"));
        }

        board.setBoardId(4);
        game.setGameBoard(board);
        List<Figure> initFigureInStage4 = game.initFigureInStage();

        for(Figure f : initFigureInStage4) {
            assertTrue(f.getClass().toString().contains("Horse") ||f.getClass().toString().contains("King"));
            assertFalse(f.getClass().toString().contains("Queen"));
        }

    }


}