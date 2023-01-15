package com.knights_move.model;

public class Queen extends Figure implements FigureInterface{
    public Queen(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //need to complete

    // Method to check if the queen can attack another piece at a given row and column on the board
    public boolean canAttack(Position queenPosition, Position opponentPosition) {

        int queenX = queenPosition.getX();
        int queenY = queenPosition.getY();

        int targetX = opponentPosition.getX();
        int targetY = opponentPosition.getY();

        // The queen can attack in any of the eight directions (horizontally, vertically, or diagonally)
        // as long as there are no pieces blocking her path.
        // Check if the target is in the same row, column, or diagonal as the queen

        // Queen can move horizontally, vertically, or diagonally, so check if the knight is in any of these directions
        if (queenX == targetX || queenY == targetY || Math.abs(queenX - targetX) == Math.abs(queenY - targetY)) {
            System.out.println("Queen can attack");
            return true;
        }


        // If the knight is not in any of the above positions, the queen can attack it
        System.out.println("Queen can't attack, moving closer to opponent");
        return false;

    }

    @Override
    // Method to move the queen closer to the target piece
    public Position move(Position opponentPosition, Position queenPosition) {
        System.out.println("opponentPosition ->" + opponentPosition);
        // If possible, attack the horse
        if(canAttack(queenPosition, opponentPosition)) {
            return opponentPosition;
        }

        // Calculate the difference in rows and columns between the queen and the target piece
        int row = queenPosition.getX();
        int col = queenPosition.getY();

        int targetRow = opponentPosition.getX();
        int targetCol = opponentPosition.getY();

        int rowDiff = targetRow - row;
        int colDiff = targetCol - col;

        // If the queen is not already in the same row or column as the target piece,
        // move the queen towards the target piece by incrementing or decrementing the row or column coordinate
        // by the maximum number of steps that can be taken in that direction without going past the target piece

        int maxSteps = 7; // maximum number of steps that can be taken in any direction

        if (row != targetRow) {
            int steps = Math.min(Math.abs(rowDiff), maxSteps) -1;
            System.out.println("row steps ->" + steps);
            row += steps * (rowDiff / Math.abs(rowDiff));
        }
        if (col != targetCol) {
            int steps = Math.min(Math.abs(colDiff), maxSteps) -1;
            System.out.println("col steps ->" + steps);
            col += steps * (colDiff / Math.abs(colDiff));
        }
        Position newPosition = new Position(row, col);
        // Return the new position closer to the horse if queen is unable to attack*/
        System.out.println("new position queen" + newPosition);
        return newPosition;
    }


}