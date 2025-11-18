package com.example.proyectoads.controller;

import com.example.proyectoads.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.stream.Collectors;

public class AsignarClaseController extends ControllerAdministrativo {

    // --- CONEXIÓN CON EL FXML ---
    @FXML private ComboBox<String> departamentoComboBox;
    @FXML private ComboBox<String> profesorComboBox;
    @FXML private ComboBox<String> claseComboBox;
    @FXML private Button asignarButton;
    @FXML private Label mensajeLabel;

    public AsignarClaseController(Universidad universidad) {
        super(universidad);
    }

    @FXML
    public void initialize() {
        departamentoComboBox.getItems().addAll(
                getUniversidad().getDepartamentos().stream()
                        .map(Departamento::getNombre)
                        .collect(Collectors.toList())
        );

        // Deshabilitar los otros ComboBox hasta que se seleccione un departamento
        profesorComboBox.setDisable(true);
        claseComboBox.setDisable(true);

        // Agregar un listener para reaccionar a la selección de un departamento
        departamentoComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            cargarProfesoresYClases(newValue);
        });

        mensajeLabel.setText("");
    }

    private void cargarProfesoresYClases(String nombreDepto) {
        // Limpiar selecciones y listas anteriores
        profesorComboBox.getItems().clear();
        claseComboBox.getItems().clear();
        profesorComboBox.setValue(null);
        claseComboBox.setValue(null);

        // Encontrar el objeto Departamento a partir de su nombre
        Departamento deptoSeleccionado = getUniversidad().getDepartamentos().stream()
                .filter(d -> d.getNombre().equals(nombreDepto))
                .findFirst().orElse(null);

        if (deptoSeleccionado != null) {
            // Cargar los nombres de los profesores de ese departamento
            profesorComboBox.getItems().addAll(
                    deptoSeleccionado.getProfesores().stream()
                            .map(Profesor::getNombre)
                            .collect(Collectors.toList())
            );


            for (Asignatura asig : deptoSeleccionado.getAsignaturas()) {
                claseComboBox.getItems().addAll(
                        asig.getClases().stream()
                                .map(Clase::getCodigo)
                                .collect(Collectors.toList())
                );
            }
            // Habilitar los ComboBox
            profesorComboBox.setDisable(false);
            claseComboBox.setDisable(false);
        }
    }

    @FXML
    protected void handleAsignarClase() {
        String nombreDepto = departamentoComboBox.getValue();
        String nombreProfesor = profesorComboBox.getValue();
        String codigoClase = claseComboBox.getValue();

        if (nombreDepto == null || nombreProfesor == null || codigoClase == null) {
            mensajeLabel.setText("Error: Debe seleccionar todas las opciones.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Encontrar el código del departamento a partir de su nombre
        String codigoDepto = getUniversidad().getDepartamentos().stream()
                .filter(d -> d.getNombre().equals(nombreDepto))
                .map(Departamento::getCodigo)
                .findFirst().orElse("");

        if (!codigoDepto.isEmpty()) {
            // ¡Aquí ocurre la magia! Llamamos al controlador de lógica
            String respuesta =getUniversidad().agregarClaseProf(codigoDepto, nombreProfesor, codigoClase);

            mensajeLabel.setText(respuesta);
            if (respuesta.startsWith("Éxito")) {
                mensajeLabel.setStyle("-fx-text-fill: green;");
            } else {
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        }
    }
}
