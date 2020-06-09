package EKG;

public interface DataListener {
   //Her kan vi så få data
    void notify(PatientDTO data);
}
