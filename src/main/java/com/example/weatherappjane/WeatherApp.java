
package com.example.weatherappjane;

import org.json.JSONObject;

public class    WeatherApp {
    public static void main(String[] args) {
        String city = "toronto"; // just to test it will not be hard coded
        JSONObject weatherData = WeatherAPI.getWeatherData(city);

        if (weatherData != null) {
            //aaaaaaaaaaaaaaaaaaaaaa it worked hehe
            double temp = weatherData.getJSONObject("main").getDouble("temp");
            String description = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");

            // Print the data
            System.out.println("City: " + city);
            System.out.println("Temperature: " + temp);
            System.out.println("Description: " + description);

            // save it in database
            WeatherDB.saveWeatherData(city, temp, description);
            System.out.println("Weather data saved to the database.");
        } else {
            System.out.println("Error fetching weather data.");
        }
    }
}
