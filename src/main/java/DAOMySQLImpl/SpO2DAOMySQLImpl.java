package DAOMySQLImpl;

import DAOInterfaces.SpO2DAO;
import DTO.SpO2DTO;
import Connectors.MySQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpO2DAOMySQLImpl implements SpO2DAO {
    @Override
    public void save(SpO2DTO spo2DTO) {
        Connection conn = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO spo2data (patientid,spo2,time) VALUES (?,?,?)");
            preparedStatement.setInt(1, spo2DTO.getPatientid());
            preparedStatement.setDouble(2, spo2DTO.getSpo2());
            preparedStatement.setTimestamp(3, spo2DTO.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        @Override
    public void batchsave(List<SpO2DTO> batch) {

    }

    @Override
    public List<SpO2DTO> loadData(Timestamp time,int patientid) {
        List<SpO2DTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM spo2data WHERE  time > ? AND patientid=?");
            preparedStatement.setTimestamp(1, time);
            preparedStatement.setInt(2, patientid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SpO2DTO spo2DTO = new SpO2DTO();
                spo2DTO.setSpo2(resultSet.getDouble("spo2"));
                data.add(spo2DTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
