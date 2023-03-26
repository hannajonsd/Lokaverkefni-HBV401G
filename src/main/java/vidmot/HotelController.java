package vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vinnsla.Booking;
import vinnsla.Hotel;

import java.util.Iterator;

public class HotelController {
    @FXML
    private Label welcomeText;

    public ObservableList<Hotel> hotels;


    public HotelController() {

    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Velkomin á heimasíðu Hótel Landsins");
    }

    public boolean searchHotels(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return true;
            }
        }

        return false;
    }

    public void setHotels(ObservableList<Hotel> hotels) {
        this.hotels = hotels;
    }
}
