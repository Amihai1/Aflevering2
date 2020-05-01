package EKG;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataSampleReaderSimImpl implements DataSampleReader{

    @Override
    public void save(PatientDTO patientDTO) {
        Connection conn = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO PatientData(idPatientData,Temp, SpO2, BPM, time) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1,patientDTO.getId());
            preparedStatement.setDouble(2,patientDTO.getTemp());
            preparedStatement.setDouble(3,patientDTO.getSpO2());
            preparedStatement.setDouble(4,patientDTO.getBPM());
            preparedStatement.setTimestamp(5,patientDTO.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<PatientDTO> loadData(Timestamp time) {
        List<PatientDTO> data = new ArrayList<>();
        Connection connection = MySQLConnector.getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM PatientData WHERE  time > ?");
            preparedStatement.setTimestamp(1,time);
            ResultSet resultSet =preparedStatement.executeQuery();
            while(resultSet.next()){
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setId(resultSet.getInt("idPatientData"));
                patientDTO.setTemp(resultSet.getDouble("Temp"));
                patientDTO.setSpO2(resultSet.getDouble("SpO2"));
                patientDTO.setBPM(resultSet.getDouble("BPM"));
                patientDTO.setTime(resultSet.getTimestamp("time"));
                data.add(patientDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


}