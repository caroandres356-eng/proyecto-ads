package com.example.proyectoads.controller;

import com.example.proyectoads.Main;
import com.example.proyectoads.model.Universidad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    @FXML
    private Button btnAgregarClaseProfesor;

    @FXML
    private Button btnCrearNominaProfesores;

    public ControladorAdministrador(Universidad universidad) {
        super(universidad);
    }

    @FXML
    public void initialize() {
        btnCrearClase.setOnAction(e -> {
            Stage stage = (Stage) btnCrearClase.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CrearClase.fxml"));
            fxmlLoader.setController(new CrearClaseController(super.getUniversidad()));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 500, 700);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });

        btnCrearAsignatura.setOnAction(e -> {
            Stage stage = (Stage) btnCrearAsignatura.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("crear_asignatura.fxml"));
            fxmlLoader.setController(new CrearAsignaturaController(super.getUniversidad()));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });

        btnAgregarClaseProfesor.setOnAction(e -> {
            Stage stage = (Stage) btnAgregarClaseProfesor.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AsignarClaseView.fxml"));
            fxmlLoader.setController(new AsignarClaseController(super.getUniversidad()));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 360);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });

        btnCrearNominaProfesores.setOnAction(e -> {
            Stage stage = (Stage) btnCrearNominaProfesores.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CrearNominaView.fxml"));
            fxmlLoader.setController(new CrearNominaController(super.getUniversidad()));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });

        btnVolver.setOnAction(e -> {
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Principal.fxml"));
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
