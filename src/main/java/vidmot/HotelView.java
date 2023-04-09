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
    public Label addOns;
    @FXML
    public ListView<Room> rooms;
    private ObjectProperty<Hotel> hotel = new SimpleObjectProperty<>();


    public void initialize(){
        hotel.bind(HotelController.hotelProperty());
        if(hotel.get()!= null) {
            hotelname.setText(hotel.get().getName());
            fxAbout.setText(hotel.get().getAbout());
            rooms.setItems(hotel.get().getRooms());
            setAddOns(hotel.get());
        }
        hotelProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.print(newValue);
                hotelname.setText(newValue.getName());
                fxAbout.setText(newValue.getAbout());
                rooms.setItems(newValue.getRooms());
                setAddOns(newValue);
            }
        });
    }

    private void setAddOns(Hotel hotel) {
        if(!hotel.isWifi()&& !hotel.isSpa() && !hotel.isAccess()&& !hotel.isResturant()){
            addOns.setText("Engin auka fríðindi");
        }
        else{
        String addOns = "Auka fríðindi: ";
        if(hotel.isResturant()){
            addOns+= " Það er veitigastaður.";
        }
        if(hotel.isWifi()){
            addOns+= " Frítt wifi.";
        }
        if(hotel.isSpa()){
            addOns+= " Það er spa.";
        }
        if(hotel.isAccess()){
            addOns+= " Það er aðgegni fyrir hreyfihamlaða.";
        }
        this.addOns.setText(addOns);
        }
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
