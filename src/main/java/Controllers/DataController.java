package Controllers;

import Calculator.SpO2Calculator;
import Calculator.TempCalculator;
import DAOInterfaces.SpO2DAO;
import DAOInterfaces.TempDAO;
import DAOMySQLImpl.SpO2DAOMySQLImpl;
import DAOMySQLImpl.TempDAOMySQLImpl;
import DTO.*;
import Listener.BPMListener;
import Listener.EKGListener;
import Listener.SpO2Listener;
import Listener.TempListener;
import Observable.SpO2Observable;
import Observable.TempObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;


public class DataController implements BPMListener, EKGListener, SpO2Listener, TempListener {
    public TextArea BPMArea;
    public TextArea temparea;
    public TextArea spo2area;
    public TextField patientid;
    private boolean record;
    private SpO2DAO spo2Reader = new SpO2DAOMySQLImpl();
    private TempDAO tempReader = new TempDAOMySQLImpl();

    //knappen starter printningen af Data
    public void tempbutton(ActionEvent actionEvent) {
        TempObservable TempStation = new TempCalculator();
        new Thread(TempStation).start();
        TempStation.register(this);
        this.record = !this.record;
    }
    public void spo2button(ActionEvent actionEvent){
        SpO2Observable spo2 = new SpO2Calculator();
        new Thread(spo2).start();
        spo2.register(this);
        this.record = !this.record;
    }

    public void DataRecord(ActionEvent actionEvent) {

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


    @Override
    public void notify(BPMDTO data) {
        String text = BPMArea.getText();
        text += "Time: " + data.getTime() + data.getBpm();
        BPMArea.setText(text);
        if (this.record) {
            data.setPatientid(Integer.parseInt(String.valueOf(BPMArea.getText())));
        }
    }

    @Override
    public void notify(EKGDTO data) {

    }

    @Override
    public void notify(SpO2DTO data) {
        String text = spo2area.getText();
        text = " " + data.getSpo2()+ "%";
        spo2area.setText(text);
        if (this.record) {
            data.setPatientid(Integer.parseInt(patientid.getText()));
            spo2Reader.save(data);
        }
    }
    @Override
    public void notify(TempDTO data) {
        String text = temparea.getText();
        text = " " + data.getTemp() + "\u00B0"+"C";
        temparea.setText(text);
        if (this.record) {
            data.setPatientid(Integer.parseInt(patientid.getText()));
            tempReader.save(data);
        }


    }
}
