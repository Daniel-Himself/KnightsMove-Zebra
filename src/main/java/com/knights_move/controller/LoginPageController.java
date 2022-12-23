package com.knights_move.controller;

import com.knights_move.model.SysData;
import com.knights_move.view.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Text loginErrorLabel;

    @FXML
    private Pane pnlChoosedPage;

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

        LoginBtn.setOnAction(event -> loginLogic());

        // Make the enter key act as a login button
        UsernameField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                loginLogic();
            }
        } );

    }

    private void loginLogic(){

        // login to the manager user with either 'manager' or 'm' as the username
        if(UsernameField.getText().equals("manager") || UsernameField.getText().equals("m")){
            try {
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("ManagerPasswordPage.fxml"));
                Stage stage = (Stage) LoginBtn.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else try {
            // check if username field has illegal characters
            if (!UsernameField.getText().matches("[a-zA-Z]{1,10}")) {
                loginErrorLabel.setStyle("-fx-fill: red");
                loginErrorLabel.setText("Username can only contain 1 to 10 english letters");
            }
            else{
                SysData.getInstance().setUsername(UsernameField.getText());
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("/com/knights_move/view/MainFrame.fxml"));
                Stage stage = (Stage) LoginBtn.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void textAction(KeyEvent e) {

        if (e.getCode().equals(KeyCode.ENTER))
            loginLogic();
    }
}