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
        int colY=kingCuPosition.getY();
        int rowX=kingCuPosition.getX();

        int colHorse=horseCuPosition.getY();
        int rowHorse=horseCuPosition.getX();

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
            for (int j=0;j<optionForCols.size();j++)
            {
                if(!(optionForRows.get(i)==rowX&&optionForCols.get(j)==colY)) {
                    Position newPosition=new Position(optionForRows.get(i), optionForCols.get(j));
                    potentialMoves.add(newPosition);
                }
            }
        }


        for(Position position:potentialMoves)
        {System.out.println("king valid position (Noa) ->" +position);
            int x=Math.abs(position.getX()-colHorse);
            int y=Math.abs(position.getY()-rowHorse);

            double currentDis=Math.sqrt(Math.abs(y*y-x*x));
            if(position.equals(horseCuPosition))
            {
                closerHourse=position;
            }

          else  if (Double.valueOf(currentDis)<Double.valueOf(minDistance))
            {
                minDistance=currentDis;
                closerHourse.setX(position.getX());
                closerHourse.setY(position.getY());
            }
        }
      return closerHourse;
    }
}