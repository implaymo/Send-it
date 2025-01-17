/*package send.it.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Connected with database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}*/
