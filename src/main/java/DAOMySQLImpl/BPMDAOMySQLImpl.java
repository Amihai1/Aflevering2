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
    public List<BPMDTO> loadData(Timestamp time, int patientid) {
        List<BPMDTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM bpmdata WHERE  time > ? AND patientid = ?");
            preparedStatement.setTimestamp(1, time);
            preparedStatement.setInt(2, patientid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BPMDTO bpmDTO = new BPMDTO();
                bpmDTO.setBpm(resultSet.getDouble("bpm"));
                bpmDTO.setTime(resultSet.getTimestamp("Time"));
                bpmDTO.setPatientid(resultSet.getInt("Patientid"));
                data.add(bpmDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
