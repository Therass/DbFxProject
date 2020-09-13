package Controllers;

import xmlRead.DbList;
import xmlRead.DbName;
import util.IsSql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import parameters.DbParameters;

import java.io.File;
import java.io.IOException;


public class DbChooseController {

    private DbList dbList;

    @FXML
    private ComboBox dbComboBox;

    @FXML
    private Button okButton;

    @FXML
    private void initialize() throws Exception {

        File file = new File("./src/main/resources/dbList.xml");
        Serializer serializer = new Persister();
        dbList = serializer.read(DbList.class, file);

        ObservableList<String> dbLists = FXCollections.observableArrayList();
        dbLists.addAll(dbList.getAllRes());

        dbComboBox.setItems(dbLists);
        //dbComboBox.getSelectionModel().selectLast();
        dbComboBox.setVisibleRowCount(5);

    }

    @FXML
    private void openWindowViewMain(javafx.event.ActionEvent actionEvent) throws IOException {

        DbParameters.getInstance().setDB_NAME((String) dbComboBox.getValue());
        DbName dbName = dbList.getResourceByName(DbParameters.getInstance().getDB_NAME());
        new IsSql(dbName.getProperty("isSQL"));
        DbParameters.getInstance().setJDBC_DRIVER(dbName.getProperty("jdbcDriver"));
        DbParameters.getInstance().setDB_URL(dbName.getProperty("dbURL"));
        DbParameters.getInstance().setDB_USERNAME(dbName.getProperty("dbUserName"));
        DbParameters.getInstance().setDB_USERPASS(dbName.getProperty("dbUserPass"));


        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/viewMain.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


    }
}
