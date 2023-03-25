package vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HotelController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Velkomin á heimasíðu Hótel Landsins");
    }
}