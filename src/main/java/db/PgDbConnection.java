package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PgDbConnection implements DbConnection{

	private String url = "jdbc:postgresql://localhost:5432/my_first_db";
	private Properties properties;

	private PgDbConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		properties = new Properties();
		properties.put("user", "postgres");
		properties.put("password", "123456");
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, properties);
	}

//    public static Connection getConnection2() throws SQLException, ClassNotFoundException {
//        return db.DbConnection.getInstance().getConnection();
//    }
}

