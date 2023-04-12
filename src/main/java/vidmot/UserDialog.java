package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import vinnsla.User;

import java.io.IOException;
import java.util.Optional;

public class UserDialog extends Dialog<User> {
    /**
     * Reiturinn þar sem viðskiptavinur slær inn nafnið sitt
     */
    @FXML
    public TextField fxNafn;
    /**
     * Reiturinn þar sem viðskiptavinur slær inn heimilisfangið sitt
     */
    @FXML
    public TextField fxEmail;

    /**
     * Reiturinn þar sem viðskiptavinur slær inn heimilisfangið sitt
     */
    @FXML
    public TextField fxKennitala;

    @FXML
    public TextField fxPassword;

    @FXML
    public TextField fxPasswordConfirm;

    /**
     * Hnappurinn sem viðskiptarvinu ýtir á þegar hann hefur sett inn upplýsingarnar sínar
     */
    @FXML
    public ButtonType fxILagi;


    /*
     * Upphafsstilling, þegar í lagi hnappurinn er valinn þá er nýr viðskiptavinur búin til
     */

    public UserDialog() {
        setDialogPane(readUser());

        iLagiRegla();

        setResultConverter(buttonType -> {
            if(buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
                if(fxPassword.getText().equals(fxPasswordConfirm.getText()))  {
                    System.out.println("user okey");
                    User user = new User(fxNafn.textProperty(), fxEmail.textProperty(), fxKennitala.textProperty(), fxPassword.textProperty());
                    boolean isNameUnique = user.isNameUnique();
                    if (isNameUnique) {
                        user.addUserDb();
                        return user;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Nafn í notkun!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Lykilorð ekki í samræmi!");
                    alert.showAndWait();
                }

            } else {
                return null;
            }
            return null;
        });
    }



    /**
     * Býr til dialogið fyrir innskráningu
     * @return Dialogið
     */
    private DialogPane readUser(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Bíður eftir upplýsingum um viðskiptavin og skilar þeim
     * @return Vidskiptavininum
     */
    public User getUser(){
        Optional<User> u = showAndWait();
        return u.orElse(null);
    }

    /**
     * regla þannig ekki er hægt að ýta á í lagi hnapp nema búið sé að setja upplýsingar inn í báða reiti
     */
    public void iLagiRegla(){
        Node iLagi = getDialogPane().lookupButton(fxILagi);
        iLagi.disableProperty().bind(fxNafn.textProperty().isEmpty().or(fxEmail.textProperty().isEmpty()).or(fxKennitala.textProperty().isEmpty()).or(fxPassword.textProperty().isEmpty()).or(fxPasswordConfirm.textProperty().isEmpty()));
    }
}
