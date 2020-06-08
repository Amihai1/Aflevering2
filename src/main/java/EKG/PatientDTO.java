package EKG;

import java.sql.Timestamp;
import java.util.LinkedList;


public class PatientDTO {
//indkapsler diverse variable
    private Timestamp time;
    private double temp;
    private double SpO2;
    private double BPM;
    private int id;
    private String patientId;
    private double EKGSample;


    public PatientDTO() {
    }
    //laver Getter og Setter. (Accessors)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getSpO2() {
        return SpO2;
    }

    public void setSpO2(double spO2) {
        SpO2 = spO2;
    }

    public double getBPM() {
        return BPM;
    }

    public void setBPM(double BPM) {
        this.BPM = BPM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

}

