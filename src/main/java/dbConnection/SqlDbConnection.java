package dbConnection;

import parameters.DbParameters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDbConnection implements DbConnection {

    public SqlDbConnection() {
        try {
            Class.forName(DbParameters.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DbParameters.DB_URL,
                DbParameters.DB_USERNAME,
                DbParameters.DB_USERPASS
        );
    }

}

