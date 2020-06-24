/** @author Amihai */
package DAOMySQLImpl;

import DAOInterfaces.EKGDAO;
import DTO.EKGDTO;
import Connectors.MySQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EKGDAOMySQLImpl implements EKGDAO {


    @Override
    public void batchsave(List<EKGDTO> batch) {
        try {
            Connection conn = MySQLConnector.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ekgdata1 (patientid,ekg,time) VALUES (?,?,?)");
            for (EKGDTO ekgdto : batch) {
                preparedStatement.setInt(1, ekgdto.getPatientid());
                preparedStatement.setInt(2, ekgdto.getEkg());
                preparedStatement.setTimestamp(3, ekgdto.getTime());
                preparedStatement.addBatch();

            }
            preparedStatement.executeBatch();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<EKGDTO> loadData(Timestamp time, int patientid) {
        List<EKGDTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM ekgdata1 WHERE  time > ? AND patientid = ?");
            preparedStatement.setTimestamp(1, time);
            preparedStatement.setInt(2, patientid);
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
