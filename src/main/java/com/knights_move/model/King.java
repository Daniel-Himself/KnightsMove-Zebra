package com.knights_move.model;

public class King extends Figure implements FigureInterface{
    public King(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //need to complete
    @Override
    public void move() {
        System.out.println("king");
    }
}
