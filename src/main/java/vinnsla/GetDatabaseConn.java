package vinnsla;

import java.sql.*;

public class GetDatabaseConn {
    public Connection databaseLink;
    public Connection getDBconnection() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/sql/GG_5.db";

        try {
            Class.forName("org.sqlite.JDBC");
            databaseLink= DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
