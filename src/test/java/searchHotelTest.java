import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.*;
import vidmot.HotelController;
import vinnsla.Hotel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class searchHotelTest {
    private static ObservableList<Hotel> hotels; //ObservableList af gerð Hotel
    private HotelController controller; //Tilviksbreyta fyrir hotelcontroller

    /**
     * Setjum að hótelin séu arrayList, þannig að það þarf bara að setja inn nafn
     */
    @BeforeClass
    public static void setUpClass() {
        hotels = FXCollections.observableArrayList();
        hotels.add(new Hotel("Hotel A"));
        hotels.add(new Hotel("Hotel B"));
    }

    /**
     * Hent úr öllu fylkinu svo við séum ekki með einhver gildi eftir
     */
    @AfterClass
    public static void tearDownClass() {
        hotels = null;
    }

    /**
     * Skilgreinum controllerinn og setHotels
     */
    @Before
    public void setUp() {
        controller = new HotelController();
        controller.setHotels(hotels);
    }

    /**
     * Hendum út
     */
    @After
    public void tearDown() {
        controller = null;
    }

    /**
     * Ekkert mock, skiljum það ekki nógu vel, svo venjulegt til öryggis
     * Leitum að hóteli sem við vitum að er til og skilar true ef við finnum hótel
     * Leitum að hóteli sem við vitum að er ekki til.
     */
    @Test
    public void testSearchHotels() {
        boolean result = controller.searchHotels("Hotel A");
        assertTrue(result);

        result = controller.searchHotels("Hotel C");
        assertFalse(result);
    }

    /**
     * Gerum mock af hótel klasanum, setjum svo í mockHotel listann okkar og skoðum ef við finnum hótelið
     */
    @Test
    public void testSearchHotelsWithMock() {
        Hotel mockHotel = mock(Hotel.class);
        when(mockHotel.getName()).thenReturn("Hotel A");

        hotels.add(mockHotel);

        boolean result = controller.searchHotels("Hotel A");
        assertTrue(result);

        result = controller.searchHotels("Hotel C");
        assertFalse(result);
    }
}
