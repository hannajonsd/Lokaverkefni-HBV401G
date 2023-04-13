package vinnsla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Booking {
    public Hotel hotel;
    public User user;
    public Room room;
    public LocalDate start;
    public LocalDate end;

    public Booking() {

    }

    public Hotel getHotel() {
        return hotel;
    }

    public Booking(Hotel hotel, User user, Room room, LocalDate start, LocalDate end) {
        this.hotel = hotel;
        this.user = user;
        this.room = room;
        this.start = start;
        this.end = end;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void createBooking(Hotel hotel, User user, Room room, LocalDate start, LocalDate end) {
        if (hotel == null || user == null || start == null || end == null) {
            return;
        }
        setHotel(hotel);
        setUser(user);
        setRoom(room);
        setStart(start);
        setEnd(end);
    }
    public void addBooking() {
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "INSERT INTO booking (hotel, user, room, start, end) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, room.getRoomNumber());
            pstmt.setDate(4, java.sql.Date.valueOf(start));
            pstmt.setDate(5, java.sql.Date.valueOf(end));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkAvailability(){
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM booking WHERE hotel = ? AND room = ? AND end >= ? AND start <= ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, hotel.getName());
            stmt.setString(2, room.getRoomNumber());
            stmt.setDate(3, java.sql.Date.valueOf(start)); // Convert LocalDate to java.sql.Date for comparison
            stmt.setDate(4, java.sql.Date.valueOf(end)); // Convert LocalDate to java.sql.Date for comparison
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
