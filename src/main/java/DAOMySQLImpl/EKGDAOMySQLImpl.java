package DAOMySQLImpl;

import DAOInterfaces.EKGDAO;
import DTO.BPMDTO;
import DTO.EKGDTO;
import EKG.MySQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EKGDAOMySQLImpl implements EKGDAO {
    @Override
    public void save(EKGDTO ekgDTO) {
        Connection conn = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ekgdata (patientid,ekg,time) VALUES (?,?,?)");
            preparedStatement.setInt(1, ekgDTO.getPatientid());
            preparedStatement.setInt(2, ekgDTO.getEkg());
            preparedStatement.setTimestamp(3, ekgDTO.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void batchsave(List<EKGDTO> batch) {

    }

    @Override
    public List<EKGDTO> loadData(Timestamp time) {
        List<EKGDTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM ekgdata WHERE  time > ?");
            preparedStatement.setTimestamp(1, time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EKGDTO ekgDTO = new EKGDTO();
                ekgDTO.setEkg(resultSet.getInt("ekg"));
                data.add(ekgDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
