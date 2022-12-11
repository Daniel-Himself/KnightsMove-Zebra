package com.knights_move.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    public static Scene scene;
    public static Parent parent;
    public static Stage stage;

    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QA.fxml"));
            parent = loader.load();
            scene = new Scene(parent, 800,600);
            scene.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
            stage = primaryStage;
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //A generic method for replacing a page
    public static <T> T loadPage(String pageControllerName) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(pageControllerName));
            HelloApplication.parent = loader.load();
            T t = loader.getController();
            Scene scene = new Scene(HelloApplication.parent);
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.show();
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //alert pop up for project
    public static void alertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertSuccesful(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch();
    }
}