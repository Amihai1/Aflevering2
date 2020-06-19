package DTO;

import java.sql.Timestamp;
import java.util.LinkedList;

public class EKGDTO {
    private int patientid;
    private int ekg;
    private Timestamp time;



    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getEkg() {
        return ekg;
    }

    public void setEkg(int ekg) {
        this.ekg = ekg;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
