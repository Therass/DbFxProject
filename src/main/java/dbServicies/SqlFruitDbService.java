package dbServicies;

import Controllers.ViewMainController;
import dbConnection.SqlDbConnection;
import entitys.FruitRow;
import parameters.TableParameters;
import util.AlertWindow;

import java.sql.*;

public class SqlFruitDbService implements FruitDbService {

    @Override
    public void selectFromMain() {

    }

    @Override
    public void insertIntoMain(String fruit, String sort, String amount, String provider) {
        try (Connection connection = new SqlDbConnection().getConnection()) {
            String insert = "INSERT INTO " +
                    TableParameters.TABLE + " ( " +
                    TableParameters.FRUIT + ", " +
                    TableParameters.SORT + ", " +
                    TableParameters.AMOUNT + ", " +
                    TableParameters.PROVIDER + " ) " +
                    " VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insert);

            statement.setString(1, fruit);
            statement.setString(2, sort);
            statement.setInt(3, Integer.parseInt(amount));
            statement.setString(4, provider);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            new AlertWindow("Amount field must be a number");
        }
    }

    @Override
    public void updateMain(String fruit, String sort, String amount, String provider) {

        try (Connection connection = new SqlDbConnection().getConnection()) {
            String update = "UPDATE " +
                    TableParameters.TABLE + " SET " +
                    TableParameters.AMOUNT + " = ? WHERE " +
                    TableParameters.FRUIT + " LIKE ? AND " +
                    TableParameters.SORT + " LIKE ? AND " +
                    TableParameters.PROVIDER + " LIKE ?";


            PreparedStatement statement = connection.prepareStatement(update);

            statement.setInt(1, Integer.parseInt(amount));
            statement.setString(2, fruit);
            statement.setString(3, sort.isEmpty() ? "%" : sort);
            statement.setString(4, provider.isEmpty() ? "%" : provider);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            new AlertWindow("Amount field must be a number");
        }
    }

    @Override
    public void removeFromMain(String fruit, String sort, String provider) {
        try (Connection connection = new SqlDbConnection().getConnection()) {
            String delete = " DELETE FROM " +
                    TableParameters.TABLE + " WHERE " +
                    TableParameters.FRUIT + " LIKE ? AND " +
                    TableParameters.SORT + " LIKE ? AND " +
                    TableParameters.PROVIDER + " LIKE ?";

            PreparedStatement statement = connection.prepareStatement(delete);

            statement.setString(1, fruit);
            statement.setString(2, sort.isEmpty() ? "%" : sort);
            statement.setString(3, provider.isEmpty() ? "%" : provider);
            statement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            new AlertWindow("Amount field must be a number");
        }

    }

    @Override
    public void selectAllFromMain() {
        try (Connection connection = new SqlDbConnection().getConnection()) {
            String select = "select * from " + TableParameters.TABLE;
            Statement statement = connection.createStatement();
            statement.executeQuery(select);
            final ResultSet resultSet = statement.getResultSet();

            ViewMainController.fruitAutoComplete.clear();
            ViewMainController.sortAutoComplete.clear();
            ViewMainController.providerAutoComplete.clear();

            while (resultSet.next()) {
                final Integer id = resultSet.getInt("id");
                final String fruit = resultSet.getString("fruit");
                final String sort = resultSet.getString(3);
                final String amount = resultSet.getString(4);
                final String provider = resultSet.getString(5);

                ViewMainController.fruitAutoComplete.add(fruit);
                ViewMainController.sortAutoComplete.add(sort);
                ViewMainController.providerAutoComplete.add(provider);

                ViewMainController.fruitRowData.add(new FruitRow(id, fruit, sort, amount, provider));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


