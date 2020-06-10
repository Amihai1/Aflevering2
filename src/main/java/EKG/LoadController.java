package EKG;

import DAOInterfaces.TempDAO;
import DAOMySQLImpl.TempDAOMySQLImpl;
import DTO.PatientDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class LoadController {
    public DatePicker datePicker;
    public TextArea DataArea;
    // knap med vores Load controller
    public void loadData(ActionEvent actionEvent) {
            LocalDateTime localDateTime = datePicker.getValue().atStartOfDay();
            Timestamp time = Timestamp.valueOf(localDateTime);
            TempDAO dao = new PatientDAO();
            TempDAO tdao = new TempDAOMySQLImpl();
            List<PatientDTO> patientData = dao.loadData(time);
            //List<PatientDTO> patientTemp = tdao.loadData(time);
            String text = " ";
            for (PatientDTO data: patientData) {
                text +=  "idPatientData: " + data.getId()  + ", Temp: " + data.getTemp() + ", SpO2: " + data.getSpO2() +"%" + ", BPM: " + data.getBPM() + ", Time: " + data.getTime()+ "\r\n";
            }
            DataArea.setText(text);

    }

}
