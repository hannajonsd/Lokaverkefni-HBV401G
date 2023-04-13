package vinnsla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private String roomNumber;
    private String type;
    private int price;
    private String size;

    public Room(String roomNumber, String type, int price, String size) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.size = size;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", type=" + type + ", price=" + price + ", size=" + size + "]";
    }

    public List<Booking> getBookings(Hotel hotel, User user) {
        List<Booking> bookings = new ArrayList<>();
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM booking WHERE room=? AND hotel=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, roomNumber);
            stmt.setString(2, hotel.getName());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                LocalDate start = rs.getDate("start").toLocalDate();
                LocalDate end = rs.getDate("end").toLocalDate();
                // Create Booking object and add to list

                Booking booking = new Booking(hotel, user, this, start, end);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}

