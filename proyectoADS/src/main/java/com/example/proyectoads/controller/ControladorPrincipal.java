package com.example.proyectoads.controller;

import com.example.proyectoads.HelloApplication;
import com.example.proyectoads.model.Universidad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorPrincipal {

    @FXML
    private Button btnEstudiante;

    @FXML
    private Button btnAdministrativo;

    @FXML
    private Button btnDirector;

    Universidad universidad;

    public ControladorPrincipal(Universidad universidad) {
        this.universidad = universidad;
    }

    @FXML
    public void initialize() {
        btnEstudiante.setOnAction(e -> {
            Stage stage = (Stage) btnEstudiante.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu_Estudiante.fxml"));
            fxmlLoader.setController(new ControladorEstudiante(universidad));
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
        btnAdministrativo.setOnAction(e ->{
            Stage stage = (Stage) btnEstudiante.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu_Administrador.fxml"));
            fxmlLoader.setController(new ControladorAdministrador(universidad));
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

        btnDirector.setOnAction(e -> System.out.println("Director de Carrera seleccionado"));
    }
}
