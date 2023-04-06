package vidmot;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vinnsla.GetDatabaseConn;
import vinnsla.Hotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HotelController {
    @FXML
    public HotelSidaView hotels;
    public static Hotel hotel;
    public HotelView hotelView;


    public HotelController() {

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
    public void setHotel(Hotel hotel){
       this.hotel = hotel;
    }
    public Hotel getHotel(){
        return hotel;
    }

    public void onVelja(ActionEvent actionEvent) {
        String hotelname= (String) hotels.getSelectionModel().getSelectedItem();
        setHotelDb(hotelname);
        ViewSwitcher.switchTo(View.HOTEL);
    }

    public void setHotelDb(String name){
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM hotel WHERE name ='" +  name+ "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // loop through the result set
            while (rs.next()) {
                Hotel h = new Hotel(rs.getString("name"), rs.getString("about"));
                setHotel(h);
            }
            System.out.print(hotel.toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}
