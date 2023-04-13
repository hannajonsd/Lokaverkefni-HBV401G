package vidmot;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import vinnsla.Hotel;
import vinnsla.Hotels;

public class HotelSidaView extends ListView<Hotel> {

    /**
     * Hotellistinn búinn til
     */
    public HotelSidaView(){
        Hotels h = new Hotels();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotelSida-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        setItems(h.getHotels());
    }
    public void setHotels(ObservableList<Hotel> hotels){
        setItems(hotels);
    }
}
