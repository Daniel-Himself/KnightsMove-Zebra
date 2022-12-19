package com.knights_move.model;

public class Queen extends Figure implements FigureInterface{
    public Queen(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
        // TODO set speedRate to 0 for queen
    }
    //need to complete
    @Override
    public Position move(Position a, Position b) {
        System.out.println("queen");
        Position position = null; //TODO need to complete
        return position;
    }
}