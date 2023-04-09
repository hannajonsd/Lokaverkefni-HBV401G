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
                ObservableList<Room> rooms = getRoomsForHotel(rs.getString("name"));
                Hotel h = new Hotel(rs.getString("name"), rs.getString("about"), rooms);
                hotels.add(h);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ObservableList<Room> getRoomsForHotel(String hotelName) {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM room WHERE hotel = '" + hotelName + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // loop through the result set
            while (rs.next()) {
                String roomNumber = rs.getString("roomnumber");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                String size = rs.getString("size");
                Room room = new Room(roomNumber, type, price, size);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
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
