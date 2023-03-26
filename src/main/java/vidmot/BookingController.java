package vidmot;

import vinnsla.Booking;
import vinnsla.Hotel;

import java.util.Date;

public class BookingController {
    public Booking bk;

    public void createBooking(String hotel, String user, int roomnumber, Date start, Date end){
        bk = new Booking(hotel, user, roomnumber, start, end);

    }
}
