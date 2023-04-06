package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Hotels {
    protected ObservableList<Hotel> hotels = FXCollections.observableArrayList();

    public Hotels(){
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM hotel";
        try {
            System.out.println("Connection to SQLite has been established.");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // loop through the result set
            while (rs.next()) {
                Hotel h = new Hotel(rs.getString("name"), rs.getString("about"));
                hotels.add(h);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ObservableList<String> getHotelNames(){
        ObservableList<String> hotelnames = FXCollections.observableArrayList();
        for (Hotel hotel : hotels) {
            hotelnames.add(hotel.getName());
        }
        return hotelnames;
    }
    public ObservableList<Hotel> getHotels(){
        return hotels;
    }
}
