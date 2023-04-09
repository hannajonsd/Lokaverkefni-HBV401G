package vidmot;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import vinnsla.Hotel;
import vinnsla.Room;

import java.io.IOException;

public class HotelView {
    @FXML
    public Label hotelname;
    @FXML
    public Label fxAbout;
    private  HotelController hotelController;
    @FXML
    public ListView<Room> rooms;
    private ObjectProperty<Hotel> hotel = new SimpleObjectProperty<>();

    public void setHotelController(HotelController hotelController) {
        this.hotelController = hotelController;
    }

    public void initialize(){
        hotel.bind(HotelController.hotelProperty());
        if(hotel.get()!= null) {
            hotelname.setText(hotel.get().getName());
            fxAbout.setText(hotel.get().getAbout());
            rooms.setItems(hotel.get().getRooms());
        }
        hotelProperty().addListener((observable, oldValue, newValue) -> {
            // Update the UI with the new values
            if (newValue != null) {
                System.out.print(newValue);
                hotelname.setText(newValue.getName());
                fxAbout.setText(newValue.getAbout());
                rooms.setItems(newValue.getRooms());
            }
        });
    }

    public Hotel getHotel() {
        return hotel.get();
    }

    public void setHotel(Hotel hotel) {
        this.hotel.set(hotel);
    }

    public String getHotelname() {
        return hotelname.getText();
    }

    public void setHotelname(String name) {
        hotelname.setText(name);
    }

    public String getFxAbout() {
        return fxAbout.getText();
    }

    public void setFxAbout(String about) {
        fxAbout.setText(about);
    }
    @FXML
    void goHome(){

        ViewSwitcher.switchTo(View.HEIMASIDA);
    }
    public ObjectProperty<Hotel> hotelProperty() {
        return hotel;
    }
}
