import org.junit.*;
import vidmot.BookingController;
import vinnsla.Booking;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class newBookingTest {

    private Booking mockBooking; //Tilviksbreyta fyrir mockBooking af gerð Booking
    private BookingController controller; //Tilviksbreyta fyrir controllerinn

    /**
     * Setjum mockBooking sem mock af klasanum Booking,
     * gerum nýjan controller með mock booking
     */
    @Before
    public void setUp() {
        mockBooking = mock(Booking.class);
        controller = new BookingController(mockBooking);
    }

    /**
     * Göngum úr skugga að það sé búin til bókun, skilar true ef bókunin er búin til
     */
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


    /**
     * Setjum null til að testa hvort þetta skili false ef bókun verður ekki til
     */
    @Test
    public void testCreateBooking2() {
        String user = "John";
        int roomnumber = 101;
        Date start = new Date();
        Date end = new Date();
        boolean b = controller.createBooking(null, user, roomnumber, start, end);

        assertFalse(b);
    }

    /**
     * Hreinsar bókunina
     */
    @After
    public void tearDown() {
        reset(mockBooking);
    }
}
