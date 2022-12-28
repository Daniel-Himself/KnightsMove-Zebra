package com.knights_move.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {
    private int figureId;
    private Position position;
    private String typeOfFigure;
    private int speedRate;
    //todo - fix it cause no access to queens method move
    public abstract Position move(Position a, Position b);

    public Figure(int figureId, Position position, int speedRate) {
        this.figureId = figureId;
        this.position = position;
        this.speedRate = speedRate;
    }


    public int getFigureId() {
        return figureId;
    }

    public void setFigureId(int figureId) {
        this.figureId = figureId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSpeedRate() {
        return speedRate;
    }

    public void setSpeedRate(int speedRate) {
        this.speedRate = speedRate;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "figureId=" + figureId +
                ", position=" + position +
                ", typeOfFigure='" + typeOfFigure + '\'' +
                ", speedRate=" + speedRate +
                '}';
    }

    public List<Position> horseOptions(Position current, Board board) {
        List<Position> optionList = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();
        List<Position> possiblePos = new ArrayList<>();
        possiblePos.add(new Position(x + 1, y + 2));
        possiblePos.add(new Position(x + 1, y - 2));
        possiblePos.add(new Position(x - 1, y + 2));
        possiblePos.add(new Position(x - 1, y - 2));
        possiblePos.add(new Position(x + 2, y - 1));
        possiblePos.add(new Position(x + 2, y + 1));
        possiblePos.add(new Position(x - 2, y - 1));
        possiblePos.add(new Position(x - 2, y + 1));
        for(Position pp: possiblePos){
            if(validPosition(pp)){
                Tile t = board.getTileByPosition(pp);
                if(!t.isVisited()){
                    optionList.add(pp);
                }
            }
        }
        return optionList;
    }
    private boolean validPosition(Position p){
        int x = p.getX();
        int y = p.getY();
        if(x <= 7 && x >= 0 && y <= 7 && y >= 0){
            return  true;
        }
        return false;
    }
}