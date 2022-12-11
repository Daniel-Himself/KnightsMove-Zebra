package com.knights_move.controller;
import com.knights_move.model.Answer;
import com.knights_move.model.Question;
import com.knights_move.model.SysData;
import com.knights_move.view.HelloApplication;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {

    @FXML
    private Button addTeam;

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
    private ComboBox<String> combo_correctAns;

    @FXML
    private ComboBox<String> combo_level;

    @FXML
    private Label label_title;

    @FXML
    private Pane pane_top;

    @FXML
    private TextField text_question;

    private static boolean EditMode=false;
    public static boolean getEditMode() {
        return EditMode;
    }
    public static void setEditMode(boolean editMode) {
        EditMode = editMode;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTeam();
        initCorrectAnswer();
        initlevel();

        button_save.setOnAction(e->{
            //insertNewRow();
        });

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

        combo_answer1.setOnMouseClicked(e->{
            initAnswer();
        });
        combo_answer2.setOnMouseClicked(e->{
            initAnswer();
        });
        combo_answer3.setOnMouseClicked(e->{
            initAnswer();
        });
        combo_answer4.setOnMouseClicked(e->{
            initAnswer();
        });
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
    private void showAnswers()
    {
        try{
            HelloApplication.loadPage("Answers.fxml");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//    public void insertNewRow()
//    {
//
//        if(getEditMode())
//        {
//            String quesId=" "
//            Question question =SysData.getInstance().getQuestionByName(quesId);
//            text_question.setText(question.getQuesId());
//            combo_answer1.setValue((question.getAnswers().get(1).getContent()));
//            combo_answer2.setValue(question.getAnswers().get(2).getContent());
//            combo_answer3.setValue(question.getAnswers().get(3).getContent());
//            combo_answer4.setValue(question.getAnswers().get(4).getContent());
//            combo_correctAns.setValue(String.valueOf(question.getCorrect_answerID()));
//            combo_level.setValue(String.valueOf(question.getLevel()));
//            combo_Team.setValue(question.getTeamNick());
//        }
//            String questionId = this.text_question.getText();
//            ArrayList<Answer> answer = new ArrayList<>();
//            Answer a1 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
//            Answer a2 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
//            Answer a3 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
//            Answer a4 = new Answer(combo_answer1.getSelectionModel().getSelectedItem());
//            answer.add(a1);
//            answer.add(a2);
//            answer.add(a3);
//            answer.add(a4);
//            int correct_ans = Integer.valueOf(this.combo_correctAns.getSelectionModel().getSelectedItem());
//            int level = Integer.valueOf((this.combo_level.getSelectionModel().getSelectedItem()));
//            String team = this.combo_Team.getSelectionModel().getSelectedItem();
//            //check if the questions exist
//            if(EditMode==false) {
//                if (SysData.getInstance().getQuestions() != null && !(SysData.getInstance().getQuestions().isEmpty())) {
//                    if (SysData.getInstance().getQuestionByName(questionId) != null) {
//                        HelloApplication.alertError("Error", "This questions content exist");
//                    } else {
//                        Question newQuestion = new Question(questionId, answer, correct_ans, level, team);
//                        SysData.getInstance().getQuestions().add(newQuestion);
//                    }
//                }
//            }
//            else if(EditMode){
//                Question q= SysData.getInstance().getQuestionByName(quesId);
//                q.setQuesId(questionId);
//                q.setAnswers(answer);
//                q.setCorrect_answerID(correct_ans);
//                q.setLevel(level);
//                q.setTeamNick(team);
//            }
//        }
//

}

