package com.example.proyectoads.controller;

import com.example.proyectoads.HelloApplication;
import com.example.proyectoads.model.Universidad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorAdministrador extends ControllerAdministrativo {
    @FXML
    private Button btnCrearClase;

    @FXML
    private Button btnCrearAsignatura;

    @FXML
    private Button btnVolver;

    public ControladorAdministrador(Universidad universidad) {
        super(universidad);
    }

    @FXML
    public void initialize() {
        btnCrearClase.setOnAction(e -> {
            Stage stage = (Stage) btnCrearClase.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Interfaz_Prerrequisitos.fxml"));
            fxmlLoader.setController(new ControladorPrerrequisitos(super.getUniversidad()));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 800, 600);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });

        btnCrearAsignatura.setOnAction(e -> {
            Stage stage = (Stage) btnCrearAsignatura.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("crear_asignatura.fxml"));
            fxmlLoader.setController(new CrearAsignaturaController(super.getUniversidad()));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 800, 600);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });

        btnVolver.setOnAction(e -> {
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Principal.fxml"));
            fxmlLoader.setController(new ControladorPrincipal(super.getUniversidad()));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 320, 240);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });
    }
}
