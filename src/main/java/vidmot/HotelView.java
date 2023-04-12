package vidmot;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
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

public class HotelView {
    @FXML
    public Label hotelname;
    @FXML
    public Label fxAbout;
    public Label addOns;
    @FXML
    public TableView<Room> rooms;
    @FXML
    private TableColumn<Room, Integer> roomNumberColumn;
    @FXML
    private TableColumn<Room, String> sizeColumn;
    @FXML
    private TableColumn<Room, Double> priceColumn;
    @FXML
    private TableColumn<Room, String> typeColumn;
    public Button fxBook;
    private final ObjectProperty<Hotel> hotel = new SimpleObjectProperty<>();
    public static ObjectProperty<Room> room= new SimpleObjectProperty<>();


    public void initialize(){
        fxBook.disableProperty().bind(Bindings.isEmpty(rooms.getSelectionModel().getSelectedItems()));
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
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
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
        ObservableList<Review> reviews = hotel.get().getReviews();

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
        room.set(selectedItem);
    }

    public static ObservableValue<Room> roomProperty() {
        return room;
    }
}
