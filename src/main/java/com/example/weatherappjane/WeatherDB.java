package com.example.weatherappjane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WeatherDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/weatherapp";
    private static final String USER = "root";
    private static final String PASS = "07U8o|p`:AZN";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveWeatherData(String city, double temperature, String description) {
        Connection conn = connect();
        if (conn != null) {
            try {
                String query = "INSERT INTO weather_data (city, temperature, description) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, city);
                stmt.setDouble(2, temperature);
                stmt.setString(3, description);
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Ensure this method is implemented in the WeatherDB class
    public static List<String> getLast10Entries() {
        List<String> entries = new ArrayList<>();
        Connection conn = connect();
        if (conn != null) {
            try {
                String query = "SELECT city, temperature, description FROM weather_data ORDER BY id DESC LIMIT 10";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String city = rs.getString("city");
                    double temp = rs.getDouble("temperature");
                    String description = rs.getString("description");
                    entries.add("City: " + city + ", Temp: " + temp + "°C, Description: " + description);
                }
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entries;
    }

    // Add the getAllWeatherData() method if not already in WeatherDB
    public static List<WeatherRecord> getAllWeatherData() {
        List<WeatherRecord> records = new ArrayList<>();
        Connection conn = connect();
        if (conn != null) {
            try {
                String query = "SELECT city, temperature, description FROM weather_data";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String city = rs.getString("city");
                    double temp = rs.getDouble("temperature");
                    String description = rs.getString("description");
                    records.add(new WeatherRecord(city, temp, description));
                }
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return records;
    }
}
