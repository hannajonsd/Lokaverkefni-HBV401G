package vidmot;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import vinnsla.GetDatabaseConn;
import vinnsla.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LoginDialog extends Dialog<User>{
    public Button fxNyskraning;
    @FXML
    private DialogPane dialogPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ButtonType cancelbutton;

    private boolean loggedIn = false;
    private boolean cancel = false;

    public LoginDialog() {
        setDialogPane(readUser());
        fxNyskraning.setOnAction(event -> {
            register();
        });
        setResultConverter(buttonType -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (!isUsernameInDb(username)) {
                    setHeaderText("Nafn ekki til");
                } else if (!doPasswordMatch(password, username)) {
                    setHeaderText("Vitlaust lykilorð");
                } else {
                    User user = getUserByName(username);
                    System.out.println(user);
                    loggedIn = true;
                    return user;
                }
                return null;
        });
    }

    public User getUser(){
        while(!loggedIn && !cancel) {

            Optional<User> u = showAndWait();
            if(loggedIn){

                return u.orElse(null);
            }
            System.out.println(cancel);
        }
        return null;
    }

    private DialogPane readUser(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void register() {
        UserDialog ud = new UserDialog();
        User user = ud.getUser();
    }
}
