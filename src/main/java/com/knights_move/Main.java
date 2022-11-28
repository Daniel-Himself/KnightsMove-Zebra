package com.knights_move;

import com.knights_move.model.Question;
import com.knights_move.model.SysData;
import com.knights_move.view.HelloApplication;

public class Main {
    public static void main(String[] args) {
        HelloApplication.main(args);
        SysData instance= new SysData();
        System.out.println(instance.DesJsonQuestions());
        HelloApplication.main(args);
        System.out.println( Question.fromJson2());


        // ArrayList<Answer> array=new ArrayList<>();
        //array.add(new Answer("answer1"));
        //array.add(new Answer("answer1"));
        //array.add(new Answer("answer1"));
        //array.add(new Answer("answer1"));
        // Question questions=new Question("q3",);
    }
}