package com.knights_move.controller;

import com.knights_move.model.SysData;
import com.knights_move.view.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {


    public Pane pnlChoosedPage;
    public AnchorPane pnlHome;
    @FXML
    private Button historyBtn;

    @FXML
    private Button pieChartBtn;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button exitBtn;

    @FXML
    private Button qaBtn;

    @FXML
    private Button rulesBtn;

    @FXML
    private Button playBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button signOutBtn;


    @FXML
    void initialize() {
        assert pnlHome != null : "fx:id=\"pnlHome\" was not injected: check your FXML file 'HomePanel.fxml'.";

        // Set username welcome label
        usernameLabel.setText("Hello " + SysData.getInstance().getUsername());

        exitBtn.setOnAction(event -> {
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();
        });
        signOutBtn.setOnAction(event ->{
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("LoginPage.fxml")));
                Stage stage = (Stage) signOutBtn.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException err) {
                err.printStackTrace();
            }
        });

        try{
            pnlChoosedPage.getChildren().clear();
            Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/HomePanel.fxml")));
            pnlChoosedPage.getChildren().add(node);
        } catch(IOException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleButtonClick(ActionEvent e) {
        if(e.getSource() == playBtn){
            initMenuItems(e);
        }
        if(e.getSource() == historyBtn){
            initMenuItems(e);
        }
        if(e.getSource() == qaBtn){
            initMenuItems(e);
        }
        if(e.getSource() == rulesBtn){
            initMenuItems(e);
        }
        if(e.getSource() == pieChartBtn){
            initMenuItems(e);
        }
        if(e.getSource() == homeBtn){
            initialize();
        }
    }

    @FXML
    private void initMenuItems(ActionEvent e){
        if(e.getSource() == homeBtn){
            try{
                pnlChoosedPage.getChildren().clear();
                Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/HomePanel.fxml")));
                pnlChoosedPage.getChildren().add(node);
            } catch(IOException | NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
        if(e.getSource() == playBtn){
            try{
                pnlChoosedPage.getChildren().clear();
                Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/Play.fxml")));
                pnlChoosedPage.getChildren().add(node);
            } catch(IOException | NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
        if(e.getSource() == historyBtn){
            try{
                pnlChoosedPage.getChildren().clear();
                Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/History.fxml")));
                pnlChoosedPage.getChildren().add(node);
            } catch(IOException | NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
        if(e.getSource() == qaBtn){
            // Restrict access to the QA page only to the "manager" user
            if(!SysData.getInstance().getUsername().equals("Manager")){
                try{
                    pnlChoosedPage.getChildren().clear();
                    Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/PermissionDeniedPage.fxml")));
                    System.out.println("Permission denied! A non-manager user tried to access the QA page.");
                    pnlChoosedPage.getChildren().add(node);
                } catch(IOException | NullPointerException ex){
                    System.out.println(ex.getMessage());
                }
            }
            else{
                try{
                    pnlChoosedPage.getChildren().clear();
                    Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/QA.fxml")));
                    pnlChoosedPage.getChildren().add(node);
                } catch(IOException | NullPointerException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        if(e.getSource() == rulesBtn){
            try{
                pnlChoosedPage.getChildren().clear();
                Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/Rules.fxml")));
                pnlChoosedPage.getChildren().add(node);
            } catch(IOException | NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
        if(e.getSource() == pieChartBtn){
            try{
                pnlChoosedPage.getChildren().clear();
                Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/PieChart.fxml")));
                pnlChoosedPage.getChildren().add(node);
            } catch(IOException | NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}