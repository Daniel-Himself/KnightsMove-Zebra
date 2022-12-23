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
}