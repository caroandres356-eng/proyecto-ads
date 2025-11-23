module com.example.proyectoads {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.base;
    requires com.google.gson;

    opens com.example.proyectoads.controller to javafx.fxml;
    opens com.example.proyectoads.model to javafx.fxml, com.google.gson;
    opens com.example.proyectoads to javafx.fxml;

    exports com.example.proyectoads;
}
