/** @author Amihai */
package DAOMySQLImpl;

import DAOInterfaces.BPMDAO;
import DTO.BPMDTO;
import Connectors.MySQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BPMDAOMySQLImpl implements BPMDAO {
    @Override
    public void save(BPMDTO bpmDTO) {
        Connection conn = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO bpmdata (patientid,bpm,time) VALUES (?,?,?)");
            preparedStatement.setInt(1, bpmDTO.getPatientid());
            preparedStatement.setDouble(2, bpmDTO.getBpm());
            preparedStatement.setTimestamp(3, bpmDTO.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<BPMDTO> loadData(Timestamp time) {
        List<BPMDTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM tempdata WHERE  time > ?");
            preparedStatement.setTimestamp(1, time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BPMDTO bpmDTO = new BPMDTO();
                bpmDTO.setBpm(resultSet.getInt("bpm"));
                data.add(bpmDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
