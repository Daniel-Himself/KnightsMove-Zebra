package com.knights_move.ui_controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.knights_move.view.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class HistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> historTbl;

    @FXML
    private TableColumn<?, ?> scoreCol;

    @FXML
    private TableColumn<?, ?> awardCol;

    @FXML
    private Button historyBtn;

    @FXML
    private TableColumn<?, ?> dateCol;

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
    private AnchorPane pnlHistory;

    @FXML
    private Button homeBtn;

    @FXML
    private Button signInBtn;


    @FXML
    void initialize() {
        assert awardCol != null : "fx:id=\"awardCol\" was not injected: check your FXML file 'History.fxml'.";
        assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'History.fxml'.";
        assert historTbl != null : "fx:id=\"historTbl\" was not injected: check your FXML file 'History.fxml'.";
        assert pnlHistory != null : "fx:id=\"pnlHistory\" was not injected: check your FXML file 'History.fxml'.";
        assert scoreCol != null : "fx:id=\"scoreCol\" was not injected: check your FXML file 'History.fxml'.";
    }
}