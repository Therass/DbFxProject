import db.FruitH2SqlService;
import db.FruitServiceMongoDb;
import db.PgDbConnection;
import db.FruitDBService;
import db.FruitPostgresSqlService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

public class Controller {

	private Statement statement;

	private FruitDBService fruitDBService;

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
	private TextField fruitTextField;

	@FXML
	private TextField sortTextField;

	@FXML
	private TextField amountTextField;

	@FXML
	private TextField providerTextField;

	@FXML
	private void initialize() {

		fruitDBService = new FruitPostgresSqlService();

		fillTableView();
		tableDb.getSortOrder().add(fruitColumn);

		TextFields.bindAutoCompletion(fruitTextField, t -> {
			return fruitAutoComplete.stream().filter(elem
				-> {
				return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
			}).collect(Collectors.toList());
		}).setVisibleRowCount(3);

		TextFields.bindAutoCompletion(sortTextField, t -> {
			return sortAutoComplete.stream().filter(elem
				-> {
				return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
			}).collect(Collectors.toList());
		}).setVisibleRowCount(3);

		TextFields.bindAutoCompletion(providerTextField, t -> {
			return providerAutoComplete.stream().filter(elem
				-> {
				return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
			}).collect(Collectors.toList());
		}).setVisibleRowCount(3);


	}

	@FXML
	public void fillTableView() {

		idColumn.setCellValueFactory(new PropertyValueFactory<Row, Integer>("id"));
		fruitColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("fruit"));
		sortColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("sort"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("amount"));
		providerColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("provider"));

		//con.setAutoCommit(true);
		ObservableList<Row> RowData = FXCollections.observableArrayList();

		try {
			statement = PgDbConnection.getInstance().getConnection().createStatement();
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

		fruitTextField.clear();
		sortTextField.clear();
		amountTextField.clear();
		providerTextField.clear();
	}

	@FXML
	private void addDb() throws SQLException {
		String fruitText = fruitTextField.getText();
		String sortText = sortTextField.getText();
		String amountText = amountTextField.getText();
		String providerText = providerTextField.getText();

		if (fruitText.isEmpty() || sortText.isEmpty() || amountText.isEmpty() || providerText.isEmpty()) {
			alertWindow = new AlertWindow("Fill all textfields");
		}else {
            int parsedAmount = Integer.parseInt(amountText);
            fruitDBService.insertIntoMain(fruitText,sortText, parsedAmount,providerText);
        }

	}

	@FXML
	private void updateDb() throws SQLException {

		String update = "UPDATE main_table SET  amount = ? WHERE fruit LIKE ? AND sort LIKE ? AND provider LIKE ?";
		PreparedStatement statement = PgDbConnection.getInstance().getConnection().prepareStatement(update);
        if (fruitTextField.getText().isEmpty() || amountTextField.getText().isEmpty()) {
            alertWindow = new AlertWindow("Fill fruit and amount textfield");
        } else {
            statement.setInt(1, Integer.parseInt(amountTextField.getText()));
            statement.setString(2, fruitTextField.getText());
            statement.setString(3, sortTextField.getText().isEmpty() ? "%" : sortTextField.getText());
            statement.setString(4, providerTextField.getText().isEmpty() ? "%" : providerTextField.getText());
            statement.execute();
            reloadTableView();
        }
	}

	@FXML
	private void removeDb() throws SQLException {
		//statement.executeUpdate("Delete from main_table where provider= 'Russia';");
		String delete = "DELETE FROM main_table WHERE fruit LIKE ? AND sort LIKE ? AND provider LIKE ?";
		PreparedStatement statement = PgDbConnection.getInstance().getConnection().prepareStatement(delete);
        if (fruitTextField.getText().isEmpty()) {
            alertWindow = new AlertWindow("Fill fruit textfield");
        }
		statement.setString(1, fruitTextField.getText());
		statement.setString(2, sortTextField.getText().isEmpty() ? "%" : sortTextField.getText());
		//statement.setInt(3, Integer.parseInt(amountText.getText()));
		statement.setString(3, providerTextField.getText().isEmpty() ? "%" : providerTextField.getText());
		statement.execute();
		//statement.executeUpdate("insert into main_table(fruit,sort,amount,provider) values ('baba','sweet','15','Russia');");
		reloadTableView();
	}

	@FXML
	private void reloadTableView() throws SQLException {
		fillTableView();

	}

}