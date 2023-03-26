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
    private HotelService hotelService;
    private ReservationService reservationService;
    private BookingController bookingController;

    @Before
    public void setUp() {
        // Create mock objects for hotelService and reservationService
        hotelService = mock(HotelService.class);
        reservationService = mock(ReservationService.class);

        // Create a BookingController with the mock objects
        bookingController = new BookingController(hotelService, reservationService);
    }

    @Test
    public void testBook_SuccessfulBooking() {
        // Create a BookingRequest object with valid hotel ID
        Booking booking = new Booking("hotel", "John Smith",1234, new Date(2023, Calendar.MAY, 1), new Date(2023, Calendar.MAY, 5));

        // Set up the mock hotelService to return the hotel
        when(hotelService.getHotelById("1234")).thenReturn(hotel);

        // Create a Reservation object
        Reservation reservation = new Reservation("5678", bookingRequest.getGuestName(), bookingRequest.getStartDate(), bookingRequest.getEndDate(), hotel);

        // Set up the mock reservationService to return the reservation
        when(reservationService.makeReservation(bookingRequest, hotel)).thenReturn(reservation);

        // Call the book() method and assert that it returns a non-null Booking object
        assertNotNull(bookingController.book(bookingRequest));
    }

    @Test
    public void testBook_HotelNotFound() {
        // Create a BookingRequest object with invalid hotel ID
        Booking booking = new Booking("5678", "Jane Doe", LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 5));

        // Set up the mock hotelService to return null
        when(hotelService.getHotelById("5678")).thenReturn(null);

        // Call the book() method and assert that it returns null
        assertNull(bookingController.book(bookingRequest));
    }






        @AfterClass
    public static void tearDownClass() {
        // Clean up resources used by all tests
    }
}
