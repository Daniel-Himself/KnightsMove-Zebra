package com.knights_move.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure implements FigureInterface{
    public King(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }

    //ignore
    @Override
    public boolean canAttack(Position position, Position queenCurrPosition) {
        return false;
    }

    //need to complete
    @Override
    public Position move(Position horseCuPosition, Position kingCuPosition) {
        List<Position> potentialMoves= new ArrayList<>();
        int rowX=kingCuPosition.getX();
        int colY=kingCuPosition.getY();

        int rowHorse=horseCuPosition.getX();
        int colHorse=horseCuPosition.getY();

        ArrayList<Integer> optionForRows= new ArrayList<>();
        ArrayList<Integer> optionForCols=new ArrayList<>();

        double minDistance=1000;
        Position closerHourse=new Position(1000,10000);

        switch (rowX) {
            case 0: {
                optionForRows.add(0);
                optionForRows.add(7);
                optionForRows.add(1);
                break;
            } case  (7): {
                optionForRows.add(0);
                optionForRows.add(7);
                optionForRows.add(6);
                break;
            } default: {
                optionForRows.add(rowX);
                optionForRows.add(rowX + 1);
                optionForRows.add(rowX - 1);
            }
        }
        switch (colY) {
            case (0): {
                optionForCols.add(0);
                optionForCols.add(7);
                optionForCols.add(1);
                break;
            } case (7): {
                optionForCols.add(0);
                optionForCols.add(7);
                optionForCols.add(6);
                break;
            } default: {
                optionForCols.add(colY);
                optionForCols.add(colY + 1);
                optionForCols.add(colY - 1);
            }
        }
        for(int i=0;i<optionForRows.size();i++)
        {
            for(int j=0;j<optionForCols.size();j++)
            {
                if(!(optionForRows.get(i)==rowX&&optionForCols.get(j)==colY)) {
                    Position newPosition=new Position(optionForRows.get(i), optionForCols.get(j));
                    potentialMoves.add(newPosition);
                }
            }
        }


        for(Position position:potentialMoves)
        {
            System.out.println("king valid position  ->" +position);
            double currentDis=Math.abs(position.getX()-rowHorse)+Math.abs(position.getY()-colHorse);

            if(position.equals(horseCuPosition))
            {
                closerHourse.setX(horseCuPosition.getX());
                closerHourse.setY(horseCuPosition.getY());
                return closerHourse;
            }

          else if(Double.valueOf(currentDis)<Double.valueOf(minDistance))
            {
                minDistance=currentDis;
                closerHourse.setX(position.getX());
                closerHourse.setY(position.getY());
            }
        }
      return closerHourse;
    }
}