package vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import vinnsla.Hotel;

public class HotelSidaView extends ListView {
    /**
     * Local hlutur fyrir hotelið
     */
    private Hotel h;

    /**
     * Hotellistinn búinn til
     */
    public HotelSidaView(){
        h = new Hotel();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotelSida-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        setItems(h.getHotelNames());
    }
}
