package com.example.proyectoads.controller;

import com.example.proyectoads.HelloApplication;
import com.example.proyectoads.model.Universidad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import java.io.IOException;

public class ControladorCorrequisitos extends ControllerAcademico {
    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtAsignatura;

    @FXML
    private Button btnVolver;


    public ControladorCorrequisitos(Universidad universidad) {
        super(universidad);
    }

    @FXML
    private void onActionConsultar() {
        String departamento = txtDepartamento.getText();
        String asignatura = txtAsignatura.getText();
        String mostrar = super.consultarCorrequisitos(departamento,asignatura);
        super.mostrarVentanaTexto("Correquisistos", mostrar);
    }

    @FXML
    public void onActionVolver(ActionEvent e) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu_Estudiante.fxml"));
        fxmlLoader.setController(new ControladorEstudiante(super.getUniversidad()));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        }
}
