package com.knights_move.model;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Question {
    private String quesID;
    private ArrayList<Answer> answers;
    private int correct_answerID;
    private int level;
    private String teamNick;

    public Question(String quesId,ArrayList<Answer> answers, int correct_answerID, int level, String teamNick) {
        this.quesID= quesId;
        this.answers = answers;
        if(answers.size()==4) {
            this.correct_answerID = correct_answerID;
        }
        else return;
        if(level>0&&level<5) {
            this.level = level;
        }
        else return;
        this.teamNick = teamNick;
    }

    public Question() {

    }

    public boolean checkAnswer( int answer){
        if(this.correct_answerID == answer){
            return true;
        }
        return false;
    }

    public ArrayList<String> returnContent()
    {
        ArrayList<String> content= new ArrayList<>();
        for (int i=0;i<answers.size();i++)
        {
                content.add(answers.get(i).getContent());
        }
        return content;

    }
    public String getQuesId() {
        return this.quesID;
    }

    public void setQuesId(String quesId) {
        this.quesID = quesId;
    }

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getCorrect_answerID() {
        return correct_answerID;
    }

    public void setCorrect_answerID(int correct_answerID) {
        this.correct_answerID = correct_answerID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTeamNick() {
        return teamNick;
    }

    public void setTeamNick(String teamNick) {
        this.teamNick = teamNick;
    }

    public Question fromJsonQuestion(JSONObject questionJson)
    {

        JSONArray arrayAnswerJson=(JSONArray)questionJson.get("answers");
        ArrayList<Answer> answerArray=new ArrayList<Answer>();
        for(Object obj:arrayAnswerJson)
        {
            answerArray.add((new Answer(obj.toString())));
        }
        String quesId= (questionJson.get("question").toString());
        int correct_ans=Integer.valueOf(questionJson.get("correct_ans").toString());
        int level=Integer.valueOf(questionJson.get("level").toString());
        String teamNick=questionJson.get("team").toString();
        Question question=new Question(quesId,answerArray,correct_ans,level,teamNick);
        return question;
     }
    /**
     * second method for convert json to java object, another option for do it
     * @return
     */
    //delete
    public static Question fromJson2(){
        ObjectMapper mapper=new ObjectMapper();
        Question question;
        try{
            //convert JSON string from file to Object
            question=mapper.readValue("QuestionsFormat.json", Question.class);
            System.out.println(question.getQuesId());

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return question;
    }

    /**
     * java object to JSON
     */
    public void toJson (Question question)
    {
        ObjectMapper mapper=new ObjectMapper();
        try{
            mapper.writeValue(new File("Question.json"),question);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "quesID='" + quesID + '\'' +
                ", answers=" + answers +
                ", correct_answerID=" + correct_answerID +
                ", level=" + level +
                ", teamNick='" + teamNick + '\'' +
                '}';
    }
}