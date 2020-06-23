package DAOMySQLImpl;

import DAOInterfaces.TempDAO;
import DTO.PatientDTO;
import DTO.TempDTO;
import Connectors.MySQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TempDAOMySQLImpl implements TempDAO {

    @Override
    public void save(TempDTO tempDTO) {
        Connection conn = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO tempdata (patientid,temp,time) VALUES (?,?,?)");
            preparedStatement.setInt(1, tempDTO.getPatientid());
            preparedStatement.setDouble(2, tempDTO.getTemp());
            preparedStatement.setTimestamp(3, tempDTO.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchsave(List<TempDTO> batch) {

    }

    @Override
    public List<TempDTO> loadData(Timestamp time, int patientid) {
        List<TempDTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM tempdata WHERE  time > ? AND patientid =?");
            preparedStatement.setTimestamp(1, time);
            preparedStatement.setInt(2, patientid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TempDTO tempDTO = new TempDTO();
                tempDTO.setPatientid(resultSet.getInt("patientid"));
                tempDTO.setTemp(resultSet.getDouble("temp"));
                tempDTO.setTime(resultSet.getTimestamp("time"));
                data.add(tempDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}

