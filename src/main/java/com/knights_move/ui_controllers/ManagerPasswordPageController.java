package com.knights_move.ui_controllers;

import com.knights_move.view.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManagerPasswordPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void handleButtonClick(ActionEvent event) {

    }

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


        managerLoginBtn.setOnAction(event -> {
            // manager password is 1234
            // if password is correct, open main menu - manager
            if(passwordField.getText().equals("1234")){
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("MainFrame.fxml")));
                    Stage stage = (Stage) managerLoginBtn.getScene().getWindow();
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }// if pass is incorrect, show error message
            else {
                message.setText("Incorrect password!");
                message.setStyle("-fx-fill: red");
                System.out.println("Wrong password");
            }

        });
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

}