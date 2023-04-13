package vinnsla;

import javafx.beans.property.StringProperty;

import java.sql.*;

public class User {
    private StringProperty name;
    private StringProperty email;
    private StringProperty password;
    private StringProperty ssn;


    /**
     * Smiður fyrir viðskiptavin
     * @param name viðskiptavins
     * @param email heimilisfang viðskiptavins
     * @param ssn
     */
    public User(StringProperty name, StringProperty email, StringProperty ssn, StringProperty password) {
        this.name = name;
        this.email = email;
        this.ssn = ssn;
        this.password = password;
    }

    public void addUserDb(){
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "INSERT INTO user (name, ssn, email, password) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name.get());
            pstmt.setString(2, ssn.get());
            pstmt.setString(3, email.get());
            pstmt.setString(4, password.get());
            pstmt.executeUpdate();
            System.out.println("user inserted into table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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



    public String getSsn() {
        return ssn.get();
    }

    public StringProperty ssnProperty() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn.set(String.valueOf(ssn));
    }

    public boolean isNameUnique() {
        GetDatabaseConn dbconn = new GetDatabaseConn();
        Connection conn = dbconn.getDBconnection();
        String query = "SELECT * FROM user WHERE name=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name.get());
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
