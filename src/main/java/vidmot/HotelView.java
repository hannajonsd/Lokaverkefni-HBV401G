package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import vinnsla.Hotel;

public class HotelView {

    public ListView fxrooms;
    @FXML
    public Label hotelname;
    @FXML
    public Label fxAbout;

    public void initialize(){
        Hotel hotel= HotelController.hotel;
        hotelname.setText(hotel.getName());
        fxAbout.setText(hotel.getAbout());
    }

    public ListView getFxrooms() {
        return fxrooms;
    }

    public void setFxrooms(ListView fxrooms) {
        this.fxrooms = fxrooms;
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

    public void goHome(){

        ViewSwitcher.switchTo(View.HEIMASIDA);
    }
}
