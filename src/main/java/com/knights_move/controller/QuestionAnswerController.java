package com.knights_move.controller;

import com.knights_move.model.Answer;
import com.knights_move.model.Question;
import com.knights_move.model.SysData;
import com.knights_move.view.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionAnswerController implements Initializable {

    @FXML
    private ImageView image_add;

    @FXML
    private Text message;

    @FXML
    private ImageView image_delete;

    @FXML
    private ImageView image_edit;
    @FXML
    private Button button_delete;

    @FXML
    private Button button_edit;

    @FXML
    private Button button_save;
    @FXML
    private TableView<QuestionAnswerController.NewQuestion> TableView_Question;
    @FXML
    private TableColumn<NewQuestion,String> column_answer1;

    @FXML
    private TableColumn<Answer, String> column_answer2;

    @FXML
    private TableColumn<NewQuestion,String> column_answer3;

    @FXML
    private TableColumn<NewQuestion,String> column_answer4;

    @FXML
    private TableColumn<NewQuestion,String> column_correctAns;

    @FXML
    private TableColumn<NewQuestion,String> column_level;

    @FXML
    private TableColumn<NewQuestion,String> column_question;

    @FXML
    private TableColumn<NewQuestion,String> column_team;
    @FXML
    private TextField keyWordsTextFiled;

    @FXML
    private Label label_title;
    private static boolean deleteMode=false;

    public static class NewQuestion {
        private String quesID;
        ArrayList<Answer> answers;
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

        @Override
        public String toString() {
            return "NewQuestion{" +
                    "quesID='" + quesID + '\'' +
                    ", answers=" + answers +
                    ", correct_answerID=" + correct_answerID +
                    ", level=" + level +
                    ", teamNick='" + teamNick + '\'' +
                    '}';
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        button_save.setOnAction(e->{
            try{
                AddQuestionController.setEditMode(false);
                HelloApplication.loadPage("AddQuestion.fxml");

            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        });
        button_edit.setOnAction(e->{
            try{
                AddQuestionController.setEditMode(true);
                if(!TableView_Question.getSelectionModel().isEmpty()) {
                    NewQuestion newQuestion=TableView_Question.getSelectionModel().getSelectedItem();
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("AddQuestion.fxml"));
                    HelloApplication.parent = loader.load();
                    loader.getController();
                    Scene scene = new Scene(HelloApplication.parent);
                    HelloApplication.stage.setScene(scene);
                    HelloApplication.stage.setUserData(newQuestion);
                    HelloApplication.stage.show();
                }
                else {
                    message.setText("Please click on a row");
                    message.setStyle("-fx-fill: red");
                    System.out.println("QuestionAnswerController error: Please click on a row");
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }

        });
        button_delete.setOnAction(e->{
            if(!TableView_Question.getSelectionModel().isEmpty()) {
                deleteQuestion();
            }
            else {
                message.setText("Please click on a row");
                message.setStyle("-fx-fill: red");
                System.out.println("QuestionAnswerController error: Please click on a row");
            }
        });

        //create filter list where someone type in search
        ObservableList<NewQuestion> observableListFilter=FXCollections.observableArrayList(getNewQuestionList());
        FilteredList<NewQuestion> filteredDate= new FilteredList<>(observableListFilter, b->true);
        keyWordsTextFiled.textProperty().addListener((observable, oldValue, newValue)->{
            filteredDate.setPredicate(newQuestion->{
                if(newValue==null||newValue.isEmpty()|| newValue.isBlank()){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();
                if(newQuestion.getQuesID().toLowerCase().indexOf(searchKeyWord)>-1)
                {
                    return true;
                }
                else if(newQuestion.getTeamNick().toLowerCase().indexOf(searchKeyWord)>-1)
                {
                    return true;
                }
                else
                    return false;

            });
        } );
        SortedList<NewQuestion> sortedList= new SortedList<>(filteredDate);
        sortedList.comparatorProperty().bind(TableView_Question.comparatorProperty());
        TableView_Question.setItems(sortedList);
    }
    public ArrayList<NewQuestion> getNewQuestionList() {
        ArrayList<NewQuestion> newQuestionsList = new ArrayList<>();
        if (SysData.getInstance().getQuestions() != null && !(SysData.getInstance().getQuestions().isEmpty())) {
            for (Question q : SysData.getInstance().getQuestions()) {
                newQuestionsList.add(new NewQuestion(q.getQuesId(), q.getAnswers(), q.getCorrect_answerID(), q.getLevel(), q.getTeamNick()));
            }
        }
        return newQuestionsList;
    }
    private void initTable()
    {
        TableView_Question.getItems().clear();
        if(deleteMode==false) {
            SysData.getInstance().DesJsonQuestions();
        }
        column_question.setCellValueFactory(new PropertyValueFactory<>("quesID"));
        column_answer1.setCellValueFactory(new PropertyValueFactory<>("answers"));
        column_correctAns.setCellValueFactory(new PropertyValueFactory<>("correct_answerID"));
        column_level.setCellValueFactory(new PropertyValueFactory<>("level"));
        column_team.setCellValueFactory(new PropertyValueFactory<>("teamNick"));

        ObservableList<NewQuestion> observableList = FXCollections.observableArrayList(getNewQuestionList());
        TableView_Question.setItems(observableList);


    }
    public void deleteQuestion()
    {
        deleteMode=true;
        NewQuestion selected=TableView_Question.getSelectionModel().getSelectedItem();
        String quesId=selected.getQuesID();
        ArrayList<Question> questions=SysData.getInstance().getQuestions();
        questions.remove(SysData.getInstance().getQuestionByName(quesId));
        SysData.getInstance().setQuestions(questions);
        SysData.getInstance().serJsonQuestion();
        initTable();

    }

}