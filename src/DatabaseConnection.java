import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/key_management";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Not Found!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check URL, Username, and Password.");
        }
        return con;
    }

    public static String getUrl() {
        return url;
    }

    public static void main(String[] args) {
        Connection testConnection = getConnection();
        if (testConnection != null) {
            System.out.println("Database connected successfully!");
            try {
                testConnection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection.");
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
