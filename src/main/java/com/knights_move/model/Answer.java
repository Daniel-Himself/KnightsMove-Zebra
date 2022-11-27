package com.knights_move.model;

public class Answer {
    private int answerID;
    private String content;

    public Answer(int answerID, String content) {
        this.answerID = answerID;
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
}
