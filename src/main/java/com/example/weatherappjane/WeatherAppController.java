package com.example.weatherappjane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.util.List;

public class WeatherAppController {

    @FXML
    private TextField cityInput;

    @FXML
    private Label resultLabel;

    @FXML
    private Label last10EntriesLabel;  // Add a new label in FXML for displaying the last 10 entries

    @FXML
    private Button getWeatherButton;

    @FXML
    private void fetchWeatherData() {
        // Get city name from input field
        String city = cityInput.getText().trim();

        // Call the WeatherAPI to get weather data for the city
        if (!city.isEmpty()) {
            JSONObject weatherData = WeatherAPI.getWeatherData(city);

            if (weatherData != null) {
                // Extract and display weather data
                double temp = weatherData.getJSONObject("main").getDouble("temp");
                String description = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");

                resultLabel.setText("Temperature: " + temp + "°C\nDescription: " + description);

                // Optionally save the data to MySQL
                WeatherDB.saveWeatherData(city, temp, description);

                // Fetch and display the last 10 entries
                List<String> lastEntries = WeatherDB.getLast10Entries();
                StringBuilder lastEntriesText = new StringBuilder("Last 10 Entries:\n");
                for (String entry : lastEntries) {
                    lastEntriesText.append(entry).append("\n");
                }
                last10EntriesLabel.setText(lastEntriesText.toString());

            } else {
                resultLabel.setText("Error fetching weather data.");
            }
        } else {
            resultLabel.setText("Please enter a city name.");
        }
    }
}
