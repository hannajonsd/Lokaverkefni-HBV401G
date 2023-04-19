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
                boolean spa = rs.getBoolean("spa");
                boolean restaurant = rs.getBoolean("restaurant");
                boolean wifi = rs.getBoolean("wifi");
                boolean access = rs.getBoolean("access");
                ObservableList<Review> reviews = getReviewsForHotel(rs.getString("name"));
                int distance = rs.getInt("distance");
                String webpage = rs.getString("webPage");
                Hotel h = new Hotel(rs.getString("name"), rs.getString("about"), rooms, spa, wifi,  restaurant, access, reviews, webpage, distance);
                hotels.add(h);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private ObservableList<Review> getReviewsForHotel(String hotelName) {
        ObservableList<Review> reviews = FXCollections.observableArrayList();
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM review WHERE hotel = '" + hotelName + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // loop through the result set
            while (rs.next()) {
                String comment = rs.getString("comments");
                int stars = rs.getInt("stars");
                Review review = new Review(comment, stars);
                reviews.add(review);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reviews;
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
                int price = rs.getInt("price");
                String size = rs.getString("size");
                Room room = new Room(roomNumber, type, price, size);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
