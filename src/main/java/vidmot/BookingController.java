package vidmot;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import vinnsla.Booking;
import vinnsla.Room;

import java.util.Date;

import static vidmot.HotelView.roomProperty;

public class BookingController {
    public Booking bk;

    public Label fxRoom;
    public ObjectProperty<Room> room= new SimpleObjectProperty<>();
    public void initialize(){
        room.bind(roomProperty());
        if (room.get() != null) {
           fxRoom.setText(room.get().toString());
        }
        roomProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.print(newValue);
                fxRoom.setText(newValue.toString());
            }
        });
    }

    public void goHome() {
        ViewSwitcher.switchTo(View.HEIMASIDA);
    }

    public void goBack() {
        ViewSwitcher.switchTo(View.HOTEL);
    }
}
