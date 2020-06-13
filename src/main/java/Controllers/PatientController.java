package Controllers;

import DAOInterfaces.PatientDAO;
import DAOMySQLImpl.PatientDAOMySQLImpl;
import DTO.PatientDTO;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.util.List;


public class PatientController {
    public TextField cprField;
    public TextField fField;
    public TextField eField;
    public TextArea DataArea;
    public void søgeknap(ActionEvent actionEvent){
        String cpr = cprField.getText();
        String fornavn = fField.getText();
        String efternavn = eField.getText();
        PatientDAO patientDAO = new PatientDAOMySQLImpl();
        List<PatientDTO> patientdata = patientDAO.search(cpr,fornavn,efternavn);
        String text = " ";
        for(PatientDTO data: patientdata){
            text += "patientid: " + data.getId() + ", CPR: " + data.getCpr() + ", Fornavn: " + data.getFornavn() + ", Efternavn: " + data.getEfternavn() + "\r\n";
        }
        DataArea.setText(text);
    }
}
