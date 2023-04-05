package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;


public class Hotel {
    public String name;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String about;

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
    public Hotel(String name, String about){ //listi af hotelum
        setAbout(about);
        setName(name);

    }
}
