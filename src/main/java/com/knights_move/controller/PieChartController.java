package com.knights_move.controller;


import com.knights_move.model.SysData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;


public class PieChartController{
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane piePanel;

    public void initialize () {
        assert pieChart != null : "fx:id=\"pieChart\" was not injected: check your FXML file 'PieChart.fxml'.";
        assert piePanel != null : "fx:id=\"piePanel\" was not injected: check your FXML file 'PieChart.fxml'.";

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Level 1", SysData.getInstance().getQuestionByLevel(1).values().size()),
                        new PieChart.Data("Level 2", SysData.getInstance().getQuestionByLevel(2).values().size()),
                        new PieChart.Data("Level 3", SysData.getInstance().getQuestionByLevel(2).values().size()));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle(""); // blank title because there is already an FXML title bar, it helps to avoid overlapping objects
        // add chart to panel
        piePanel.getChildren().add(chart);


    }
}