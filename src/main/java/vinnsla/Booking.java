package vinnsla;

import java.util.Date;

public class Booking {
    public String hotel;
    public String user;
    public int roomnumber;
    public Date start;
    public Date end;

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getUser() {
        return user;
    }

    public int getRoomNumber() {
        return roomnumber;
    }

    public Date getStartDate() {
        return start;
    }

    public Date getEndDate() {
        return end;
    }

    public Booking() {
        this.hotel ="";
        this.user = "";
        this.roomnumber = 0;
        this.start = null;
        this.end = null;
    }

    public boolean createBooking(String hotel, String user, int roomnumber, Date start, Date end) {
        if (hotel == null || user == null || start == null || end == null) {
            return false;
        }
        setHotel(hotel);
        setUser(user);
        setRoomnumber(roomnumber);
        setStart(start);
        setEnd(end);
        return true;
    }
}
