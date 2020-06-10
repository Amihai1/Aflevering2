package EKG;

import DTO.PatientDTO;

public interface DataListener {
   //Her kan vi så få data
    void notify(PatientDTO data);
}
