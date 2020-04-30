package EKG;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;


public class DataController implements DataListener {
    public TextArea DataOutput;
    public TextField DataField;
    private boolean record;
    private DataSampleReader sampleReader = new DataSampleReaderSimImpl();


    public void buttonPressed(ActionEvent actionEvent) {
        DataObservable DataStation = new PatientDataGenerator();
        new Thread(DataStation).start();
        DataStation.register(this);
    }

    public void DataRecord(ActionEvent actionEvent) {
        this.record = !this.record;
    }


    @Override
    public void notify(PatientDTO data) {
        String text = DataOutput.getText();
        text += "Time: " + data.getTime() + "Temperatur: " + data.getTemp() + "SpO2: " + data.getSpO2() + "BPM: " + data.getBPM() + "\r\n";
        DataOutput.setText(text);
        if (this.record) {
            data.setPatientId(DataField.getText());
            sampleReader.save(data);
        }
    }


    public void LoadData(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoadGui.fxml"));
        try {
            FlowPane flowPane = fxmlLoader.load();
            Stage loadStage = new Stage();
            loadStage.setScene((new Scene(flowPane)));
            loadStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
