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

public class ControladorEstudiante extends ControllerAcademico {
    @FXML
    private Button btnPrerrequisitos;

    @FXML
    private Button btnCorrequisitos;

    @FXML
    private Button btnVolver;

    public ControladorEstudiante(Universidad universidad) {
        super(universidad);
    }

    @FXML
    public void initialize() {
        btnPrerrequisitos.setOnAction(e -> {
            Stage stage = (Stage) btnPrerrequisitos.getScene().getWindow();
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

        btnCorrequisitos.setOnAction(e -> {
            Stage stage = (Stage) btnPrerrequisitos.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Interfaz_Correquisitos.fxml"));
            fxmlLoader.setController(new ControladorCorrequisitos(super.getUniversidad()));
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
