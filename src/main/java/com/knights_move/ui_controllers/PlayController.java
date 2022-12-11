package com.knights_move.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button historyBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Label timeLbl;

    @FXML
    private Button logoBtn;

    @FXML
    private Button qaBtn;

    @FXML
    private AnchorPane pnlGameBoard;

    @FXML
    private Button rulesBtn;

    @FXML
    private Label scoreLbl;

    @FXML
    private Button playBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button signInBtn;

    @FXML
    void initialize() {
        assert pnlGameBoard != null : "fx:id=\"pnlGameBoard\" was not injected: check your FXML file 'Play.fxml'.";
        assert scoreLbl != null : "fx:id=\"scoreLbl\" was not injected: check your FXML file 'Play.fxml'.";
        assert timeLbl != null : "fx:id=\"timeLbl\" was not injected: check your FXML file 'Play.fxml'.";
    }
}