package com.knights_move.ui_controllers;
import java.io.IOException;

import com.knights_move.view.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {


    @FXML
    private Button historyBtn;

    @FXML
    private Button onboardingBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logoBtn;

    @FXML
    private Button qaBtn;

    @FXML
    private Button rulesBtn;

    @FXML
    private Button playBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button signInBtn;

    @FXML
    private Button tosignInBtn;

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
        qaBtn.setOnAction(event -> {
            qaBtn.getScene().getWindow().hide();
            openScene("QuestionsAnswers.fxml");
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