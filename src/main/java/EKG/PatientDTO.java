package EKG;

import java.sql.Time;
import java.sql.Timestamp;


public class PatientDTO {
    private double sample;
    private Timestamp time;
    private double temp;
    private double SpO2;
    private double BPM;
    private int id;
    private String patientId;
    public PatientDTO(double sample, Timestamp time) {
        this.sample = sample;
        this.time = time;
    }

    public PatientDTO() {

    }

    public double getSample() {
        return sample;
    }

    public void setSample(double sample) {
        this.sample = sample;
    }

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

