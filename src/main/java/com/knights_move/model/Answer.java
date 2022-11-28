package com.knights_move.model;

public class Answer {
    private static int answerID=0;
    private String content;

    public Answer(String content) {
        this.answerID +=1;
        this.content = content;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return  content ;

    }
}
