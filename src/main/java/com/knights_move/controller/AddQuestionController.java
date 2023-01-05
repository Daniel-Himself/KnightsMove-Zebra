package com.knights_move.controller;

import com.knights_move.model.*;
import com.knights_move.view.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.awt.*;
import java.awt.event.InputEvent;

public class AddQuestionController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Button addTeam;
    @FXML
    private Label message;
    @FXML
    private Button button_save;

    @FXML
    private ComboBox<String> combo_Team;
    @FXML
    private TextField textFiled_answer1;

    @FXML
    private TextField textFiled_answer2;

    @FXML
    private TextField textFiled_answer3;

    @FXML
    private TextField textFiled_answer4;

    @FXML
    private ComboBox<String> combo_correctAns;

    @FXML
    private ComboBox<String> combo_level;

    @FXML
    private Label label_title;

    @FXML
    private Pane pane_top;

    @FXML
    private TextField text_question;
    @FXML
    private static boolean EditMode=false;
    public static boolean getEditMode() {
        return EditMode;
    }
    public static void setEditMode(boolean editMode) {
        EditMode = editMode;
    }
    public static QuestionAnswerController.NewQuestion newQuestion;
    public static QuestionAnswerController.NewQuestion getNewQuestion() {
        return newQuestion;
    }

    public static void setNewQuestion(QuestionAnswerController.NewQuestion newQuestion) {
        AddQuestionController.newQuestion = newQuestion;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(EditMode)
        {
            text_question.setText(newQuestion.getQuesID());
            textFiled_answer1.setText(newQuestion.getAnswers().get(0).getContent());
            textFiled_answer2.setText(newQuestion.getAnswers().get(1).getContent());
            textFiled_answer3.setText(newQuestion.getAnswers().get(2).getContent());
            textFiled_answer4.setText(newQuestion.getAnswers().get(3).getContent());
            combo_correctAns.getSelectionModel().select(String.valueOf(newQuestion.getCorrect_answerID()));
            combo_level.getSelectionModel().select(String.valueOf(newQuestion.getLevel()));
            combo_Team.getSelectionModel().select(String.valueOf(newQuestion.getTeamNick()));
        }
        initTeam();
        initCorrectAnswer();
        initlevel();

        button_save.setOnAction(e->{
            insertNewRow();
        });

}

    public static void receiveQues(QuestionAnswerController.NewQuestion newQues) {
        setNewQuestion(newQues);
    }
    public void initlevel() {
        combo_level.getSelectionModel().clearSelection();
        List<String> levelList = new ArrayList<String>();
        for(int i=1;i<=3;i++)
        {
            levelList.add(String.valueOf(i));
        }
        //convert list to ObservableList list
        //JavaFX ObservableList is JavaFX SDK’s special implementation of List interface.
        ObservableList observableList = FXCollections.observableArrayList(levelList);
        combo_level.setItems(observableList);
    }
    public void initTeam() {
        combo_Team.getSelectionModel().clearSelection();
        List<String> teamUniqelist = new ArrayList<String>();
        Team[] allTeams= Team.values();
        for (Team team:allTeams)
        {
            teamUniqelist.add(String.valueOf(team));
        }
        ObservableList observableList = FXCollections.observableArrayList(teamUniqelist);
        combo_Team.setItems(observableList);
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

    public void insertNewRow()
    {
            String questionId = this.text_question.getText();
            ArrayList<Answer> answer = new ArrayList<>();
            Answer a1 = new Answer(textFiled_answer1.getText().toString());
            Answer a2 = new Answer(textFiled_answer2.getText().toString());
            Answer a3 = new Answer(textFiled_answer3.getText().toString());
            Answer a4 = new Answer(textFiled_answer4.getText().toString());
            answer.add(a1);
            answer.add(a2);
            answer.add(a3);
            answer.add(a4);
            int correct_ans = Integer.valueOf(this.combo_correctAns.getSelectionModel().getSelectedItem());
            int level = Integer.valueOf((this.combo_level.getSelectionModel().getSelectedItem()));
            Team team=null;
            for(Team teamName:Team.values())
            {
                if(teamName.toString().compareTo(combo_Team.getSelectionModel().getSelectedItem())==0)
                {
                    team=teamName;
                }
            }
            //check if the questions exist
            if(EditMode==false) {
                if (SysData.getInstance().getQuestions() != null && !(SysData.getInstance().getQuestions().isEmpty())) {
                    if (SysData.getInstance().getQuestionByName(questionId) != null) {
                        message.setText("Question already exists!");
                        message.setStyle("-fx-text-fill: red");
                    } else {
                        Question newQuestion = new Question(questionId, answer, correct_ans, level, team.toString());
                        SysData.getInstance().getQuestions().add(newQuestion);
                        SysData.getInstance().serJsonQuestion();
                        message.setText("Question added successfully");
                        message.setStyle("-fx-text-fill: green");
                        System.out.println(SysData.getInstance().getQuestions());

                        //add the changes the admin did
                        String changeDes="Added "+ questionId;
                        EditHistoryController.historyEdit change= new EditHistoryController.historyEdit(questionId, STATUS.ADD,changeDes, LocalDate.now());
                        SysData.getInstance().getListOfChange().add(change);
                        SysData.getInstance().serJsonChange();
                    }
                }
            }
            else if(EditMode==true){
                setEditMode(false);
                Question q= SysData.getInstance().getQuestionByName(newQuestion.getQuesID());
                q.setQuesId(questionId);
                q.setAnswers(answer);
                q.setCorrect_answerID(correct_ans);
                q.setLevel(level);
                q.setTeamNick(team.toString());
                HelloApplication.alertSuccesful("Successful","the question is up to date");

                //add the changes the admin did
                String changeDes="Edited "+ questionId;
                EditHistoryController.historyEdit change= new EditHistoryController.historyEdit(questionId, STATUS.EDIT,changeDes, LocalDate.now());
                SysData.getInstance().getListOfChange().add(change);
                SysData.getInstance().serJsonChange();

                //pass pages
                try {
                    SysData.getInstance().serJsonQuestion();
                    HomeController homeController = new HomeController();
                    homeController.setReQuesPage(true);
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("MainFrame.fxml"));
                    HelloApplication.parent = loader.load();
                    loader.getController();
                    Scene scene = new Scene(HelloApplication.parent);
                    HelloApplication.stage.setScene(scene);
                    HelloApplication.stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}