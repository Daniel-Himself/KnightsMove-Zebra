package com.knights_move.view;

import com.knights_move.model.SysData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Scene scene;
    public static Parent parent;
    public static Stage stage;

    public void start(Stage primaryStage) {

        try {
            SysData sysData = SysData.getInstance();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            parent = loader.load();
            scene = new Scene(parent, 800,600);
            scene.getStylesheets().add(getClass().getResource("design.scss").toExternalForm());
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

    public static void main(String[] args) {
        launch();
    }
}