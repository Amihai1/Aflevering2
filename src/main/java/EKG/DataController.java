package EKG;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;


public class DataController extends Application implements DataListener {

    public static void main(String[] args) {

        launch(args);

    }


    @Override
    public void notify(PatientDTO data) {

    }

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EkgGUI.fxml"));
        FlowPane flowPane = loader.load();
        primaryStage.setScene(new Scene(flowPane));
        primaryStage.show();

    }


    public void buttonPressed(ActionEvent actionEvent) {

    }
}
