package com.knights_move.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RulesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button historyBtn;

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
    private AnchorPane pnlRules;
    @FXML
    private Button signInBtn;


    @FXML
    void initialize() {
        assert pnlRules != null : "fx:id=\"pnlRules\" was not injected: check your FXML file 'Rules.fxml'.";


    }
}