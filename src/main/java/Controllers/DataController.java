package Controllers;

import Calculator.*;
import DAOInterfaces.EKGDAO;
import DAOInterfaces.SpO2DAO;
import DAOInterfaces.TempDAO;
import DAOMySQLImpl.EKGDAOMySQLImpl;
import DAOMySQLImpl.SpO2DAOMySQLImpl;
import DAOMySQLImpl.TempDAOMySQLImpl;
import DTO.*;
import Listener.BPMListener;
import Listener.EKGListener;
import Listener.SpO2Listener;
import Listener.TempListener;
import Observable.BPMObservable;
import Observable.EKGObservable;
import Observable.SpO2Observable;
import Observable.TempObservable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class DataController implements BPMListener, EKGListener, SpO2Listener, TempListener {
    public TextArea BPMArea;
    public TextArea temparea;
    public TextArea spo2area;
    public TextField patientid;
    public Polyline Linje;
    private boolean record;
    private double x = 0.0;
    private SpO2DAO spo2Reader = new SpO2DAOMySQLImpl();
    private TempDAO tempReader = new TempDAOMySQLImpl();
    private EKGDAO ekgdao = new EKGDAOMySQLImpl();
    private EKGDTO ekgdto;

    public void setpatientid(int id) {
        this.patientid.setText(String.valueOf(id));
    }

    //knappen starter printningen af Data
    public void tempbutton(ActionEvent actionEvent) {
        TempObservable TempStation = new TempCalculator();
        new Thread(TempStation).start();
        TempStation.register(this);

    }

    public void TempRecord(ActionEvent actionEvent) {
        TempObservable TempStation = new TempCalculator();
        TempStation.register(this);
        this.record = !this.record;
    }

    public void spo2button(ActionEvent actionEvent) {
        SpO2Observable spo2 = new SpO2Calculator();
        new Thread(spo2).start();
        spo2.register(this);

    }

    public void SpO2Record(ActionEvent actionEvent) {
        SpO2Observable spo2 = new SpO2Calculator();
        spo2.register(this);
        this.record = !this.record;
    }

    public void ekgbutton(ActionEvent actionEvent) {

        EKGObservable ekg = new EKGGenerator();
        new Thread((Runnable) ekg).start();
        ekg.register(this);
        this.record = !this.record;
    }

    public void ekgRecord(ActionEvent actionEvent) {

    }

    public void bpmbutton(ActionEvent actionEvent) {
        BPMObservable bpm = new BPMCalculator();
        new Thread(bpm).start();
        bpm.register(this);
    }


    public void LoadData(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/HistorikGUI.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            Stage loadStage = new Stage();
            loadStage.setScene((new Scene(anchorPane)));
            loadStage.show();
            HistorikController hcontroller = fxmlLoader.<HistorikController>getController();
            String patientDTO = patientid.getText();
            hcontroller.setpatientid(Integer.parseInt(patientDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void notify(BPMDTO data) {
        String text = BPMArea.getText();
        text = " " + data.getBpm();
        BPMArea.setText(text);
        if (this.record) {
            data.setPatientid(Integer.parseInt(String.valueOf(BPMArea.getText())));

        }
    }


    @Override
    public void notify(SpO2DTO data) {
        String text = spo2area.getText();
        text = " " + data.getSpo2() + "%";
        spo2area.setText(text);
        if (this.record) {
            data.setPatientid(Integer.parseInt(patientid.getText()));
            spo2Reader.save(data);
        }
    }

    @Override
    public void notify(TempDTO temp) {
        String text = temparea.getText();
        text = " " + temp.getTemp() + "\u00B0" + "C";
        temparea.setText(text);
        if (this.record) {
            temp.setPatientid(Integer.parseInt(patientid.getText()));
            System.out.println(temp.getPatientid());
            tempReader.save(temp);
        }
    }

    @Override
    public void notify(LinkedList<EKGDTO> data) {
        Platform.runLater(() -> {
            List<Double> point = new LinkedList<>();
            List<Integer> ekgdtos = new LinkedList<>();
            for (EKGDTO ekgdto : data) {
                point.add(x);
                point.add((double)ekgdto.getEkg()/6);
                ekgdtos.add(ekgdto.getEkg());
                x++;
                ekgdto.setPatientid(Integer.parseInt(patientid.getText()));


            }
            if (x > 800) {
                x = 0;
                Linje.getPoints().clear();
            }
            Linje.getPoints().addAll(point);
        });
        new Thread(() -> {
            if (this.record = !this.record) {

                ekgdao.batchsave(data);
            }
        }).start();

    }

}


