package vinnsla;

import java.sql.*;

public class GetDatabaseConn {
    public Connection databaseLink;
    public Connection getDBconnection() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/sql/GG_2.db";

        try {
            if(databaseLink == null){
                Class.forName("org.sqlite.JDBC");
                databaseLink = DriverManager.getConnection(url);
            }

        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return databaseLink;
    }
}
