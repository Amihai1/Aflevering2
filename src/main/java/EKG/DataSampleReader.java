package EKG;

import java.sql.Timestamp;
import java.util.List;

public interface DataSampleReader {
    void save(PatientDTO patientDTO);
    List<PatientDTO> loadData(Timestamp time);

}