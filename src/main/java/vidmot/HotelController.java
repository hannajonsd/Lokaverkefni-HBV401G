package vidmot;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import vinnsla.GetDatabaseConn;
import vinnsla.Hotel;
import vinnsla.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


public class HotelController {
    @FXML
    public HotelSidaView hotels;
    private static ObjectProperty<Hotel> hotel = new SimpleObjectProperty<>();
    public HotelView hotelView;
    public Button fxVelja;
    public User user;
    public Button fxRegister;
    public Button fxLogin;

    public void initialize() {
        UserDialog ud = new UserDialog();
        user = ud.getUser();
        fxVelja.disableProperty().bind(Bindings.isEmpty(hotels.getSelectionModel().getSelectedItems()));
    }


    public boolean searchHotels(String hotelName) {
        /*
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return true;
            }
        }

         */
        return false;
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

    public void onVelja(ActionEvent actionEvent) throws IOException {

        setHotel(hotels.getSelectionModel().getSelectedItem());
        ViewSwitcher.switchTo(View.HOTEL);
    }

    public void register() {
        UserDialog ud = new UserDialog();
        user = ud.getUser();
    }

    public void login() {
        textDialog();
    }

    public void textDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Innskráning");
        dialog.setHeaderText("Skráðu inn nafn og lykilorð");

        ButtonType loginButtonType = new ButtonType("Innskrá", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        grid.add(new Label("Nafn:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Lykilorð:"), 0, 1);
        grid.add(passwordField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(usernameField::requestFocus);


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(usernameField.getText(), passwordField.getText());
            }
            return null;
        });


        AtomicBoolean closeDialog = new AtomicBoolean(false); // Flag to determine if dialog should be closed

        while (!closeDialog.get()) { // Loop to keep the dialog open if needed
            Optional<Pair<String, String>> result = dialog.showAndWait();

            result.ifPresent(values -> {
                String username = values.getKey();
                String password = values.getValue();
                if (!isUsernameInDb(username)) {
                    dialog.setHeaderText("Notendanafn ekki til");
                } else if (!doPasswordMatch(password, username)) {
                    dialog.setHeaderText("Vitlaust lykilorð");
                } else {
                    user = getUserByName(username);
                    System.out.println(user);
                    closeDialog.set(true); // Set flag to close dialog
                }
            });

            // If result is not present, dialog was cancelled, set flag to close dialog
            if (result.isEmpty()) {
                closeDialog.set(true);
            }
        }
    }
    public boolean isUsernameInDb(String username) {
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM user WHERE name=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doPasswordMatch(String password, String username) {
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM user WHERE name=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            String passwordDB = rs.getString("password");
            return password.equals(passwordDB);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByName(String username) {
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM user WHERE name=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            StringProperty name = new SimpleStringProperty(username);
            StringProperty password = new SimpleStringProperty(rs.getString("password"));
            StringProperty email = new SimpleStringProperty(rs.getString("email"));
            StringProperty ssn = new SimpleStringProperty(rs.getString("ssn"));
            return new User(name, email, ssn, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

