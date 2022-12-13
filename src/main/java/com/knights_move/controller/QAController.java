package com.knights_move.controller;

import com.knights_move.model.Answer;
import com.knights_move.model.Question;
import com.knights_move.model.SysData;
import com.knights_move.view.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QAController implements Initializable {
    public class NewQuestion
    {
        private String quesID;
        private ArrayList<Answer> answers;
        private int correct_answerID;
        private int level;
        private String teamNick;

        public NewQuestion(String quesID, ArrayList<Answer> answers, int correct_answerID, int level, String teamNick) {
            this.quesID = quesID;
            this.answers = answers;
            this.correct_answerID = correct_answerID;
            this.level = level;
            this.teamNick = teamNick;
        }

        public String getQuesID() {
            return quesID;
        }

        public void setQuesID(String quesID) {
            this.quesID = quesID;
        }

        public ArrayList<Answer> getAnswers() {
            return answers;
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
    }

    @FXML
    private Button button_delete;
    @FXML
    private Button button_edit;
    @FXML
    private Button addTeam;

    @FXML
    private AnchorPane anchorPane_center;

    @FXML
    private Button button_new_answer1;

    @FXML
    private Button button_new_answer2;

    @FXML
    private Button button_new_answer3;

    @FXML
    private Button button_new_answer4;

    @FXML
    private Button button_save;
    @FXML
    private TableView<NewQuestion> TableView_Question;

    @FXML
    private TableColumn<NewQuestion, String> column_question;

    @FXML
    private TableColumn<NewQuestion, String> column_answer1;

    @FXML
    private TableColumn<NewQuestion, String> column_answer2;

    @FXML
    private TableColumn<NewQuestion, String> column_answer3;

    @FXML
    private TableColumn<NewQuestion, String> column_answer4;

    @FXML
    private TableColumn<NewQuestion, Integer> column_level;

    @FXML
    private TableColumn<NewQuestion, String> column_team;
    @FXML
    private TableColumn<NewQuestion, String>column_correctAns;
    @FXML
    private TableColumn<NewQuestion, String> column_Id_hidden;

    @FXML
    private ComboBox<String> combo_Team;

    @FXML
    private ComboBox<String> combo_answer1;

    @FXML
    private ComboBox<String> combo_answer2;

    @FXML
    private ComboBox<String> combo_answer3;

    @FXML
    private ComboBox<String> combo_answer4;

    @FXML
    private ComboBox<String> combo_level;
    @FXML
    private ComboBox<String> combo_correctAns;

    @FXML
    private Button exitBtn;

    @FXML
    private Button historyBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Label label_title;

    @FXML
    private Button logoBtn;

    @FXML
    private Pane pane_top;

    @FXML
    private Button playBtn;

    @FXML
    private Button qaBtn;

    @FXML
    private Button rulesBtn;

    @FXML
    private Button signInBtn;

    @FXML
    private TextField text_question;
    FXMLLoader loader;
    boolean EditMode=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTeam();
        initCorrectAnswer();
        initlevel();
        initTable();

        button_new_answer1.setOnAction(e->{
            showAnswers();
        });
        button_new_answer2.setOnAction(e->{
            showAnswers();
        });
        button_new_answer3.setOnAction(e->{
            showAnswers();
        });
        button_new_answer4.setOnAction(e->{
            showAnswers();
        });

        initAnswer();
        combo_answer1.setOnMouseClicked(e->{
            initAnswer();
        });

        button_save.setOnAction(e->{
            insertNewRow();
        });
        button_edit.setOnAction(e->{
            insertNewRow();
        });
        button_delete.setOnAction(e->{
            System.out.println("heyyy");
            deleteQuestion();
        });
    }
    private void initTable()
    {
        System.out.println("heyyykkkkkkkkkk");

        column_question.setCellValueFactory(new PropertyValueFactory<>("Question"));
        column_answer1.setCellValueFactory(new PropertyValueFactory<>("Answer1"));
        column_answer2.setCellValueFactory(new PropertyValueFactory<>("Answer2"));
        column_answer3.setCellValueFactory(new PropertyValueFactory<>("Answer3"));
        column_answer4.setCellValueFactory(new PropertyValueFactory<>("Answer4"));
        column_correctAns.setCellValueFactory(new PropertyValueFactory<>("CorrectAns"));
        column_level.setCellValueFactory(new PropertyValueFactory<>("Level"));
        column_team.setCellValueFactory(new PropertyValueFactory<>("Team"));

        List<QAController.NewQuestion> newQuestionsList= new ArrayList<>();
        if( SysData.getInstance().getQuestions()!=null&& !(SysData.getInstance().getQuestions().isEmpty())) {
            for (Question q : SysData.getInstance().getQuestions()) {
                newQuestionsList.add(new NewQuestion(q.getQuesId(), q.getAnswers(), q.getCorrect_answerID(), q.getLevel(), q.getTeamNick()));
            }
            ObservableList<QAController.NewQuestion> observableList = FXCollections.observableArrayList(newQuestionsList);
            TableView_Question.setItems(observableList);
        }
    }
    public void deleteQuestion()
    {
        NewQuestion selected=TableView_Question.getSelectionModel().getSelectedItem();
        String quesId=selected.getQuesID();
        ArrayList<Question> questions=SysData.getInstance().getQuestions();
        questions.remove(SysData.getInstance().getQuestionByName(quesId));
        SysData.getInstance().setQuestions(questions);
        System.out.println("heiii");
        System.out.println(SysData.getInstance().getQuestions());
    }
    public String editQuestion()
    {
        NewQuestion selected=TableView_Question.getSelectionModel().getSelectedItem();
        text_question.setText(selected.getQuesID());
        combo_answer1.getSelectionModel().select(selected.getAnswers().get(1).getContent());
        combo_answer2.getSelectionModel().select(selected.getAnswers().get(2).getContent());
        combo_answer3.getSelectionModel().select(selected.getAnswers().get(3).getContent());
        combo_answer4.getSelectionModel().select(selected.getAnswers().get(4).getContent());
        combo_correctAns.getSelectionModel().select(selected.getCorrect_answerID());
        //for me-> check the cast from int to string
        combo_level.getSelectionModel().select(String.valueOf(selected.getLevel()));
        combo_Team.getSelectionModel().select(String.valueOf(selected.getTeamNick()));
        button_delete.setVisible(false);
        button_new_answer1.setVisible(false);
        this.EditMode=true;
        return text_question.getText();
    }
    private void insertNewRow(){
        String questionId = this.text_question.getText();
        ArrayList<Answer> answer = new ArrayList<>();
        Answer a1 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
        Answer a2 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
        Answer a3 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
        Answer a4 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
        answer.add(a1);
        answer.add(a2);
        answer.add(a3);
        answer.add(a4);
        int correct_ans = Integer.valueOf(this.combo_correctAns.getSelectionModel().getSelectedItem());
        int level = Integer.valueOf((this.combo_level.getSelectionModel().getSelectedItem()));
        String team = this.combo_Team.getSelectionModel().getSelectedItem();
            //check if the questions exist
            if(EditMode==false) {
                if (SysData.getInstance().getQuestions() != null && !(SysData.getInstance().getQuestions().isEmpty())) {
                    if (SysData.getInstance().getQuestionByName(questionId) != null) {
                        HelloApplication.alertError("Error", "This questions content exist");
                    }
                    Question newQuestion = new Question(questionId, answer, correct_ans, level, team);
                    SysData.getInstance().getQuestions().add(newQuestion);
                }
            }
            else if(EditMode){
                String quesId=editQuestion();
                Question q= SysData.getInstance().getQuestionByName(quesId);
                q.setQuesId(questionId);
                q.setAnswers(answer);
                q.setCorrect_answerID(correct_ans);
                q.setLevel(level);
                q.setTeamNick(team);
            }
        initTable();
    }
    private void initAnswer()
    {
        combo_answer1.getSelectionModel().clearSelection();
        combo_answer2.getSelectionModel().clearSelection();
        combo_answer3.getSelectionModel().clearSelection();
        combo_answer4.getSelectionModel().clearSelection();

        if(AnswersController.getAllAnswer()!=null){
                if(!(AnswersController.getAllAnswer().isEmpty())) {
                    List<Answer> array = new ArrayList<>();
                    List<String> answerContent = new ArrayList<>();
                    for(AnswersController.NewAnswer NewAnswer:AnswersController.getAllAnswer())
                        array.add(new Answer(NewAnswer.getContent()));

                    for (Answer answer : array) {
                        answerContent.add(answer.getContent());
                    }
                    ObservableList observableList = FXCollections.observableArrayList(answerContent);
                    combo_answer1.setItems(observableList);
                    combo_answer2.setItems(observableList);
                    combo_answer3.setItems(observableList);
                    combo_answer4.setItems(observableList);
                }
        }
    }

    public void initTeam() {
        combo_Team.getSelectionModel().clearSelection();
        List<String> TeamUniqelist = new ArrayList<String>();
        if(SysData.getInstance().getQuestions()!=null){
            for (Question question : SysData.getInstance().getQuestions()) {
                String team = question.getTeamNick();
                if (!TeamUniqelist.contains(team)) {
                    TeamUniqelist.add(team);
                }
            }
        }
        //convert list to ObservableList list
        //JavaFX ObservableList is JavaFX SDK’s special implementation of List interface.
        ObservableList observableList = FXCollections.observableArrayList(TeamUniqelist);
        combo_Team.setItems(observableList);
    }

    public void initlevel() {
        combo_level.getSelectionModel().clearSelection();
        List<String> levelList = new ArrayList<String>();
        for(int i=1;i<5;i++)
        {
            levelList.add(String.valueOf(i));
        }
        //convert list to ObservableList list
        //JavaFX ObservableList is JavaFX SDK’s special implementation of List interface.
        ObservableList observableList = FXCollections.observableArrayList(levelList);
        combo_level.setItems(observableList);
    }
    public void initCorrectAnswer() {
        combo_correctAns.getSelectionModel().clearSelection();
        List<String> CorrectList = new ArrayList<String>();
        for(int i=1;i<5;i++)
        {
            CorrectList.add(String.valueOf(i));
        }
        //convert list to ObservableList list
        //JavaFX ObservableList is JavaFX SDK’s special implementation of List interface.
        ObservableList observableList = FXCollections.observableArrayList(CorrectList);
        combo_correctAns.setItems(observableList);
    }
    private void showAnswers()
    {
        try{
            HelloApplication.loadPage("Answers.fxml");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}