package com.example.weatherappjane;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.List;

public class WeatherAppTableController {

    @FXML
    private TableView<WeatherRecord> weatherTable;

    @FXML
    private TableColumn<WeatherRecord, String> cityColumn;

    @FXML
    private TableColumn<WeatherRecord, Double> tempColumn;

    @FXML
    private TableColumn<WeatherRecord, String> descColumn;

    public void initialize() {
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        weatherTable.setItems(getWeatherData());
    }

    private ObservableList<WeatherRecord> getWeatherData() {
        List<WeatherRecord> data = WeatherDB.getAllWeatherData();
        return FXCollections.observableArrayList(data);
    }

    @FXML
    private void backToChart() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/weatherappjane/WeatherAppGUI.fxml"));
        Parent chartViewRoot = loader.load();
        Stage stage = (Stage) weatherTable.getScene().getWindow();
        stage.setScene(new Scene(chartViewRoot));
    }
}
