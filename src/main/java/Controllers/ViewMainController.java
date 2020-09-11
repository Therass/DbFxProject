package Controllers;

import dbServicies.FruitDbService;
import dbServicies.NoSqlFruitDbService;
import dbServicies.SqlFruitDbService;
import entitys.FruitRow;
import initdb.InitNoSqlDb;
import initdb.InitSqlDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import parameters.DbParameters;
import parameters.TableParameters;
import util.AlertWindow;
import util.TextFieldsValidator;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ViewMainController {

    private FruitDbService fruitDBService;

    public static ObservableList<FruitRow> fruitRowData;

    public static Set<String> fruitAutoComplete = new LinkedHashSet<String>();
    public static Set<String> sortAutoComplete = new LinkedHashSet<String>();
    public static Set<String> providerAutoComplete = new LinkedHashSet<String>();

    @FXML
    private Button reloadButton;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeButton;

    @FXML
    private TableView<FruitRow> tableDb;

    @FXML
    private TableColumn<FruitRow, Integer> idColumn;

    @FXML
    private TableColumn<FruitRow, String> fruitColumn;

    @FXML
    private TableColumn<FruitRow, String> sortColumn;

    @FXML
    private TableColumn<FruitRow, String> amountColumn;

    @FXML
    private TableColumn<FruitRow, String> providerColumn;

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

        if (DbParameters.IS_SQL) {
            fruitDBService = new SqlFruitDbService();
            new InitSqlDb();
        } else {
            //TODO: NOSQL BEHAVIOR
            new AlertWindow("NO BEHAVIOR FOR NOSQL DATABASES YET");

            fruitDBService = new NoSqlFruitDbService();
            new InitNoSqlDb();
        }

        fillTableView();

        fillTextFieldsAutoComplete();

    }

    @FXML
    public void fillTableView() {

        idColumn.setCellValueFactory(new PropertyValueFactory<FruitRow, Integer>(TableParameters.ID));
        fruitColumn.setCellValueFactory(new PropertyValueFactory<FruitRow, String>(TableParameters.FRUIT));
        sortColumn.setCellValueFactory(new PropertyValueFactory<FruitRow, String>(TableParameters.SORT));
        amountColumn.setCellValueFactory(new PropertyValueFactory<FruitRow, String>(TableParameters.AMOUNT));
        providerColumn.setCellValueFactory(new PropertyValueFactory<FruitRow, String>(TableParameters.PROVIDER));

        //con.setAutoCommit(true);
        fruitRowData = FXCollections.observableArrayList();

        fruitDBService.selectAllFromMain();

        tableDb.setItems(fruitRowData);
        tableDb.getSortOrder().add(fruitColumn);

    }

    @FXML
    private void addDb() {
        if (new TextFieldsValidator().isValid(fruitTextField.getText(),
                sortTextField.getText(),
                amountTextField.getText(),
                providerTextField.getText())) {
            fruitDBService.insertIntoMain(fruitTextField.getText(),
                    sortTextField.getText(),
                    amountTextField.getText(),
                    providerTextField.getText());

            reloadTableView();
        }
    }

    @FXML
    private void updateDb() {

        if (new TextFieldsValidator().isValid(fruitTextField.getText(), amountTextField.getText())) {
            fruitDBService.updateMain(fruitTextField.getText(),
                    sortTextField.getText(),
                    amountTextField.getText(),
                    providerTextField.getText());
            reloadTableView();
        }
    }

    @FXML
    private void removeDb() {
        if (new TextFieldsValidator().isValid(fruitTextField.getText())) {
            fruitDBService.removeFromMain(fruitTextField.getText(),
                    sortTextField.getText(),
                    providerTextField.getText());
            reloadTableView();
        }
    }

    @FXML
    private void reloadTableView() {
        fillTableView();
        fillTextFieldsAutoComplete();
        clearTextFields();
    }

    private void fillTextFieldsAutoComplete() {

        AutoCompletionBinding f = TextFields.bindAutoCompletion(fruitTextField, t -> {
            return fruitAutoComplete.stream().filter(elem
                    -> {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        });
        f.setPrefWidth(130);
        f.setVisibleRowCount(4);

        AutoCompletionBinding s = TextFields.bindAutoCompletion(sortTextField, t -> {
            return sortAutoComplete.stream().filter(elem
                    -> {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        });
        s.setPrefWidth(130);
        s.setVisibleRowCount(4);

        AutoCompletionBinding p = TextFields.bindAutoCompletion(providerTextField, t -> {
            return providerAutoComplete.stream().filter(elem
                    -> {
                return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
            }).collect(Collectors.toList());
        });
        p.setPrefWidth(130);
        p.setVisibleRowCount(4);

    }

    public void clearTextFields() {
        fruitTextField.clear();
        sortTextField.clear();
        amountTextField.clear();
        providerTextField.clear();
    }

}