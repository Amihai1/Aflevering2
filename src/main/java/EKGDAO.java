import EKG.DAO;
import EKG.PatientDTO;

import java.sql.Timestamp;
import java.util.List;

public class EKGDAO implements DAO {

    @Override
    public void save(PatientDTO patientDTO) {

    }

    @Override
    public void batchsave(List<PatientDTO> batch) {

    }



    @Override
    public List<PatientDTO> loadData(Timestamp time) {
        return null;
    }

}