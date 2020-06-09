package EKG;

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
            DAO DAO = new DAOSimImpl();
            List<PatientDTO> patientData = DAO.loadData(time);
            String text = " ";
            for (PatientDTO data: patientData) {
                text +=  "idPatientData: " + data.getId()  + ", Temp: " + data.getTemp() + ", SpO2: " + data.getSpO2() + ", BPM: " + data.getBPM() + ", Time: " + data.getTime()+ "\r\n";
            }
            DataArea.setText(text);

    }

}
