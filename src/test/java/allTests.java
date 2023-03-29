import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runnar öll testin
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ newBookingTest.class, searchHotelTest.class})

public class allTests {
}
