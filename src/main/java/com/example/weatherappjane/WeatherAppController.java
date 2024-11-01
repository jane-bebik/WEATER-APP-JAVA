package com.example.weatherappjane;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.util.List;

public class WeatherAppController {

    @FXML
    private BarChart<String, Number> weatherChart;

    @FXML
    private Button showTableButton;

    @FXML
    private TextField cityInput;  // Input field for city search

    @FXML
    private Label resultLabel;  // Label to display search results

    public void initialize() {
        displayWeatherChart();  // Initialize and display the BarChart when the app starts
    }

    private void displayWeatherChart() {
        weatherChart.setTitle("Temperature of Last 10 Entries");
        weatherChart.setCategoryGap(25);
        weatherChart.setBarGap(10);
        weatherChart.getXAxis().setTickLabelRotation(90);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Last 10 Temperature Entries");

        // Fetch last 10 entries and populate the chart
        List<String> lastEntries = WeatherDB.getLast10Entries();
        for (String entry : lastEntries) {
            String[] data = entry.split(", ");
            String city = data[0].split(": ")[1];
            double temp = Double.parseDouble(data[1].split(": ")[1].replace("°C", ""));
            series.getData().add(new XYChart.Data<>(city, temp));
        }
        weatherChart.getData().clear();
        weatherChart.getData().add(series);
    }

    @FXML
    private void fetchWeatherData() {
        String city = cityInput.getText().trim();

        if (!city.isEmpty()) {
            JSONObject weatherData = WeatherAPI.getWeatherData(city);

            if (weatherData != null) {
                double temp = weatherData.getJSONObject("main").getDouble("temp");
                String description = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");

                // Display results
                resultLabel.setText("Temperature: " + temp + "°C\nDescription: " + description);

                // Save to database
                WeatherDB.saveWeatherData(city, temp, description);

                // Update chart with new entry
                displayWeatherChart();
            } else {
                resultLabel.setText("Error fetching weather data. Please try again.");
            }
        } else {
            resultLabel.setText("Please enter a city name.");
        }
    }

    @FXML
    private void showTableView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/weatherappjane/WeatherAppTableView.fxml"));
        Parent tableViewRoot = loader.load();
        Stage stage = (Stage) showTableButton.getScene().getWindow();
        stage.setScene(new Scene(tableViewRoot));
    }
}
