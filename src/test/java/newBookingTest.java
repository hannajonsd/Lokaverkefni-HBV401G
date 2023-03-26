import org.junit.*;
import vidmot.BookingController;
import vinnsla.Booking;
import vinnsla.Hotel;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class newBookingTest {

    private Booking mockBooking;
    private BookingController controller;

    @Before
    public void setUp() {
        mockBooking = mock(Booking.class);
        controller = new BookingController(mockBooking);
    }

    @Test
    public void testCreateBooking() {
        String hotel = "Hilton";
        String user = "John";
        int roomnumber = 101;
        Date start = new Date();
        Date end = new Date();
        controller.createBooking(hotel, user, roomnumber, start, end);

        verify(mockBooking).createBooking(hotel, user, roomnumber, start, end);
    }

    @After
    public void tearDown() {
        reset(mockBooking);
    }
}
