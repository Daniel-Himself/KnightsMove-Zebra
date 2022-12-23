package com.knights_move.controller;

import com.knights_move.model.Answer;
import com.knights_move.model.Question;
import com.knights_move.model.SysData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AnswersController implements Initializable {
    @FXML
    private Button button_save;

    @FXML
    private Text message;

    @FXML
    private TableView<NewAnswer> tableView_answers;

    @FXML
    private TableColumn<NewAnswer,String> column_content;

    @FXML
    private Label label_title;

    @FXML
    private Pane pane_title;

    @FXML
    private TextField text_newAnswer;
    private static ArrayList<NewAnswer> allAnswer;

    public static class NewAnswer {
        private String content;

        public NewAnswer(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static ArrayList<NewAnswer> getAllAnswer() {
        if(allAnswer==null||allAnswer.isEmpty()) {
            allAnswer = new ArrayList<>();
            if (SysData.getInstance().getQuestions() != null && !(SysData.getInstance().getQuestions().isEmpty()))
                {
                for (Question question : SysData.getInstance().getQuestions()) {
                    for (int i = 1; i < question.getAnswers().size(); i++) {
                        String content=question.getAnswers().get(i).getContent();
                        allAnswer.add(new NewAnswer(content));
                    }
                }
             }
        }
        return allAnswer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getAllAnswer();
        initTable();
        button_save.setOnAction(e->{
          save();
        });
    }
    //method return all the answer in system
    public ArrayList<Answer> getAnswersInSystem()
    {
        ArrayList<Answer> answerarray =new ArrayList<>();
        if(SysData.getInstance().getQuestions()==null||SysData.getInstance().getQuestions().isEmpty())
        {
          return null;
        }
        else {
            for (Question question : SysData.getInstance().getQuestions()) {
                for (int i=1;i<question.getAnswers().size();i++) {
                    answerarray.add(question.getAnswers().get(i));
                }
            }
            return answerarray;
        }
    }
    //bolean method thet return if the answer exist
    public boolean isExist(String content)
    {
        int count=0;
        ArrayList<NewAnswer> answersArray=new ArrayList<>();
        if(AnswersController.allAnswer==null)
        {
            getAllAnswer();
        }
        answersArray.addAll(AnswersController.allAnswer);
        if(!answersArray.isEmpty())
        {
            for (NewAnswer answer:answersArray)
            {
                if(answer.content.compareTo(content)==0)
                {
                    count++;
                }
            }
        }
        if (count==1)
            return true;
        else  return false;
    }

    public void save() {
        if(!text_newAnswer.getText().isEmpty()) {
            String contentAnswer = text_newAnswer.getText();
            if (isExist(contentAnswer)) {
                message.setText("Answer already exists, please select it from the table");
                message.setStyle("-fx-fill: red");
                System.out.println("AnswersController error: Answer already exists, please select it from the table");
            } else {
                if( AnswersController.allAnswer==null)
                {
                    getAllAnswer();
                }
                if(contentAnswer.length()>3) {
                    AnswersController.allAnswer.add(new NewAnswer(contentAnswer));
                    message.setText("Answer added successfully");
                    message.setStyle("-fx-fill: green");
                    System.out.println("AnswersController success message: Answer added successfully");
                }
                else{
                    message.setText("Invalid answer");
                    message.setStyle("-fx-fill: red");
                    System.out.println("AnswersController error: Invalid answer");

                }
            }
        }
        else {
            message.setText("Please fill in the empty field");
            message.setStyle("-fx-fill: red");
            System.out.println("AnswersController error: Please fill in the empty field");
        }
        initTable();
    }
    public void initTable()
    {
        column_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        List<NewAnswer> newAnswerList= new ArrayList<>();
        if(AnswersController.allAnswer!=null&&!(AnswersController.allAnswer.isEmpty())) {
            newAnswerList.addAll(getAllAnswer());
        }
        ObservableList<NewAnswer> observableList= FXCollections.observableArrayList(newAnswerList);
        tableView_answers.setItems(observableList);

    }
}