import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.SQLException;

import static javafx.application.Application.launch;

public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("viewMain.fxml"));
        primaryStage.setTitle("Fruit Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("icon.jpg"));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    public static void main(String[] args){
        launch(args);
    }
}
