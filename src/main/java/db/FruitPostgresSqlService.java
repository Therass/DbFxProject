package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FruitPostgresSqlService implements FruitDBService {

	private PgDbConnection pgDbConnection;

	@Override
	public void insertIntoMain(String fruit, String sort, int amount, String provider) {
		try (Connection connection = pgDbConnection.getConnection()) {
			String insert = "INSERT INTO main_table(fruit, sort, amount, provider) VALUES (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			if (fruit.isEmpty() ||
				sort.isEmpty() ||
				amount != 0 ||
				provider.isEmpty()) {
			} else {
				statement.setString(1, fruit);
				statement.setString(2, sort);
				statement.setInt(3, amount);
				statement.setString(4, provider);
				statement.execute();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void updateMain(String fruit, String sort, int amount, String provider) {

	}

	@Override
	public void removeFromMain(String fruit, String sort, String provider) {

	}

}
