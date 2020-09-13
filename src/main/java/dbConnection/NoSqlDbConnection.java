package dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class NoSqlDbConnection implements  DbConnection {
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
}
