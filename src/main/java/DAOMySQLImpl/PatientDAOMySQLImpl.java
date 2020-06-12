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
        try {
            PreparedStatement statement = conn.prepareStatement("select*from patientdata where cpr =?,fornavn = ?,efternavn=?");
            statement.setString(1,cpr);
            statement.setString(2,fornavn);
            statement.setString(3,efternavn);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setCpr(resultSet.getString("cpr"));
                patientDTO.setFornavn(resultSet.getString("fornavn"));
                patientDTO.setEfternavn(resultSet.getString("efternavn"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
