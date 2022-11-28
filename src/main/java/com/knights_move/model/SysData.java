package com.knights_move.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SysData {
    private static SysData instance=null;
    ArrayList<Game> games;
    ArrayList<Question> questions;

    //singleton constructor
    public static SysData getInstance()
    {
        if(instance==null)
        {
            // crate a singleton instance
            instance=new SysData();

            if(instance.DesJsonGame()&&instance.DesJsonQuestions())
            {
                System.out.println("the objects loaded successfully from file!");
           }
            else{
                System.out.println("there is an Error");
            }
        }
        return instance;
    }

    public ArrayList<Game> getGames() {
        return this.games;
    }
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }
    public void setQuestions(ArrayList<Question> questions) {this.questions = questions;}

    /**
     * save the system data
     * @return true if system data was saves successfully and false other
     */
    public boolean save()
    {
        try{
            serJsonQuestion();
            serJsonGames();
            return true;
        }
        catch (Exception e) {
            return false;

        }
    }

    /**loads the questions data from the json file,deserlizable
     *
     * @return true if the json question file  loaded to arraylist
     */

    public boolean DesJsonQuestions() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\noaas\\OneDrive\\שולחן העבודה\\KnightsMove\\lib\\QuestionsFormat.json")) {
            //read json file
            Object obj = jsonParser.parse(reader);
            //getting questions array from JSON object
            JSONArray quesArray= (JSONArray) ((JSONObject)obj).get("questions");
            for (Object object :quesArray ) {
                Question question=new Question();
                if(this.questions==null)
                {
                    this.questions=new ArrayList<>();
                }
                this.questions.add(question.fromJsonQuestion((JSONObject)object));
            }
            return true;
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**loads the Game data from the json file,deserlizable
     *
     * @return true if the json question file  loaded to arraylist
     */

    public boolean DesJsonGame() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Games.json")) {
            //read json file
            Object obj = jsonParser.parse(reader);
            //getting questions array from JSON object
            JSONArray questionsArray = ((JSONArray) (((JSONObject) obj).get("Games")));
            for (Object object : questionsArray) {
                Game game= new Game();
                if(this.games==null)
                {
                    this.games=new ArrayList<>();
                }
                this.games.add(game.fromJson((JSONObject) object));

            }
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * write arraylist to json games file, return true if its succeded
     * @return
     */
    public boolean serJsonGames()
    {
        try(FileWriter file=new FileWriter("Games.json")){
            //we can write any JSONArray or JSONobject instance to the file
            JSONObject gameobj=new JSONObject();
            JSONArray gamelist=new JSONArray();
            JSONObject listJson=new JSONObject();

            for (Game game : games) {
                gameobj.put("gameID", game.getGameID());
                gameobj.put("gameBoard", game.getGameBoard());
                gameobj.put("stage", game.getStageGame());
                gameobj.put("question", game.getQuestion());
                gamelist.add(gameobj);
                listJson.put("Game",gamelist);
            }
            file.write(listJson.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * write arraylist to json Question file, return true if its succeded
     * @return
     */
    public boolean serJsonQuestion()
    {
        try(FileWriter file=new FileWriter("Question.json")){
            //we can write any JSONArray or JSONobject instance to the file
            JSONObject questionObj=new JSONObject();
            JSONArray questionList=new JSONArray();
            JSONObject listJson=new JSONObject();

            for (Question question:questions) {
                questionObj.put("question", question.getQuesId());
                questionObj.put("answers", question.getAnswers());
                questionObj.put("correct_ans", question.getCorrect_answerID());
                questionObj.put("level", question.getLevel());
                questionObj.put("team", question.getTeamNick());

                questionList.add(questionObj);
                listJson.put("Game",questionList);
          }
            file.write(listJson.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    }
