package EKG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static Connection conn;
    public static Connection getConn(){
        try {
            if (conn==null || conn.isClosed()){
                conn = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk:3306/s172501?" +
                        "user=s172501&password=KZLb24VANQ5UrG1agJGJS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}

