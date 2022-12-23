package com.knights_move.model;

import java.util.List;

public class King extends Figure implements FigureInterface{
    public King(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //need to complete
    @Override
    public Position move(Position a, Position b) {
        System.out.println("king");
        Position position = null; //TODO need to complete
        return position;
    }
}