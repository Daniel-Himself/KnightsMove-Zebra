package com.knights_move.controller;

import com.knights_move.model.SysData;
import com.knights_move.view.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ManagerPasswordPageController {

    @FXML
    private Text message;

    @FXML
    private Button backBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logoBtn;

    @FXML
    private Button managerLoginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Pane pnlChoosedPage;

    @FXML
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'ManagerPasswordPage.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'ManagerPasswordPage.fxml'.";
        assert logoBtn != null : "fx:id=\"logoBtn\" was not injected: check your FXML file 'ManagerPasswordPage.fxml'.";
        assert managerLoginBtn != null : "fx:id=\"managerLoginBtn\" was not injected: check your FXML file 'ManagerPasswordPage.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'ManagerPasswordPage.fxml'.";
        assert pnlChoosedPage != null : "fx:id=\"pnlChoosedPage\" was not injected: check your FXML file 'ManagerPasswordPage.fxml'.";

        exitBtn.setOnAction(event -> {
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();
        });

        // enter key acts as a login button
        passwordField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                managerLoginLogic();
            }
        } );

        managerLoginBtn.setOnAction(event -> managerLoginLogic());

        backBtn.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("LoginPage.fxml")));
                Stage stage = (Stage) managerLoginBtn.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void managerLoginLogic() {
        // manager password is both 1234 and 'm'
        if(passwordField.getText().equals("1234") || passwordField.getText().equals("m")) {
            try {
                SysData.getInstance().setUsername("Manager");
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("MainFrame.fxml")));
                Stage stage = (Stage) managerLoginBtn.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }// if pass is incorrect, show error message
        else {
            message.setText("Incorrect password! (Correct password is '1234')");
            message.setStyle("-fx-fill: red");
            System.out.println("Wrong password");
        }

    }

}