import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import org.controlsfx.control.textfield.TextFields;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {

    private Statement statement;
    AlertWindow alertWindow;
    Set<String> fruitAutoComplete = new LinkedHashSet<String>();
    Set<String> sortAutoComplete = new LinkedHashSet<String>();
    Set<String> providerAutoComplete = new LinkedHashSet<String>();

    @FXML
    private Button reloadButton;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeButton;

    @FXML
    private TableView<Row> tableDb;

    @FXML
    private TableColumn<Row, Integer> idColumn;

    @FXML
    private TableColumn<Row, String> fruitColumn;

    @FXML
    private TableColumn<Row, String> sortColumn;

    @FXML
    private TableColumn<Row, String> amountColumn;

    @FXML
    private TableColumn<Row, String> providerColumn;

    @FXML
    private TextField fruitText;

    @FXML
    private TextField sortText;

    @FXML
    private TextField amountText;

    @FXML
    private TextField providerText;

    @FXML
    private void initialize() {

        fillTableView();
        tableDb.getSortOrder().add(fruitColumn);

        TextFields.bindAutoCompletion(fruitText,  t -> {
            return fruitAutoComplete.stream().filter(elem
                    -> {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        }).setVisibleRowCount(3);


        TextFields.bindAutoCompletion(sortText, t -> {
            return sortAutoComplete.stream().filter(elem
                    -> {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        }).setVisibleRowCount(3);

        TextFields.bindAutoCompletion(providerText,  t -> {
            return providerAutoComplete.stream().filter(elem
                    -> {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        }).setVisibleRowCount(3);


    }

    @FXML
    public void fillTableView(){

        idColumn.setCellValueFactory(new PropertyValueFactory<Row, Integer>("id"));
        fruitColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("fruit"));
        sortColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("sort"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("amount"));
        providerColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("provider"));

        //con.setAutoCommit(true);
        ObservableList<Row> RowData = FXCollections.observableArrayList();

        try {
            statement = DbConnection.getInstance().getConnection().createStatement();
            statement.executeQuery("select * from main_table");
            final ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                final Integer id = resultSet.getInt("id");
                final String fruit = resultSet.getString("fruit");
                final String sort = resultSet.getString(3);
                final String amount = resultSet.getString(4);
                final String provider = resultSet.getString(5);

                fruitAutoComplete.add(fruit);
                sortAutoComplete.add(sort);
                providerAutoComplete.add(provider);

                RowData.add(new Row(id, fruit, sort, amount, provider));

            }
            tableDb.setItems(RowData);
            tableDb.getSortOrder().add(fruitColumn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        fruitText.clear();
        sortText.clear();
        amountText.clear();
        providerText.clear();
    }

    @FXML
    private void addDb() throws SQLException {

        String insert = "INSERT INTO main_table(fruit, sort, amount, provider) VALUES (?,?,?,?)";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(insert);
        if (fruitText.getText().isEmpty() ||
            sortText.getText().isEmpty() ||
            amountText.getText().isEmpty() ||
            providerText.getText().isEmpty())  alertWindow = new AlertWindow("Fill all textfields");
        else {
            statement.setString(1, fruitText.getText());
            statement.setString(2, sortText.getText());
            statement.setInt(3, Integer.parseInt(amountText.getText()));
            statement.setString(4, providerText.getText());
            statement.execute();
            reloadTableView();
        }

    }

    @FXML
    private void updateDb() throws SQLException {

        String update = "UPDATE main_table SET  amount = ? WHERE fruit LIKE ? AND sort LIKE ? AND provider LIKE ?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(update);
        if (fruitText.getText().isEmpty() || amountText.getText().isEmpty())
            alertWindow = new AlertWindow("Fill fruit and amount textfield");
        else{
        statement.setInt(1, Integer.parseInt(amountText.getText()));
        statement.setString(2,fruitText.getText());
        statement.setString(3,sortText.getText().isEmpty() ? "%" : sortText.getText());
        statement.setString(4,providerText.getText().isEmpty() ? "%" : providerText.getText());
        statement.execute();
        reloadTableView();
        }
    }

    @FXML
    private void removeDb() throws SQLException {
        //statement.executeUpdate("Delete from main_table where provider= 'Russia';");
            String delete = "DELETE FROM main_table WHERE fruit LIKE ? AND sort LIKE ? AND provider LIKE ?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(delete);
        if (fruitText.getText().isEmpty()) alertWindow = new AlertWindow("Fill fruit textfield");
        statement.setString(1,fruitText.getText());
        statement.setString(2,sortText.getText().isEmpty() ? "%" : sortText.getText());
        //statement.setInt(3, Integer.parseInt(amountText.getText()));
        statement.setString(3,providerText.getText().isEmpty() ? "%" : providerText.getText());
        statement.execute();
        //statement.executeUpdate("insert into main_table(fruit,sort,amount,provider) values ('baba','sweet','15','Russia');");
        reloadTableView();
    }

    @FXML
    private void reloadTableView() throws SQLException {
        fillTableView();

    }

}