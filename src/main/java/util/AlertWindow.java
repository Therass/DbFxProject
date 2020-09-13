package util;

import javafx.scene.control.Alert;


public class AlertWindow {

    private Alert alert;

    public AlertWindow(String text){
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();

    }

}
