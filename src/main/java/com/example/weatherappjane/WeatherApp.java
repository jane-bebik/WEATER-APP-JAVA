package com.example.weatherappjane;

import org.json.JSONObject;


    public class WeatherApp {
        public static void main(String[] args) {
            String city = "Toronto"; // it is just to test it will not be hard coded
            JSONObject weatherData = WeatherAPI.getWeatherData(city);


            if (weatherData != null) {
                // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                double temp = weatherData.getJSONObject("main").getDouble("temp");
                String description = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");
// plz work??


                // Print data
                System.out.println("City: " + city);
                System.out.println("Temperature: " + temp);
                System.out.println("Description: " + description);


    }

}}
