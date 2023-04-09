package vidmot;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vinnsla.GetDatabaseConn;
import vinnsla.Hotel;

import java.io.IOException;


public class HotelController {
    @FXML
    public HotelSidaView hotels;
    private static ObjectProperty<Hotel> hotel = new SimpleObjectProperty<>();
    public HotelView hotelView;


    public boolean searchHotels(String hotelName) {
        /*
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return true;
            }
        }

         */
        return false;
    }

    public void setHotels(ObservableList<Hotel> hotels) {
        this.hotels = (HotelSidaView) hotels;

    }
    public Hotel getHotel() {
        return hotel.get();
    }

    public void setHotel(Hotel hotel) {
        HotelController.hotel.set(hotel);
    }

    // Define the property getter for the hotel property
    public static ObjectProperty<Hotel> hotelProperty() {
        return hotel;
    }

    public void onVelja(ActionEvent actionEvent) throws IOException {

        setHotel( hotels.getSelectionModel().getSelectedItem());
        ViewSwitcher.switchTo(View.HOTEL);
    }
}

