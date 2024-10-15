package com.example.weatherappjane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherAppGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/weatherappjane/WeatherAppGUI.fxml"));

        // Set up the stage (window) properties
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root, 400, 300));  // Set width and height
        primaryStage.show();  // Display the window
    }

    public static void main(String[] args) {
        launch(args);
    }
}
