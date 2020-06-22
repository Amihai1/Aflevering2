


    

   package Controllers;
import DAOInterfaces.BPMDAO;
import DAOInterfaces.EKGDAO;
import DAOInterfaces.SpO2DAO;
import DAOInterfaces.TempDAO;
import DAOMySQLImpl.BPMDAOMySQLImpl;
import DAOMySQLImpl.EKGDAOMySQLImpl;
import DAOMySQLImpl.SpO2DAOMySQLImpl;
import DAOMySQLImpl.TempDAOMySQLImpl;
import DTO.BPMDTO;
import DTO.EKGDTO;
import DTO.SpO2DTO;
import DTO.TempDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polyline;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
    public class HistorikController {
        public TextArea bpmArea;
        public DatePicker datepickerBPM;
        public TextArea tempArea;
        public DatePicker datepickerTemp;
        public TextArea spo2Area;
        public DatePicker datepickerSpO2;
        public Polyline ekgPane;
        public DatePicker datepickerEKG;
        public TextField patientid;

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

        public void spo2load(ActionEvent actionEvent) {
            SpO2DTO spO2DTO = new SpO2DTO();
            LocalDateTime localDateTime = datepickerSpO2.getValue().atStartOfDay();
            Timestamp time = Timestamp.valueOf(localDateTime);
            SpO2DAO spo2dao = new SpO2DAOMySQLImpl();
            List<SpO2DTO> spo2data = spo2dao.loadData(time,spO2DTO.getPatientid());
            String textspo2 = " ";
            for (SpO2DTO data : spo2data) {
                textspo2 += "Patientid" + data.getPatientid() + "SpO2" + data.getSpo2()+ "%" + "Time" + data.getTime() + "\r\n";
            }
            spo2Area.setText(textspo2);
        }
        /*public void EKGload(ActionEvent actionEvent) {
            LocalDateTime localDateTime = datepickerEKG.getValue().atStartOfDay();
            Timestamp time = Timestamp.valueOf(localDateTime);
            EKGDAO ekgdao = new EKGDAOMySQLImpl();
            List<EKGDTO> ekgdata = ekgdao.loadData(time);
            String textekg = " ";
            for (EKGDTO data : ekgdata) {
                textekg += data.getEkg();
            }
            ekgPane.getPoints(ekgdata);
        }*/


    public void tempload(ActionEvent actionEvent) {
        TempDTO tempDTO = new TempDTO();
        tempDTO.setPatientid(Integer.parseInt(patientid.getText()));
        LocalDateTime localDateTime = datepickerTemp.getValue().atStartOfDay();
        Timestamp time = Timestamp.valueOf(localDateTime);
        TempDAO tempdao = new TempDAOMySQLImpl();
        List<TempDTO> tempdata = tempdao.loadData(time, tempDTO.getPatientid() );
        String texttemp = " ";
        for (TempDTO data : tempdata) {
            texttemp += "Patientid" + data.getPatientid() + "Temp" + data.getTemp()+ "\u00B0" + "C" + "Time" + data.getTime() + "\r\n";
        }
        tempArea.setText(texttemp);
    }

    public void setpatientid(int id) {this.patientid.setText(String.valueOf(id)); }
}
