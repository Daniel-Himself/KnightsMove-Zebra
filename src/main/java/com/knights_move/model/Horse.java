package com.knights_move.model;

public class Horse extends Figure implements FigureInterface{

    public Horse(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //need to complete
    @Override
    public void move() {
        System.out.println("horse");

    }
}
