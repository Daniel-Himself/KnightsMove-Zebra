package com.knights_move.model;
import java.util.*;

public class Figure {
    private int figureId;
    private Position position;
    private String typeOfFigure;
    private int speedRate;

    public Figure(int figureId, Position position, String typeOfFigure, int speedRate) {
        this.figureId = figureId;
        this.position = position;
        this.typeOfFigure = typeOfFigure;
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

    public String getTypeOfFigure() {
        return typeOfFigure;
    }

    public void setTypeOfFigure(String typeOfFigure) {
        this.typeOfFigure = typeOfFigure;
    }

    public int getSpeedRate() {
        return speedRate;
    }

    public void setSpeedRate(int speedRate) {
        this.speedRate = speedRate;
    }
}
