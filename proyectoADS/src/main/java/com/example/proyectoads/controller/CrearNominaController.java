package com.example.proyectoads.controller;

import com.example.proyectoads.controller.ControladorAdministrador;
import com.example.proyectoads.model.Departamento;
import com.example.proyectoads.model.Profesor;
import com.example.proyectoads.model.Universidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Map;
import java.util.stream.Collectors;

public class CrearNominaController extends ControladorAdministrador {

    // --- CONEXIÓN CON EL FXML ---
    @FXML private ComboBox<String> departamentoComboBox;
    @FXML private TableView<NominaEntry> nominaTableView; // Usaremos la clase auxiliar NominaEntry
    @FXML private TableColumn<NominaEntry, String> profesorColumn;
    @FXML private TableColumn<NominaEntry, Double> salarioColumn;
    @FXML private Label mensajeLabel;

    public CrearNominaController(Universidad universidad) {
        super(universidad);
    }

    // --- LÓGICA DE LA INTERFAZ ---
    // Obtenemos la instancia estática del controlador de lógica

    @FXML
    public void initialize() {
        // 1. Configurar las columnas de la tabla para que sepan qué dato mostrar.
        profesorColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));
        salarioColumn.setCellValueFactory(new PropertyValueFactory<>("salarioCalculado"));

        // 2. Cargar los nombres de los departamentos en el ComboBox
        departamentoComboBox.getItems().addAll(
                getUniversidad().getDepartamentos().stream()
                        .map(Departamento::getNombre)
                        .collect(Collectors.toList())
        );

        mensajeLabel.setText("");
    }

    @FXML
    protected void handleGenerarNomina() {
        String nombreDepto = departamentoComboBox.getValue();

        // Limpiar la tabla y mensajes
        nominaTableView.getItems().clear();
        mensajeLabel.setText("");

        if (nombreDepto == null) {
            mensajeLabel.setText("Por favor, seleccione un departamento.");
            return;
        }

        // Encontrar el código del departamento a partir de su nombre
        String codigoDepto = getUniversidad().getDepartamentos().stream()
                .filter(d -> d.getNombre().equals(nombreDepto))
                .map(Departamento::getCodigo)
                .findFirst().orElse("");

        if (!codigoDepto.isEmpty()) {
            // ¡Llamamos al controlador de lógica para obtener la nómina!
            Map<Profesor, Double> nomina = getUniversidad().crearNomina(codigoDepto);

            if (nomina == null || nomina.isEmpty()) {
                mensajeLabel.setText("No se encontraron profesores para generar la nómina.");
                return;
            }

            // Convertir el Mapa a una lista que la TableView pueda entender
            ObservableList<NominaEntry> dataParaTabla = FXCollections.observableArrayList();
            for (Map.Entry<Profesor, Double> entry : nomina.entrySet()) {
                dataParaTabla.add(new NominaEntry(entry.getKey().getNombre(), entry.getValue()));
            }

            // Mostrar los datos en la tabla
            nominaTableView.setItems(dataParaTabla);
        }
    }

    /**
     * Clase auxiliar interna para representar una fila de la tabla de nómina.
     * JavaFX TableView necesita esta estructura para funcionar correctamente.
     */
    public static class NominaEntry {
        private final String nombreProfesor;
        private final Double salarioCalculado;

        public NominaEntry(String nombreProfesor, Double salarioCalculado) {
            this.nombreProfesor = nombreProfesor;
            this.salarioCalculado = salarioCalculado;
        }

        public String getNombreProfesor() {
            return nombreProfesor;
        }

        public Double getSalarioCalculado() {
            return salarioCalculado;
        }
    }
}
