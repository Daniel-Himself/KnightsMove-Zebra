package com.knights_move.ui_controllers;


import com.knights_move.model.SysData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class PieChartController extends Application {
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane piePanel;

    public Pane pnlChoosedPage;



    public void initialize (URL url, ResourceBundle rbl) {
        assert piePanel != null : "fx:id=\"piePanel\" was not injected: check your FXML file 'PieChart.fxml'.";

        try{
            pnlChoosedPage.getChildren().clear();
            Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/knights_move/view/HomePanel.fxml")));
            pnlChoosedPage.getChildren().add(node);
        } catch(IOException | NullPointerException e){
            System.out.println(e.getMessage());
        }

    }
    @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Level 1", SysData.getInstance().getQuestionByLevel(1).values().size()),
                        new PieChart.Data("Level 2", SysData.getInstance().getQuestionByLevel(2).values().size()),
                        new PieChart.Data("Level 3", SysData.getInstance().getQuestionByLevel(2).values().size()));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Questions By Level");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
