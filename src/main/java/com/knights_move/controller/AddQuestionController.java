package com.knights_move.controller;

import com.knights_move.model.Answer;
import com.knights_move.model.Question;
import com.knights_move.model.SysData;
import com.knights_move.model.Team;
import com.knights_move.view.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private Button button_back;


    private static boolean EditMode=false;
    public static boolean getEditMode() {
        return EditMode;
    }
    public static void setEditMode(boolean editMode) {
        EditMode = editMode;
    }

    QuestionAnswerController.NewQuestion newQuestion;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ap.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        receiveData(mouseEvent);
                        if(EditMode)
                        {
                            text_question.setText(newQuestion.getQuesID());
                            textFiled_answer1.setText(newQuestion.getAnswers().get(0).getContent());
                            textFiled_answer2.setText(newQuestion.getAnswers().get(1).getContent());
                            textFiled_answer3.setText(newQuestion.getAnswers().get(2).getContent());
                            textFiled_answer4.setText(newQuestion.getAnswers().get(3).getContent());
                            combo_correctAns.getSelectionModel().select(newQuestion.getCorrect_answerID());
                            combo_level.getSelectionModel().select(String.valueOf(newQuestion.getLevel()));
                            combo_Team.getSelectionModel().select(String.valueOf(newQuestion.getTeamNick()));
                        }

                    }
        });
        initTeam();
        initCorrectAnswer();
        initlevel();

        button_save.setOnAction(e->{
            insertNewRow();
        });

        button_back.setOnAction(e->{
            HelloApplication.loadPage("MainFrame.fxml");
        });
}
    private  void receiveData(MouseEvent e) {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
       this.newQuestion = (QuestionAnswerController.NewQuestion) stage.getUserData();

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
            Team team = Team.valueOf(combo_Team.getSelectionModel().getSelectedItem());

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
                    }
                }
            }
            else if(EditMode==true){
                Question q= SysData.getInstance().getQuestionByName(newQuestion.getQuesID());
                q.setQuesId(questionId);
                q.setAnswers(answer);
                q.setCorrect_answerID(correct_ans);
                q.setLevel(level);
                q.setTeamNick(team.toString());
                SysData.getInstance().serJsonQuestion();
                message.setText("Question updated successfully");
                message.setStyle("-fx-text-fill: green");
                System.out.println("AddQuestionController message: Question updated successfully");
                //HelloApplication.alertSuccesful("Successful","the question is up to date");
            }
        }
}