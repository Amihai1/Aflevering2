package EKG;

import DAOInterfaces.TempDAO;
import DTO.PatientDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class EKGDAO implements TempDAO {
    @Override
    public void save(PatientDTO patientDTO) {

    }

    @Override
    public void batchsave(List<PatientDTO> batch) {
        Connection conn = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO PatientData(EKG) VALUES (?)");
            //preparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PatientDTO> loadData(Timestamp time) {
        return null;
    }
}
