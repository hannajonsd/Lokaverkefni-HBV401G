package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;


public class Hotel {
    public String name;
    public String about;
    public ObservableList<Room> rooms;

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ObservableList<Room> rooms) {
        this.rooms = rooms;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }



    public Hotel(String name){
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    /**
     * Observable listi af veitingarhlutum sem fara á matseðilinn
     */



    /**
     * Veitingarhlutir settir í matseðilslistann
     */
    public Hotel(String name, String about, ObservableList<Room> rooms){ //listi af hotelum
        setAbout(about);
        setName(name);
        setRooms(rooms);
    }
    @Override
    public String toString() {
        return name;
    }
}
