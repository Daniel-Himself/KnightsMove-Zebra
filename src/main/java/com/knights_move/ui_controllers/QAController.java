package com.knights_move.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class QAController {

    @FXML
    private Tab manageQuestTab;

    @FXML
    private AnchorPane pnlQuestions;

    @FXML
    private Button historyBtn;

    @FXML
    private TableView<?> questionsTab;

    @FXML
    private TableColumn<?, ?> questionCol;

    @FXML
    private TableColumn<?, ?> answDCol;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn<?, ?> answBCol;

    @FXML
    private TableColumn<?, ?> answCCol;

    @FXML
    private Button qaBtn;

    @FXML
    private Button rulesBtn;

    @FXML
    private Tab qATab;

    @FXML
    private TableColumn<?, ?> answACol;

    @FXML
    private Button logoBtn;

    @FXML
    private Button playBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button signInBtn;

    @FXML
    void initialize() {
        assert answACol != null : "fx:id=\"answACol\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert answBCol != null : "fx:id=\"answBCol\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert answCCol != null : "fx:id=\"answCCol\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert answDCol != null : "fx:id=\"answDCol\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert manageQuestTab != null : "fx:id=\"manageQuestTab\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert pnlQuestions != null : "fx:id=\"pnlQuestions\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert qATab != null : "fx:id=\"qATab\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert questionCol != null : "fx:id=\"questionCol\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
        assert questionsTab != null : "fx:id=\"questionsTab\" was not injected: check your FXML file 'QuestionsAnswers.fxml'.";
    }
}