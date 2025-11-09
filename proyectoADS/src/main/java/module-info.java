module com.example.proyectoads {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoads to javafx.fxml;
    exports com.example.proyectoads;
}