import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./fxml/dbChoose.fxml"));
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
