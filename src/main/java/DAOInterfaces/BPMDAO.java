/** @author Amihai */
package DAOInterfaces;

import DTO.BPMDTO;

import java.sql.Timestamp;
import java.util.List;

    public interface BPMDAO {
        void save(BPMDTO bpmDTO);



        List<BPMDTO> loadData(Timestamp time);
    }
