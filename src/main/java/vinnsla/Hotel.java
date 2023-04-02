package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hotel {
    public String name;

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
    protected ObservableList<Hotel> hotels = FXCollections.observableArrayList();


    /**
     * Veitingarhlutir settir í matseðilslistann
     */
    public Hotel(){ //listi af hotelum
        hotels.add(new Hotel("Hotel Kopavogur"));
        hotels.add(new Hotel("Hotel Hlidar"));
        hotels.add(new Hotel("Hotel Sudurland"));
        hotels.add(new Hotel("Hotel Akureyri"));
        hotels.add(new Hotel("Hotel Snaefellsnes"));
        hotels.add(new Hotel( "Hotel Hofn"));
        hotels.add(new Hotel( "Adventure Hotel"));
        hotels.add(new Hotel( "Ice Apartments"));
        hotels.add(new Hotel( "Diamond Hotel"));
    }

    /**
     *
     * @return skilar lista af veitingum, matseðlinum
     */
    public ObservableList<Hotel> getHotel(){return hotels;}

}
