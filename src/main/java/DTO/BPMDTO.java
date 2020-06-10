package DTO;

import DAOInterfaces.BPMDAO;

import java.sql.Timestamp;
import java.util.List;

public class BPMDTO {
   private int patientid;
   private int bpm;
   private Timestamp time;

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
