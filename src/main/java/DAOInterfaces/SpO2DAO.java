/** @author Amihai */
package DAOInterfaces;

import DTO.SpO2DTO;
import DTO.TempDTO;

import java.sql.Timestamp;
import java.util.List;

public interface SpO2DAO {
    void save(SpO2DTO spo2DTO);

    List<SpO2DTO> loadData(Timestamp time,int patientid);


}
