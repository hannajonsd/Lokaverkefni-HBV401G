package vinnsla;

import java.util.Date;

public class Booking {
    public String hotel;
    public String user;
    public int roomnumber;
    public Date start;
    public Date end;

    public Booking() {
        hotel="";
        user="";
        roomnumber=0;
        start=null;
        end=null;
    }

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
}
