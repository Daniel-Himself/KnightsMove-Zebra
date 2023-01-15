package com.knights_move.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {
    private int figureId;
    private Position position;
    private String typeOfFigure;
    private int speedRate;

    public abstract Position move(Position a, Position b);

    public Figure(int figureId, Position position, int speedRate) {
        this.figureId = figureId;
        this.position = position;
        this.speedRate = speedRate;
    }


    // method returns list of possible horse movements
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
            Position validPosition = validPosition(pp);
            if(validPosition != null) {
                    Tile t = board.getTileByPosition(validPosition);
                    if(t.getType() != TypeTile.BLOCKED){
                        optionList.add(validPosition);
                    }
            }
        }
        System.out.println("Horse options List -> " + optionList);
        return optionList;
    }

    public List<Position> horseOptionsLevel2(Position current, Board board){
        List<Position> optionList = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();
        List<Position> possiblePos = new ArrayList<>();
        //two squares straight and one diagonally
        possiblePos.add(new Position(x + 1, y + 3));
        possiblePos.add(new Position(x + 1, y - 3));
        possiblePos.add(new Position(x - 1, y + 3));
        possiblePos.add(new Position(x - 1, y - 3));

        possiblePos.add(new Position(x + 3, y + 1));
        possiblePos.add(new Position(x + 3, y - 1));
        possiblePos.add(new Position(x - 3, y + 1));
        possiblePos.add(new Position(x - 3, y - 1));

        //two squares diagonally and one straight
        possiblePos.add(new Position(x + 2, y - 1));
        possiblePos.add(new Position(x + 2, y + 1));
        possiblePos.add(new Position(x - 2, y - 1));
        possiblePos.add(new Position(x - 2, y + 1));

        possiblePos.add(new Position(x + 2, y - 2));
        possiblePos.add(new Position(x + 2, y + 2));
        possiblePos.add(new Position(x - 2, y - 2));
        possiblePos.add(new Position(x - 2, y + 2));

        possiblePos.add(new Position(x + 2, y - 3));
        possiblePos.add(new Position(x + 2, y + 3));
        possiblePos.add(new Position(x - 2, y - 3));
        possiblePos.add(new Position(x - 2, y + 3));

        for(Position pp: possiblePos){
            Position validPosition = validPosition(pp);
            if(validPosition != null) {
                Tile t = board.getTileByPosition(validPosition);
                if(t.getType() != TypeTile.BLOCKED){
                    optionList.add(validPosition);
                }
            }
        }
        System.out.println("Horse options level 2 List -> " + optionList);
        return optionList;

    }

    // checking valid position on board with attendance to cyclic movements
    private Position validPosition(Position p){
        int x = p.getX();
        int y = p.getY();
        if(x <= 7 && x >= 0 && y <= 7 && y >= 0){
            return p;
        }

        if((x < 0 && x >= -2) && (y <= 7 && y >= 0)) {
            x = x + 8;
            p.setX(x);
            return p;
        }
        if((x > 7 && x <= 9) && (y <= 7 && y >= 0)) {
            x = x - 8;
            p.setX(x);
            return p;
        }
        if((y < 0 && y >= -2) && (x <= 7 && x >= 0)){
            y = y + 8;
            p.setY(y);
            return p;
        }
        if((y > 7 && y <= 9) && (x <= 7 && x >= 0)){
            y = y - 8;
            p.setY(y);
            return p;
        }
        if((y > 7 && y <= 9) && (x > 7 && x <= 9)){
            y = y - 8;
            x = x - 8;
            p.setY(y);
            p.setX(x);
            return p;
        }
        if((y < 0 && y >= -2) && (x < 0 && x >= -2)){
            y = y + 8;
            x = x + 8;
            p.setY(y);
            p.setX(x);
            return p;
        }
        return null;
    }


    public abstract boolean canAttack(Position position, Position queenCurrPosition);

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
}