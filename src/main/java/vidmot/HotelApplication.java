package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vinnsla.Hotel;

import java.io.IOException;

public class HotelApplication extends Application {
    private HotelController hotelController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("hotel-view.fxml"));
        Parent root = fxmlLoader.load(); // Load the FXML file and obtain the root parent

        // Retrieve the view instance from the FXMLLoader
        HotelView hotelView = fxmlLoader.getController();


        // Set the scene on ViewSwitcher and switch to HEIMASIDA view
        var scene = new Scene(root);
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.HEIMASIDA);
        stage.setTitle("Hotel Landsins!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
