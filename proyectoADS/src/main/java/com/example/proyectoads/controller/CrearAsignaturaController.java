package com.example.proyectoads.controller;

import com.example.proyectoads.Main;
import com.example.proyectoads.model.Universidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrearAsignaturaController extends ControllerAcademico{

    @FXML private TextField txtCodDepto;
    @FXML private TextField txtCodAsig;
    @FXML private TextField txtNombre;
    @FXML private Spinner<Integer> spinnerCreditos;
    @FXML private CheckBox chkExamenIngles;

    @FXML private ListView<String> listCorrequisitos;
    @FXML private ListView<String> listPrerrequisitos;
    @FXML private TextField txtAddCorre;
    @FXML private TextField txtAddPre;

    @FXML private Button btnCrear;
    @FXML private Button btnVolver;


    private Universidad universidad;

    private ControllerAcademico parentController;

    private ObservableList<String> correList = FXCollections.observableArrayList();
    private ObservableList<String> preList = FXCollections.observableArrayList();

    public CrearAsignaturaController(Universidad universidad) {
        super(universidad);
    }

    @FXML
    public void initialize() {
        listCorrequisitos.setItems(correList);
        listPrerrequisitos.setItems(preList);
        if (spinnerCreditos.getValueFactory() == null) {
            spinnerCreditos.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 3));
        }
    }

    @FXML
    private void onAddCorre(ActionEvent e) {
        String code = txtAddCorre.getText();
        if (code != null && !code.isBlank()) {
            correList.add(code.trim());
            txtAddCorre.clear();
        }
    }

    @FXML
    private void onRemoveCorre(ActionEvent e) {
        String sel = listCorrequisitos.getSelectionModel().getSelectedItem();
        if (sel != null) correList.remove(sel);
    }

    @FXML
    private void onAddPre(ActionEvent e) {
        String code = txtAddPre.getText();
        if (code != null && !code.isBlank()) {
            preList.add(code.trim());
            txtAddPre.clear();
        }
    }

    @FXML
    private void onRemovePre(ActionEvent e) {
        String sel = listPrerrequisitos.getSelectionModel().getSelectedItem();
        if (sel != null) preList.remove(sel);
    }

    // --- Crear asignatura ---
    @FXML
    private void onCrearAsignatura(ActionEvent e) {
        String codDepto = txtCodDepto.getText();
        String codAsig = txtCodAsig.getText();
        String nombre = txtNombre.getText();
        int creditos = spinnerCreditos.getValue();
        boolean examenIngles = chkExamenIngles.isSelected();

        List<String> listaCo = new ArrayList<>(correList);
        List<String> listaPre = new ArrayList<>(preList);

        // Validaciones básicas
        if (codDepto == null || codDepto.isBlank() ||
                codAsig == null || codAsig.isBlank() ||
                nombre == null || nombre.isBlank()) {
            super.mostrarVentanaTexto("Faltan datos", "Por favor completa código departamento, código asignatura y nombre.");
            return;
        }

        String resultado = super.crearAsignatura(codDepto, codAsig, nombre, creditos, examenIngles, listaCo, listaPre);

        super.mostrarVentanaTexto("Resultado", resultado);
    }

    @FXML
    private void onVolver(ActionEvent e) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu_Administrador.fxml"));
        fxmlLoader.setController(new ControladorAdministrador(super.getUniversidad()));
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
