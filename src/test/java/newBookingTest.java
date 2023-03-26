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
    private Booking bk;
    private BookingController bookingController;

    public String hotel = "Test Hotel";
    public String user = "Test User";
    public int roomnumber = 123;
    public Date start = new Date();
    public Date end = new Date();

    @Before
    public void setUp() {
        bk = new Booking();
        bookingController = new BookingController(bk);
        bookingController.createBooking(hotel, user, roomnumber, start, end);

    }

    @Test
    public void testCreateBooking() {

        assertEquals(hotel, bk.getHotel());
        assertEquals(user, bk.getUser());
        assertEquals(roomnumber, bk.getRoomNumber());
        assertEquals(start, bk.getStartDate());
        assertEquals(end, bk.getEndDate());
    }

    @AfterClass
    public static void tearDownClass() {
        // Clean up resources used by all tests
    }
}
