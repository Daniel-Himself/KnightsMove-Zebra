package com.knights_move.ui_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class HomeController {


    public Pane pnlChoosedPage;
    public AnchorPane pnlHome;
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
    private Button signOutBtn;

    @FXML
    private AnchorPane pnlHistory;

    @FXML
    private Button tosignInBtn;

    @FXML
    void initialize() {
        assert onboardingBtn != null : "fx:id=\"onboardingBtn\" was not injected: check your FXML file 'HomePanel.fxml'.";
        assert pnlHome != null : "fx:id=\"pnlHome\" was not injected: check your FXML file 'HomePanel.fxml'.";
        assert tosignInBtn != null : "fx:id=\"tosignInBtn\" was not injected: check your FXML file 'HomePanel.fxml'.";

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
            try{
                pnlChoosedPage.getChildren().clear();
                Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/QuestionsAnswers.fxml")));
                pnlChoosedPage.getChildren().add(node);
            } catch(IOException | NullPointerException ex){
                System.out.println(ex.getMessage());
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
    }
}