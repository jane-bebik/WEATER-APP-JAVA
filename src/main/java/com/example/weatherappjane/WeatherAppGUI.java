package com.example.weatherappjane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherAppGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/weatherappjane/WeatherAppGUI.fxml"));

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());  // Link CSS file

        primaryStage.setTitle("Jane's Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
