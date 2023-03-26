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
    public void testCreateBooking1() {
        String hotel = "Hilton";
        String user = "John";
        int roomnumber = 101;
        Date start = new Date(2023, Calendar.MAY, 1);
        Date end = new Date(2023, Calendar.MAY, 6);
        controller.createBooking(hotel, user, roomnumber, start, end);

        verify(mockBooking).createBooking(hotel, user, roomnumber, start, end);
    }

    @Test
    public void testCreateBooking2() {
        String user = "John";
        int roomnumber = 101;
        Date start = new Date();
        Date end = new Date();
        boolean b = controller.createBooking(null, user, roomnumber, start, end);

        assertFalse(b);
    }




    @After
    public void tearDown() {
        reset(mockBooking);
    }
}
