
package com.example.weatherappjane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class WeatherAppController {

    @FXML
    private TextField cityInput;

    @FXML
    private Label resultLabel;

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
            } else {
                resultLabel.setText("Error fetching weather data.");
            }
        } else {
            resultLabel.setText("Please enter a city name.");
        }
    }
}
