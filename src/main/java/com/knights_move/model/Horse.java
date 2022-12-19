package com.knights_move.model;

public class Horse extends Figure implements FigureInterface{

    public Horse(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //need to complete
    @Override
    public Position move(Position a, Position b) {
        System.out.println("horse");
        Position position = null; //TODO need to complete
        return position;
    }
}