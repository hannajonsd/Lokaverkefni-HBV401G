package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private StringProperty nafn = new SimpleStringProperty();
    public String getNafn() {
        return nafn.get();
    }
    public StringProperty nafnProperty() {
        return nafn;
    }

    public void setNafn(String nafn) {
        this.nafn.set(nafn);
    }

    /**
     * Smiður fyrir viðskiptavin
     * @param nafn viðskiptavins
     * @param email heimilisfang viðskiptavins
     */
    public User(StringProperty nafn, StringProperty email, IntegerProperty kennitala) {
        this.nafn = nafn;
        this.email = email;
        this.kennitala = kennitala;
    }

    private StringProperty email = new SimpleStringProperty();

    public String getEmail() {
        return email.get();
    }
    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    private IntegerProperty kennitala = new SimpleIntegerProperty();

    public int getKennitala() {
        return kennitala.get();
    }

    public IntegerProperty kennitalaProperty() {
        return kennitala;
    }

    public void setKennitala(int kennitala) {
        this.kennitala.set(kennitala);
    }
}
