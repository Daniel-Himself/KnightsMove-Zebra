package com.knights_move.ui_controllers;

import java.io.IOException;

import com.knights_move.view.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class QAController {

    @FXML
    private Tab manageQuestTab;

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
        playBtn.setOnAction(event -> {
            playBtn.getScene().getWindow().hide();
            openScene("Play.fxml");
        });
        historyBtn.setOnAction(event -> {
            historyBtn.getScene().getWindow().hide();
            openScene("History.fxml");
        });
        homeBtn.setOnAction(event -> {
            homeBtn.getScene().getWindow().hide();
            openScene("Home.fxml");
        });
        rulesBtn.setOnAction(event -> {
            rulesBtn.getScene().getWindow().hide();
            openScene("Rules.fxml");
        });

    }

    public void openScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}