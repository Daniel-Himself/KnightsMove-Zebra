package com.knights_move.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void canAttack() {
        // Create a new queen at position (3, 3)
        Queen queen = new Queen(1, new Position(3, 3), 0);

        // Test when the opponent is in the same row
        Position opponentPosition = new Position(5, 3);
        boolean canAttack = queen.canAttack(queen.getPosition(), opponentPosition);
        assertTrue(canAttack);

        // Test when the opponent is in the same column
        opponentPosition = new Position(3, 5);
        canAttack = queen.canAttack(queen.getPosition(), opponentPosition);
        assertTrue(canAttack);

        // Test when the opponent is in the same diagonal
        opponentPosition = new Position(4, 4);
        canAttack = queen.canAttack(queen.getPosition(), opponentPosition);
        assertTrue(canAttack);

        // Test when the opponent is not in the same row, column, or diagonal
        opponentPosition = new Position(6, 7);
        canAttack = queen.canAttack(queen.getPosition(), opponentPosition);
        assertFalse(canAttack);

    }

    @Test
    void move() {
        // Test moving the queen in the same row as the target piece
        Position queenPosition = new Position(3, 5);
        Queen queen = new Queen(2, queenPosition, 0);
        Position opponentPosition = new Position(5, 6);
        Position expectedPosition = new Position(4, 6);
        assertEquals(expectedPosition, queen.move(queenPosition, opponentPosition));
        // TODO check why this test fails even though the expected result equals to actual result
    }
}