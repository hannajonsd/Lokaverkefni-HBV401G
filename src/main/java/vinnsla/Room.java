package vinnsla;
public class Room {

    private String roomNumber;
    private String type;
    private double price;
    private String size;

    public Room(String roomNumber, String type, double price, String size) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.size = size;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", type=" + type + ", price=" + price + ", size=" + size + "]";
    }
}

