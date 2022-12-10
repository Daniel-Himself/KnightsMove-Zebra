package com.knights_move.ui_controllers;

import com.knights_move.view.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginPageController {

    @FXML
    private Button LoginBtn;

    @FXML
    private TextField UsernameField;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logoBtn;

    @FXML
    private Pane pnlChoosedPage;

    @FXML
    void handleButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert UsernameField != null : "fx:id=\"UsernameField\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert logoBtn != null : "fx:id=\"logoBtn\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert pnlChoosedPage != null : "fx:id=\"pnlChoosedPage\" was not injected: check your FXML file 'LoginPage.fxml'.";

        exitBtn.setOnAction(event -> {
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();
        });

        LoginBtn.setOnAction(event -> {

            // TODO fix manager password window on correct credentials
            if(UsernameField.getText().equals("manager")){
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("ManagerPasswordPage.fxml")));
                    Stage stage = (Stage) LoginBtn.getScene().getWindow();
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            else
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/MainFrame.fxml")));
                    Stage stage = (Stage) LoginBtn.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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