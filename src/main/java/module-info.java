module com.example.lokaverkefnihbv401g {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens vidmot to javafx.fxml;
    exports vidmot;
}
