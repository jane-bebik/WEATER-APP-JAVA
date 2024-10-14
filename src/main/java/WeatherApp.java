import org.json.JSONObject;


    public class WeatherApp {
        public static void main(String[] args) {
            String city = "Toronto"; // it is just to test it will not be hard coded
            JSONObject weatherData = WeatherAPI.getWeatherData(city);

    }

}
