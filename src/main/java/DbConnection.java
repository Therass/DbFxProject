import java.sql.*;
import java.util.Properties;
import java.sql.Connection;

public class DbConnection {

    private static Connection connection;
    private static DbConnection instance;

    private DbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        properties.put("user","postgres");
        properties.put("password","123456");
        String url = "jdbc:postgresql://localhost:5432/my_first_db";

        try {
            DbConnection.connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbConnection getInstance(){
        if (instance == null){
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {

        return connection;
	}

//    public static Connection getConnection2() throws SQLException, ClassNotFoundException {
//        return DbConnection.getInstance().getConnection();
//    }
}

