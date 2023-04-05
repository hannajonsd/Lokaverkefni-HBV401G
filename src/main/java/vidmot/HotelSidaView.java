package vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import vinnsla.Hotel;
import vinnsla.Hotels;

public class HotelSidaView extends ListView {
    /**
     * Local hlutur fyrir hotelið
     */
    private Hotels h;

    /**
     * Hotellistinn búinn til
     */
    public HotelSidaView(){
        h = new Hotels();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotelSida-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        setItems(h.getHotelNames());
    }
}
