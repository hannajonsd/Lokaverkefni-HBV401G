package vidmot;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vinnsla.Hotel;



public class HotelController {

    public ObservableList<Hotel> hotels;


    public HotelController() {

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

    public void onVelja(ActionEvent actionEvent) {
        System.out.print("ýtt á velja");
    }
}
