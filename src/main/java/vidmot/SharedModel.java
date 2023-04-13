package vidmot;


import vinnsla.Hotel;
import vinnsla.Room;
import vinnsla.User;

public class SharedModel {

    private static SharedModel instance;

    private User currentUser;

    private Hotel selectedHotel;

    private Room selectedRoom;

    private SharedModel() {
    }

    public static SharedModel getInstance() {
        if (instance == null) {
            instance = new SharedModel();
        }
        return instance;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setSelectedHotel(Hotel selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }
    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }
}
