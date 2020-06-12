package DAOMySQLImpl;

import DAOInterfaces.TempDAO;
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
    public List<TempDTO> loadData(Timestamp time) {
        List<TempDTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM tempdata WHERE  time > ?");
            preparedStatement.setTimestamp(1, time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TempDTO tempDTO = new TempDTO();
                tempDTO.setTemp(resultSet.getDouble("Temp"));
                data.add(tempDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}

