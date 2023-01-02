package com.knights_move.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure implements FigureInterface{
    public King(int figureId, Position position, int speedRate) {
        super(figureId, position, speedRate);
    }
    //need to complete
    @Override
    public Position move(Position horseCuPosition, Position kingCuPosition) {
        List<Position> potentialMoves= new ArrayList<>();
        int colX=kingCuPosition.getX();
        int rowY=kingCuPosition.getY();

        int colHorse=horseCuPosition.getX();
        int rowHorse=horseCuPosition.getY();

        ArrayList<Integer> optionForRows= new ArrayList<>();
        ArrayList<Integer> optionForCols=new ArrayList<>();

        double minDistance=1000;
        Position closerHourse=new Position(1000,10000);

        switch (rowY) {
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
                optionForRows.add(rowY);
                optionForRows.add(rowY + 1);
                optionForRows.add(rowY - 1);
            }
        }
        switch (colX) {
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
                optionForCols.add(colX);
                optionForCols.add(colX + 1);
                optionForCols.add(colX - 1);
            }
        }
        for(int i=0;i<optionForRows.size();i++)
        {
            for (int j=0;j<optionForCols.size();j++)
            {
                if(!(optionForRows.get(i)==rowY&&optionForCols.get(j)==colX))
                potentialMoves.add(new Position(optionForCols.get(j),optionForRows.get(i)));
            }
        }

        for(Position position:potentialMoves)
        {
            int x=Math.abs(position.getX()-colHorse);
            int y=Math.abs(position.getY()-rowHorse);

            double currentDis=Math.sqrt(Math.abs(y*y-x*x));

            if (Double.valueOf(currentDis)<Double.valueOf(minDistance))
            {
                minDistance=currentDis;
                closerHourse.setX(position.getX());
                closerHourse.setY(position.getY());
            }
        }
      return closerHourse;
    }
}