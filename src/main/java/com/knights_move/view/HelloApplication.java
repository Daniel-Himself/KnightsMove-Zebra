package com.knights_move.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        URL url = getClass().getResource("design.scss");
        assert url != null;
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Knights Move");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}