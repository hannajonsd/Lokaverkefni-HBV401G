package vidmot;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import vinnsla.Hotel;
import vinnsla.Review;
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

    public void getReviews(ActionEvent actionEvent) {
        // Get the reviews from the hotel's observable list
        ObservableList<Review> reviews = hotel.get().getReviews();

        // Create a Dialog
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Reviews");
        dialog.setHeaderText("All Reviews");

        // Create a DialogPane
        DialogPane dialogPane = new DialogPane();

        // Create a ListView to display the reviews
        ListView<Review> listView = new ListView<>();
        listView.setItems(reviews);

        // Set a cell factory to customize the appearance of each item in the ListView
        listView.setCellFactory(param -> new ListCell<Review>() {
            @Override
            protected void updateItem(Review review, boolean empty) {
                super.updateItem(review, empty);
                if (empty || review == null) {
                    setText(null);
                } else {
                    setText("Comment: " + review.getComment() + "\nStars: " + review.getStars());
                }
            }
        });

        // Add the ListView to the DialogPane
        dialogPane.setContent(listView);

        // Set the DialogPane as the content of the Dialog
        dialog.setDialogPane(dialogPane);

        // Add a "Close" button to the Dialog
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // Show the Dialog
        dialog.showAndWait();
    }

}
