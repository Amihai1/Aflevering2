package EKG;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DataMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //Her loader vi vores Gui.
    @Override
    public void start(Stage primaryStage) {
        //og her tager vi så fat i klassen og dens resourcer
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI.fxml"));
        try {
            AnchorPane flowPane = fxmlLoader.load();
            primaryStage.setScene(new Scene(flowPane));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}