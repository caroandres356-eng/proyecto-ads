package com.example.proyectoads.controller;

import com.example.proyectoads.Main;
import com.example.proyectoads.model.Universidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorPrerrequisitos extends ControllerAcademico {
    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtAsignatura;

    @FXML
    private Button btnVolver;


    public ControladorPrerrequisitos(Universidad universidad) {
        super(universidad);
    }

    @FXML
    private void onActionConsultar() {
        String departamento = txtDepartamento.getText();
        String asignatura = txtAsignatura.getText();
        String mostrar = super.consultarPrerrequisitos(departamento,asignatura);
        super.mostrarVentanaTexto("Prerrequisitos", mostrar);
    }

    @FXML
    public void onActionVolver(ActionEvent e) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu_Estudiante.fxml"));
        fxmlLoader.setController(new ControladorEstudiante(super.getUniversidad()));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 500);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
