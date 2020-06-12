package Controllers;

import DAOInterfaces.TempDAO;
import DAOMySQLImpl.TempDAOMySQLImpl;
import DTO.PatientDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.IOException;


public class DataController implements DataListener {
    public TextArea DataOutput;
    public TextField DataField;
    private boolean record;
    private TempDAO sampleReader = new PatientDAO();
    private TempDAO tempreader = new TempDAOMySQLImpl();

    //knappen starter printningen af Data
    public void buttonPressed(ActionEvent actionEvent) {
        DataObservable DataStation = new PatientDataGenerator();
        DataObservable BPM = new BMPGenerator();
        new Thread(BPM).start();
        new Thread(DataStation).start();
        DataStation.register(this);
    }

    public void DataRecord(ActionEvent actionEvent) {
        this.record = !this.record;
    }


    @Override
    public void notify(PatientDTO data) {
        String text = DataOutput.getText();
        text += "Time: " + data.getTime() + ", Temperatur: " + data.getTemp() + ", SpO2: " + data.getSpO2() + ", BPM: " + data.getBPM() + "\r\n";
        DataOutput.setText(text);
        if (this.record) {
            data.setPatientId(DataField.getText());
            sampleReader.save(data);
            tempreader.save(data);
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
