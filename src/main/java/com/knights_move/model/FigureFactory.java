package com.knights_move.model;

public class FigureFactory {
    //use getFigure to get figure type
    public FigureInterface getFigure(String figureType) {
        if(figureType == null)
            return null;

        if(figureType.equalsIgnoreCase("HORSE")){
            Position p0 = new Position(0,0);
            return new Horse(1, p0, 0);
        }

        else if(figureType.equalsIgnoreCase("KING")){
            Position p1 = new Position(63,63);
            return new King(2 ,p1 ,0);
        }
        else if(figureType.equalsIgnoreCase("QUEEN")) {
            Position p3 = new Position(7,7);
            return new Queen(2, p3, 0);
        }

        return null;
    }
}