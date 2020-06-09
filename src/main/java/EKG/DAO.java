package EKG;

import java.sql.Timestamp;
import java.util.List;

public interface DAO {
    void save(PatientDTO patientDTO);
    void batchsave(List<PatientDTO> batch);
    List<PatientDTO> loadData(Timestamp time);


}