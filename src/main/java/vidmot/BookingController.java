package vidmot;

import vinnsla.Booking;

import java.util.Date;

public class BookingController {
    public Booking bk;

    public BookingController(Booking booking) {
        bk = booking;
    }

    public boolean createBooking(String hotel, String user, int roomnumber, Date start, Date end){
       return bk.createBooking(hotel, user, roomnumber, start, end);
    }
}
