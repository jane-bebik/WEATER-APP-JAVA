import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class WeatherDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/WeatherApp";
    private static final String USER = "root";
    private static final String PASS = "password";

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
}
