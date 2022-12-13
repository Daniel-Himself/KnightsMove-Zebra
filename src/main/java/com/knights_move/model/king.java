package com.knights_move.model;

public class king extends Figure implements FigureInterface{
    public king(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //need to complete
    @Override
    public void move() {
        System.out.println("king");
    }
}
