package Controllers;

import DAOInterfaces.BPMDAO;
import DAOInterfaces.TempDAO;
import DAOMySQLImpl.BPMDAOMySQLImpl;
import DAOMySQLImpl.TempDAOMySQLImpl;
import DTO.BPMDTO;
import DTO.TempDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class HistorikController {
    public TextArea bpmArea;
    public DatePicker datepickerBPM;
    public TextArea tempArea;
    public DatePicker datepickerTemp;
    

    public void BPMload(ActionEvent actionEvent) {
        LocalDateTime localDateTime = datepickerBPM.getValue().atStartOfDay();
        Timestamp time = Timestamp.valueOf(localDateTime);
        BPMDAO bpmdao = new BPMDAOMySQLImpl();
        List<BPMDTO> bpmdata = bpmdao.loadData(time);
        String textbpm = " ";
        for (BPMDTO data : bpmdata) {
            textbpm += "Patientid" + data.getPatientid() + "BPM" + data.getBpm() + "Time" + data.getTime() + "\r\n";
        }
        bpmArea.setText(textbpm);
    }

    public void tempload(ActionEvent actionEvent) {
        LocalDateTime localDateTime = datepickerTemp.getValue().atStartOfDay();
        Timestamp time = Timestamp.valueOf(localDateTime);
        TempDAO tempdao = new TempDAOMySQLImpl();
        List<TempDTO> tempdata = tempdao.loadData(time);
        String texttemp = " ";
        for (TempDTO data : tempdata) {
            texttemp += "Patientid" + data.getPatientid() + "Temp" + data.getTemp()+ "\u00B0" + "C" + "Time" + data.getTime() + "\r\n";
        }
        bpmArea.setText(texttemp);
    }
}
