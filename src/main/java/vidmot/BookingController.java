package vidmot;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import vinnsla.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingController {
    public Booking bk;
    @FXML
    public Label fxRoom;
    public Room room;
    @FXML
    public Label fxBookingMessage;
    @FXML
    private DatePicker fxEnd;
    @FXML
    private DatePicker fxStart;
    public Hotel hotel;
    private User user;
    public Label fxUser;

    SharedModel model = SharedModel.getInstance();

    public void initialize(){
        user = model.getCurrentUser();
        if(user!=null) {
            updateUserName();
        }
        hotel = model.getSelectedHotel();
        room = model.getSelectedRoom();
        if (room != null) {
           fxRoom.setText(room.toString());
        }
        disableUnavailableDates();
    }
    private void disableUnavailableDates() {
        // Retrieve booking data from database
        List<Booking> bookings = getBookingsFromDatabase();

        // Callback to customize DatePicker cells
        Callback<DatePicker, DateCell> dayCellFactory = datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disable dates in the past
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #dddddd;"); // Set background color to grey
                }


                // Disable dates within booking start and end dates
                for (Booking booking : bookings) {
                    System.out.println(booking.getStart().toString());
                    System.out.println(booking.getEnd().toString());
                    if (date.isAfter(booking.getStart()) && date.isBefore(booking.getEnd())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #dddddd;"); // Set background color to grey
                    }
                }
            }
        };

        // Set day cell factory for start and end date pickers
        fxStart.setDayCellFactory(dayCellFactory);
        fxEnd.setDayCellFactory(dayCellFactory);
    }

    private List<Booking> getBookingsFromDatabase() {
        List<Booking> bookings;
        bookings = room.getBookings(hotel, user);
        return bookings;
    }

    public void updateUserName() {
        fxUser.setText(user.getName());
    }

    public void goHome() {
        ViewSwitcher.switchTo(View.HEIMASIDA);
    }

    public void goBack() {
        ViewSwitcher.switchTo(View.HOTEL);
    }

    public void book() {
        LocalDate start = fxStart.getValue();
        LocalDate end = fxEnd.getValue();
        Booking booking = new Booking(hotel, user, room, start,end);
        if(start.isBefore(LocalDate.now())){
            fxBookingMessage.setText("Ekki hægt að velja dag fyrir daginn í dag");
        }
        else if(start.isAfter(end)){
            fxBookingMessage.setText("Byrjunar dagur er á undan lokadegi");
        }
        else if(!booking.checkAvailability()){
            fxBookingMessage.setText("Herbergi bókað á þessum tíma");
        }
        else{
            booking.addBooking();
            fxBookingMessage.setText("Bókun staðfest");
        }
    }
}
