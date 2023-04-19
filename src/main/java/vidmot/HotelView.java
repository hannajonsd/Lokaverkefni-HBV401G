package vidmot;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vinnsla.Hotel;
import vinnsla.Review;
import vinnsla.Room;
import vinnsla.User;

import java.text.NumberFormat;
import java.util.Locale;

public class HotelView {
    @FXML
    public Label hotelname;
    @FXML
    public Label fxAbout;
    public Label addOns;
    @FXML
    public TableView<Room> rooms;
    @FXML
    public Label fxUser;
    @FXML
    public Label fxwebpage;
    @FXML
    public Label fxDistance;
    @FXML
    private TableColumn<Room, Integer> roomNumberColumn;
    @FXML
    private TableColumn<Room, String> sizeColumn;
    @FXML
    private TableColumn<Room, String> priceColumn;
    @FXML
    private TableColumn<Room, String> typeColumn;
    public Button fxBook;
    private Hotel hotel;
    private User user;

    SharedModel model = SharedModel.getInstance();


    public void initialize(){
        user = model.getCurrentUser();
        if(user!=null) {
            updateUserName();
        }
        fxBook.disableProperty().bind(Bindings.isEmpty(rooms.getSelectionModel().getSelectedItems()));
        setHotel(model.getSelectedHotel());
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        priceColumn.setCellValueFactory(cellData -> {
            Integer price = cellData.getValue().getPrice();
            String priceString;
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            priceString = numberFormat.format(price) + " kr.";
            return new ReadOnlyStringWrapper(priceString);
        });
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    public void updateUserName() {
        fxUser.setText(user.getName());
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
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel= hotel;
        if(hotel!= null) {
            hotelname.setText(hotel.getName());
            fxAbout.setText(hotel.getAbout());
            rooms.setItems(hotel.getRooms());
            setAddOns(hotel);
            fxwebpage.setText("Vefslóð: " + hotel.getWebpage());
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            fxDistance.setText("Vegalengd í miðbæ: "+ numberFormat.format(hotel.getDistance())+ "km");
        }
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

    public void getReviews(ActionEvent actionEvent) {
        ObservableList<Review> reviews = hotel.getReviews();

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Umsagnir");
        dialog.setHeaderText("Allar Umsagnir");

        DialogPane dialogPane = new DialogPane();

        ListView<Review> listView = new ListView<>();
        listView.setItems(reviews);

        listView.setCellFactory(param -> new ListCell<Review>() {
            @Override
            protected void updateItem(Review review, boolean empty) {
                super.updateItem(review, empty);
                if (empty || review == null) {
                    setText(null);
                } else {
                    setText("Umsögn: " + review.getComment() + "\nStjörnur: " + review.getStars());
                    setWrapText(true);
                }
            }
        });
        listView.setPrefWidth(400);
        dialogPane.setContent(listView);
        dialog.setDialogPane(dialogPane);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    public void book() {
        setRoom( rooms.getSelectionModel().getSelectedItem());
        ViewSwitcher.switchTo(View.BOKUN);
    }

    private void setRoom(Room selectedItem) {
        model.setSelectedRoom(selectedItem);
    }
}
