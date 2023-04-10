package vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;

public class User {
    private StringProperty nafn;
    private StringProperty email;
    private StringProperty password;
    private StringProperty kennitala;


    /**
     * Smiður fyrir viðskiptavin
     * @param nafn viðskiptavins
     * @param email heimilisfang viðskiptavins
     * @param kennitala
     */
    public User(StringProperty nafn, StringProperty email, StringProperty kennitala, StringProperty password) {
        this.nafn = nafn;
        this.email = email;
        this.kennitala = kennitala;
        this.password = password;
    }

    public void addUserDb(){
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "INSERT INTO user (name, ssn, email, password) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nafn.get());
            pstmt.setString(2, kennitala.get());
            pstmt.setString(3, email.get());
            pstmt.setString(4, password.get());
            pstmt.executeUpdate();
            System.out.println("user inserted into table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNafn() {
        return nafn.get();
    }
    public StringProperty nafnProperty() {
        return nafn;
    }

    public void setNafn(String nafn) {
        this.nafn.set(nafn);
    }

    public String getEmail() {
        return email.get();
    }
    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }



    public String getKennitala() {
        return kennitala.get();
    }

    public StringProperty kennitalaProperty() {
        return kennitala;
    }

    public void setKennitala(int kennitala) {
        this.kennitala.set(String.valueOf(kennitala));
    }

    public boolean isNameUnique() {
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM user WHERE name=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nafn.get());
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
