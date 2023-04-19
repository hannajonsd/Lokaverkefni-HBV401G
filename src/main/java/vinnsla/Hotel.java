package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;


public class Hotel {
    public String name;
    public String about;
    public ObservableList<Room> rooms;
    public boolean spa;
    public boolean wifi;
    public boolean restaurant;
    public ObservableList<Review> reviews;
    public String webpage;
    public int distance;

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isResturant() {
        return restaurant;
    }

    public void setResturant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public boolean access;
    public Hotel(String name, String about, ObservableList<Room> rooms, boolean spa, boolean wifi, boolean restaurant, boolean access, ObservableList<Review> reviews, String webpage, int distance) {
        this.name = name;
        this.about = about;
        this.rooms = rooms;
        this.spa = spa;
        this.wifi = wifi;
        this.restaurant = restaurant;
        this.access = access;
        this.reviews= reviews;
        this.distance= distance;
        this.webpage= webpage;

    }



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

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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
    @Override
    public String toString() {
        return name;
    }

    public ObservableList<Review> getReviews() {
        return reviews;
    }

    public boolean isRestaurant() {
        return restaurant;
    }
}
