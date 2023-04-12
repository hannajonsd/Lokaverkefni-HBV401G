package vidmot;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import vinnsla.Hotel;
import vinnsla.User;
import java.util.function.Predicate;


public class HotelController {
    @FXML
    public HotelSidaView hotels;
    private static final ObjectProperty<Hotel> hotel = new SimpleObjectProperty<>();
    @FXML
    public Button fxVelja;
    public User user;
    @FXML
    public Button fxLogin;
    @FXML
    public Label fxUser;
    @FXML
    public ChoiceBox<String> filterChoiceBox;
    @FXML
    public TextField searchHotelField;

    private FilteredList<Hotel> hotelList;


    public void initialize() {
        textDialog();
        fxVelja.disableProperty().bind(Bindings.isEmpty(hotels.getSelectionModel().getSelectedItems()));
        hotelList = new FilteredList<>(hotels.getItems());
    }

    @FXML
    private void filterHotels() {
        String selectedOption =  filterChoiceBox.getValue().toLowerCase();

        Predicate<Hotel> filterPredicate = hotel ->
                (hotel.isRestaurant() && "restaurant".contains(selectedOption))
                        || (hotel.isSpa() && "spa".contains(selectedOption))
                        || (hotel.isWifi() && "wifi".contains(selectedOption))
                        || (hotel.isAccess() && "access".contains(selectedOption));

        if("all".contains(selectedOption)){
            filterPredicate=null;
        }
        hotelList.setPredicate(filterPredicate);
        hotels.setHotels(hotelList);
    }



    public void setHotels(ObservableList<Hotel> hotels) {
        this.hotels = (HotelSidaView) hotels;

    }

    public Hotel getHotel() {
        return hotel.get();
    }

    public void setHotel(Hotel hotel) {
        HotelController.hotel.set(hotel);
    }

    // Define the property getter for the hotel property
    public static ObjectProperty<Hotel> hotelProperty() {
        return hotel;
    }

    public void onVelja(){
        if(user==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Innskráðu þig");
            alert.setHeaderText(null);
            alert.setContentText("Þarft að vera innsrkáður til að velja hótel");
            alert.showAndWait();
        }
        else {
            setHotel(hotels.getSelectionModel().getSelectedItem());
            ViewSwitcher.switchTo(View.HOTEL);
        }
    }

    public void login() {
        textDialog();
    }
    public void updateUserName() {
        fxUser.setText(user.getName());
    }

    public void textDialog() {
        LoginDialog ld = new LoginDialog();
        user = ld.getUser();
        if(user!=null){
            updateUserName();
        }
    }

    @FXML
    public void searchHotels() {
        String filterText = searchHotelField.getText().toLowerCase();
        Predicate<Hotel> filterPredicate = hotel ->
                (hotel.isRestaurant() || hotel.isSpa() || hotel.isAccess() || hotel.isWifi())
                        && hotel.getName().toLowerCase().contains(filterText);

        if (filterText.isEmpty()) {
            filterPredicate= null;
        }
        hotelList.setPredicate(filterPredicate);
        hotels.setHotels(hotelList);
    }
}

