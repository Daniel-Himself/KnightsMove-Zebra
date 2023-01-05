package com.knights_move.model;

public class Horse extends Figure implements FigureInterface{

    public Horse(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //ignore
    @Override
    public boolean canAttack(Position position, Position queenCurrPosition) {
        return false;
    }

    // horse move has different return type and implemented at figure class
    @Override
    public Position move(Position horse, Position b) {
        return null;
    }


}