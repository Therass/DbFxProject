package initdb;

import dbConnection.SqlDbConnection;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class InitSqlDb {
    public InitSqlDb() {

        try (Connection connection = new SqlDbConnection().getConnection()) {

            ScriptRunner scriptRunner = new ScriptRunner(connection);

            Reader reader = new BufferedReader(new FileReader("src/main/resources/sqlScripts/createtable.sql"));

            scriptRunner.runScript(reader);


            reader = new BufferedReader(new FileReader("src/main/resources/sqlScripts/inserttable.sql"));

            scriptRunner.runScript(reader);

        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
