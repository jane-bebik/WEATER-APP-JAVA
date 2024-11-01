
package com.example.weatherappjane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;



public class WeatherAPI {
    private static final String API_KEY = "apikeyhere";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public static JSONObject getWeatherData(String city) {
        try {
            String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString()); // Parsing JSON response
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
