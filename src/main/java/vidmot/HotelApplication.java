package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("hotelSida-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hótel Landsins!");
        stage.setScene(scene);
        stage.show();

         */
        var scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.HEIMASIDA);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
