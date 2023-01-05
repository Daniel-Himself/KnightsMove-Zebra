package com.knights_move.model;

import com.knights_move.controller.EditHistoryController;
import com.knights_move.controller.HistoryController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SysData {
    private static SysData instance = null;

    private static HashMap<Player, ArrayList<Game>> playerAndgames;
    private ArrayList<Question> questions;
    private String username; // a string that holds the username of the current user

    private ArrayList<EditHistoryController.historyEdit> listOfChange=new ArrayList<>();


    //singleton constructor
    public static SysData getInstance() {
        if (instance == null) {
            // crate a singleton instance
            instance = new SysData();
            instance.DesJsonQuestions();
            instance.DesJsonGame();
            instance.DesJsonChange();
        }
        return instance;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public HashMap<Player, ArrayList<Game>> getPlayerAndgames() {
        return playerAndgames;
    }

    public void setPlayerAndgames(HashMap<Player, ArrayList<Game>> playerAndgames) {
        if(this.getPlayerAndgames()==null)
        {
            this.playerAndgames= new HashMap<>();
        }
        for(Player p:playerAndgames.keySet())
        {
            this.getPlayerAndgames().put(p,playerAndgames.get(p));
        }
    }
    public ArrayList<EditHistoryController.historyEdit> getListOfChange() {
        return listOfChange;
    }

    public void setListOfChange(ArrayList<EditHistoryController.historyEdit> listOfChange) {
        this.listOfChange = listOfChange;
    }

    /**
     * save the system data
     *
     * @return true if system data was saves successfully and false other
     */
    public boolean save() {
        try {
            serJsonQuestion();
            serJsonGames();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    /**
     * return question by quesId, return null if there is no questions with this qusId
     */
    public Question getQuestionByName(String quesId) {
        for (Question question : this.questions) {
            if (question.getQuesId().compareTo(quesId) == 0)
                return question;
        }
        return null;
    }

    /**
     * loads the questions data from the json file,deserlizable
     *
     * @return true if the json question file  loaded to arraylist
     */

    public void DesJsonQuestions() {
        File file = new File(".\\lib\\QuestionsFormat.json");
        if (file.length() == 0) {
            System.out.println("the file is empty");
            return;
        }
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            //read json file
            Object obj = jsonParser.parse(reader);
            //getting questions array from JSON object
            JSONArray quesArray = (JSONArray) ((JSONObject) obj).get("questions");
            if (quesArray != null) {
                for (Object object : quesArray) {
                    Question question = new Question();
                    if (this.questions == null) {
                        this.questions = new ArrayList<>();
                    }
                    this.questions.add(question.fromJsonQuestion((JSONObject) object));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * loads the Game data from the json file,deserlizable
     *
     * @return true if the json question file  loaded to arraylist
     */
    public void DesJsonGame() {
        File file = new File(".\\lib\\Games.json");
        if (file.length() == 0) {
            System.out.println("the file is empty");
            return;
        }
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            //read json file
            Object obj = jsonParser.parse(reader);
            //getting questions array from JSON object
            JSONArray PlayersArray = ((JSONArray) (((JSONObject) obj).get("Players")));
            if(PlayersArray!=null) {
                for (Object object : PlayersArray) {
                    //now we iterate through player and games
                    //get the player
                    Player player = new Player(((JSONObject) object).get("player").toString());
                    //get the games the player took part
                    JSONArray arrayGamesJson = (JSONArray) ((JSONObject) object).get("Games");
                    ArrayList<Game> gameOfCurrentPlayer = new ArrayList<Game>();
                    //we iterate through json object
                    for (Object games : arrayGamesJson) {
                        int gameID = Integer.valueOf(((JSONObject) games).get("gameId").toString());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                        LocalDate dateOfGame = LocalDate.parse((((JSONObject) games).get("DateOfGame").toString()), formatter);
                        int score = Integer.valueOf(((JSONObject) games).get("score").toString());
                        Game newGame = new Game(gameID, dateOfGame,score);
                        gameOfCurrentPlayer.add(newGame);
                    }
                    if (this.playerAndgames == null) {
                        playerAndgames = new HashMap<>();
                    }
                    playerAndgames.put(player, gameOfCurrentPlayer);
                }

            }
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
     *
     * @return
     */
    public boolean serJsonGames() {
        try (FileWriter file = new FileWriter(".\\lib\\Games.json")) {
            //we can write any JSONArray or JSONobject instance to the file
            JSONArray playerlist = new JSONArray();
            JSONObject listPlayerJson = new JSONObject();
            for(Player p:getPlayerAndgames().keySet())
            {
                if(p.equals(new Player(SysData.getInstance().getUsername())))
                {
                    for(Game g:playerAndgames.get(p))
                    {
                        System.out.println(g.toString());
                    }
                }
                JSONObject playerObj = new JSONObject();
                ArrayList<Game> gameValue = getPlayerAndgames().get(p);
                if(gameValue!=null&&!(gameValue.isEmpty()))
                {
                    JSONArray gamelist = new JSONArray();
                    for(Game game: gameValue)
                    {
                        JSONObject gameobj = new JSONObject();
                        gameobj.put("gameId", game.getGameID());
                        gameobj.put("DateOfGame", game.getDateOfGame().toString());
                        gameobj.put("score", game.getTotalScoreInGame());
                        gamelist.add(gameobj);
                    }
                    playerObj.put("player", p.getUserName());
                    playerObj.put("Games", gamelist);
                    playerlist.add(playerObj);
                }
            }
            listPlayerJson.put("Players", playerlist);
            file.write(listPlayerJson.toJSONString());
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
        try(FileWriter file=new FileWriter(".\\lib\\QuestionsFormat.json")){
            //we can write any JSONArray or JSONobject instance to the file
            //JSONObject questionObj=new JSONObject();
            JSONArray questionList=new JSONArray();
            JSONObject listJson=new JSONObject();

            for (Question question:this.getQuestions()) {
                JSONObject questionObj=new JSONObject();

                questionObj.put("question", question.getQuesId());
                questionObj.put("answers", question.returnContent());
                questionObj.put("correct_ans", question.getCorrect_answerID());
                questionObj.put("level", question.getLevel());
                questionObj.put("team", question.getTeamNick());

                questionList.add(questionObj);
            }
            listJson.put("questions",questionList);
            file.write(listJson.toJSONString());
            file.flush();

            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public HashMap<Integer, ArrayList<Question>> getQuestionByLevel(int level) {
        System.out.println(questions);
//        if(questions.isEmpty())
//            return null;
//        else {
//            Collections.sort(questions, (q1, q2) -> (q1.getLevel() > q2.getLevel()) ? 1 : 0);
//        }
        HashMap<Integer, ArrayList<Question>> questionByLevel = new HashMap<>();
        if (level == 1) {
            for (Question q : questions) {
                ArrayList<Question> questionList = new ArrayList<>();
                if (q.getLevel() == 1) {
                    questionList.add(q);
                    questionByLevel.put(q.getLevel(), questionList);
                }

            }
//            System.out.println(questionByLevel.get(1).size());
        }
        if (level == 2) {
            for (Question q : questions) {
                ArrayList<Question> questionList = new ArrayList<>();
                if (q.getLevel() == 2) {
                    questionList.add(q);
                    questionByLevel.put(q.getLevel(), questionList);
                }
            }
        } else {
            for (Question q : questions) {
                ArrayList<Question> questionList = new ArrayList<>();
                if (q.getLevel() == 3) {
                    questionList.add(q);
                    questionByLevel.put(q.getLevel(), questionList);
                }
            }
        }
        if(questionByLevel.isEmpty())
            return null;
        else
            return questionByLevel;
    }


    /*save the history of changes by manager*/
    public void DesJsonChange() {
        File file = new File(".\\lib\\historyChanges.json");
        if (file.length() == 0) {
            System.out.println("the file is empty");
            return;
        }
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            //read json file
            Object obj = jsonParser.parse(reader);
            //getting questions array from JSON object
            JSONArray changeArray = (JSONArray) ((JSONObject) obj).get("changes");
            if (changeArray != null) {
                for (Object change : changeArray) {
                     String quesId=String.valueOf(((JSONObject)change).get("quesId").toString());
                     STATUS type=STATUS.DELETE;
                     for(STATUS status:STATUS.values())
                     {
                         if((((JSONObject) change).get("status").toString()).compareTo(status.toString())==0)
                         {
                             type=status;
                         }
                     }
                     String changeDescription=String.valueOf(((JSONObject) change).get("change").toString());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                    LocalDate dateOf = LocalDate.parse((((JSONObject) change).get("DateOfChange").toString()),formatter);
                    EditHistoryController.historyEdit newChange= new EditHistoryController.historyEdit(quesId,type,changeDescription,dateOf);
                    listOfChange.add(newChange);
                }
            }
        }
         catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * write arraylist to json games file, return true if its succeded
     *
     * @return
     */
    public boolean serJsonChange()
    {
        try(FileWriter file=new FileWriter(".\\lib\\historyChanges.json")){
            //we can write any JSONArray or JSONobject instance to the file
            //JSONObject questionObj=new JSONObject();
            JSONArray changesList=new JSONArray();
            JSONObject listJson=new JSONObject();
            if(getListOfChange()!=null) {
                for (EditHistoryController.historyEdit change : getListOfChange()) {
                    JSONObject changeObj = new JSONObject();

                    changeObj.put("quesId", change.getQuesId());
                    changeObj.put("status", change.getType().toString());
                    changeObj.put("change", change.getChanges().toString());
                    changeObj.put("DateOfChange", change.getDateof().toString());

                    changesList.add(changeObj);
                }
                listJson.put("changes", changesList);
                file.write(listJson.toJSONString());
                file.flush();

                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}