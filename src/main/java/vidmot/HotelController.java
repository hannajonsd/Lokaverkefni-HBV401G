package vidmot;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import vinnsla.GetDatabaseConn;
import vinnsla.Hotel;
import vinnsla.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


public class HotelController {
    @FXML
    public HotelSidaView hotels;
    private static ObjectProperty<Hotel> hotel = new SimpleObjectProperty<>();
    public HotelView hotelView;
    @FXML
    public Button fxVelja;
    public User user;
    @FXML
    public Button fxRegister;
    @FXML
    public Button fxLogin;
    @FXML
    public Label fxUser;

    public void initialize() {
        textDialog();
        fxVelja.disableProperty().bind(Bindings.isEmpty(hotels.getSelectionModel().getSelectedItems()));
    }


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

        setHotel(hotels.getSelectionModel().getSelectedItem());
        ViewSwitcher.switchTo(View.HOTEL);
    }

    public void login() {
        textDialog();
    }
    public void updateUserName() {
        fxUser.setText(user.getNafn());
    }

    public void textDialog() {
        LoginDialog ld = new LoginDialog();
        user = ld.getUser();
        if(user!=null){
            updateUserName();
        }
    }
}

