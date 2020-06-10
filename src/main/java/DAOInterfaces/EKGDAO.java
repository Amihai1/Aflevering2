package DAOInterfaces;

import DTO.EKGDTO;


import java.sql.Timestamp;
import java.util.List;

public interface EKGDAO {
    void save(EKGDTO ekgDTO);
    void batchsave(List<EKGDTO> batch);
    List<EKGDTO> loadData(Timestamp time);
}
