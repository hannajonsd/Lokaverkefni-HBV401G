import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.*;
import vidmot.HotelController;
import vinnsla.Hotel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class searchHotelTest {
    private static ObservableList<Hotel> hotels;
    private HotelController controller;


    @BeforeClass
    public static void setUpClass() {
        hotels = FXCollections.observableArrayList();
        hotels.add(new Hotel("Hotel A"));
        hotels.add(new Hotel("Hotel B"));
    }

    @AfterClass
    public static void tearDownClass() {
        hotels = null;
    }

    @Before
    public void setUp() {
        controller = new HotelController();
        controller.setHotels(hotels);
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testSearchHotels() {
        boolean result = controller.searchHotels("Hotel A");
        assertTrue(result);

        result = controller.searchHotels("Hotel C");
        assertFalse(result);
    }

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
