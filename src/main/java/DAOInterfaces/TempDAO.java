package DAOInterfaces;

import DTO.TempDTO;

import java.sql.Timestamp;
import java.util.List;

public interface TempDAO {
    void save(TempDTO tempDTO);
    void batchsave(List<TempDTO> batch);
    List<TempDTO> loadData(Timestamp time, int patientid);


}