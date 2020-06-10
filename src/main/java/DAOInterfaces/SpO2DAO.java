package DAOInterfaces;

import DTO.SpO2DTO;
import DTO.TempDTO;

import java.sql.Timestamp;
import java.util.List;

public interface SpO2DAO {
    void save(SpO2DTO spo2DTO);
    void batchsave(List<SpO2DTO> batch);
    List<SpO2DTO> loadData(Timestamp time);


}
