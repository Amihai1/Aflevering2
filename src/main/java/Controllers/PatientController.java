package Controllers;

import DAOInterfaces.PatientDAO;
import DAOMySQLImpl.PatientDAOMySQLImpl;
import DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class PatientController {
    public TextField cprField;
    public TextField fField;
    public TextField eField;
    public TextField patientField;
    public ListView<String> listView;
    private List<PatientDTO> patientdata;

    public void søgeknap(ActionEvent actionEvent) {
        String cpr = cprField.getText();
        String fornavn = fField.getText();
        String efternavn = eField.getText();
        PatientDAO patientDAO = new PatientDAOMySQLImpl();
        patientdata = patientDAO.search(cpr, fornavn, efternavn);
        ObservableList<String> text = FXCollections.observableArrayList();
        for (PatientDTO data : patientdata) {
            text.add("patientid: " + data.getId() + ", CPR: " + data.getCpr() + ", Fornavn: " + data.getFornavn() + ", Efternavn: " + data.getEfternavn());
        }
        listView.setItems(text);
    }
    public void guiknap(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            Stage loadStage = new Stage();
            loadStage.setScene((new Scene(anchorPane)));
            loadStage.show();
            DataController controller = fxmlLoader.<DataController>getController();
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            PatientDTO patientDTO = patientdata.get(selectedIndex);
            controller.setpatientid(patientDTO.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
