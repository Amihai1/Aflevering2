package DAOInterfaces;

import DTO.PatientDTO;

import java.util.List;

public interface PatientDAO {
    List<PatientDTO> search(String cpr,String fornavn, String efternavn);
}
