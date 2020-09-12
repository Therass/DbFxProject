package dbConnection;

import parameters.DbParameters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDbConnection implements DbConnection {

    public SqlDbConnection() {
        try {
            Class.forName(DbParameters.getInstance().getJDBC_DRIVER());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DbParameters.getInstance().getDB_URL(),
                DbParameters.getInstance().getDB_USERNAME(),
                DbParameters.getInstance().getDB_USERPASS()
        );
    }

}

