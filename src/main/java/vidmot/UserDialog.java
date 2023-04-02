package vidmot;

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

    /**
     * Hnappurinn sem viðskiptarvinu ýtir á þegar hann hefur sett inn upplýsingarnar sínar
     */
    @FXML
    public ButtonType fxILagi;


    /**
     * Upphafsstilling, þegar í lagi hnappurinn er valinn þá er nýr viðskiptavinur búin til

    public UserDialog(){
        setDialogPane(readUser());

        iLagiRegla();

        setResultConverter(buttonType -> {
            if(buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
                return new User(fxNafn.textProperty(), fxEmail.textProperty(), fxKennitala.textProperty());
            } else {
                return null;
            }
        });
    }
     */

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
        iLagi.disableProperty().bind(fxNafn.textProperty().isEmpty().or(fxEmail.textProperty().isEmpty()).or(fxKennitala.textProperty().isEmpty()));
    }
}
