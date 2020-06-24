/** @author Amihai */
package DAOMySQLImpl;

import Connectors.MySQLConnector;
import DAOInterfaces.PatientDAO;
import DTO.PatientDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOMySQLImpl implements PatientDAO {
    @Override
    public List<PatientDTO> search(String cpr, String fornavn, String efternavn) {
        List<PatientDTO> data = new ArrayList<>();
        Connection conn = MySQLConnector.getConn();
        fornavn = "%"+fornavn+"%";
        efternavn = "%"+efternavn+"%";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM patient WHERE cpr = ? OR fornavn LIKE ? OR efternavn LIKE ?");
            preparedStatement.setString(1,cpr);
            preparedStatement.setString(2,fornavn);
            preparedStatement.setString(3,efternavn);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setId(resultSet.getInt("patientid"));
                patientDTO.setCpr(resultSet.getString("cpr"));
                patientDTO.setFornavn(resultSet.getString("fornavn"));
                patientDTO.setEfternavn(resultSet.getString("efternavn"));
                data.add(patientDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
