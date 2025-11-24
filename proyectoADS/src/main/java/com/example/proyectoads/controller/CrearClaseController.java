package com.example.proyectoads.controller;

import com.example.proyectoads.Main;
import com.example.proyectoads.model.Horario;
import com.example.proyectoads.model.Universidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrearClaseController extends ControllerAdministrativo {

    @FXML private TextField txtCodigoDepto;
    @FXML private TextField txtCodigoAsig;
    @FXML private TextField txtCodigoClase;
    @FXML private TextField txtSemestre;
    @FXML private TextField txtCupoMaximo;

    @FXML private ListView<Horario> listaHorarios;
    @FXML private Button btnAgregarHorario;
    @FXML private Button btnCrearClase;
    @FXML private Button btnVolver;

    private final ObservableList<Horario> horariosObservable = FXCollections.observableArrayList();

    public CrearClaseController(Universidad universidad) {
        super(universidad);
    }

    @FXML
    public void initialize() {
        listaHorarios.setItems(horariosObservable);

        // Mostrar cada Horario con una representación legible
        listaHorarios.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Horario item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatHorario(item));
                }
            }
        });

        // botón suelto por si quieres hacer algo adicional
        btnAgregarHorario.setOnAction(this::onAgregarHorario);

        btnCrearClase.setOnAction(event -> {
            String codigoDepto = txtCodigoDepto.getText();
            String codigoAsig = txtCodigoAsig.getText();
            String codigoClase = txtCodigoClase.getText();
            String semestre = txtSemestre.getText();
            String cupoStr = txtCupoMaximo.getText();

            // Validaciones básicas
            if (isBlank(codigoDepto) || isBlank(codigoAsig) || isBlank(codigoClase) || isBlank(semestre) || isBlank(cupoStr)) {
                showAlert(Alert.AlertType.WARNING, "Datos incompletos", "Por favor complete todos los campos obligatorios.");
                return;
            }

            int cupoMaximo;
            try {
                cupoMaximo = Integer.parseInt(cupoStr.trim());
                if (cupoMaximo <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Cupo inválido", "El cupo máximo debe ser un número entero mayor que 0.");
                return;
            }

            List<Horario> listaHorarios = new ArrayList<>(horariosObservable);

            // Llamar a parentController.crearClase si está disponible
            String resultado;
            try {
                resultado = getUniversidad().crearClase(codigoDepto, codigoAsig, codigoClase, semestre, cupoMaximo, listaHorarios);
            } catch (Exception ex) {
                resultado = "Error al crear clase: " + ex.getMessage();
            }

            showAlert(Alert.AlertType.INFORMATION, "Resultado", resultado);
        });
    }

    // Setter para inyectar Universidad

    // Setter para inyectar ControllerAcademico (si quieres llamar crearClase ahí)

    // Handler para el botón "Agregar Horario"
    @FXML
    private void onAgregarHorario(ActionEvent event) {
        // Crear diálogo personalizado
        Dialog<Horario> dialog = new Dialog<>();
        dialog.setTitle("Agregar Horario");
        dialog.setHeaderText("Ingrese día, hora inicio, hora fin y salón");

        // Botones OK / Cancel
        ButtonType okType = new ButtonType("Agregar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

        // Contenido: GridPane con controles
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ComboBox<DayOfWeek> comboDia = new ComboBox<>();
        comboDia.getItems().addAll(DayOfWeek.values());
        comboDia.setPromptText("Día");

        TextField tfInicio = new TextField();
        tfInicio.setPromptText("HH:mm (ej. 08:00)");

        TextField tfFin = new TextField();
        tfFin.setPromptText("HH:mm (ej. 10:00)");

        TextField tfSalon = new TextField();
        tfSalon.setPromptText("Salón");

        grid.add(new Label("Día:"), 0, 0);
        grid.add(comboDia, 1, 0);
        grid.add(new Label("Hora inicio:"), 0, 1);
        grid.add(tfInicio, 1, 1);
        grid.add(new Label("Hora fin:"), 0, 2);
        grid.add(tfFin, 1, 2);
        grid.add(new Label("Salón:"), 0, 3);
        grid.add(tfSalon, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Habilitar/Deshabilitar botón OK según validación básica
        Node okButton = dialog.getDialogPane().lookupButton(okType);
        okButton.setDisable(true);

        // Listener simple para activar cuando haya valores mínimos
        comboDia.valueProperty().addListener((obs, o, n) -> {
            okButton.setDisable(!isHorarioDialogValid(comboDia, tfInicio, tfFin, tfSalon));
        });
        tfInicio.textProperty().addListener((obs, o, n) -> {
            okButton.setDisable(!isHorarioDialogValid(comboDia, tfInicio, tfFin, tfSalon));
        });
        tfFin.textProperty().addListener((obs, o, n) -> {
            okButton.setDisable(!isHorarioDialogValid(comboDia, tfInicio, tfFin, tfSalon));
        });
        tfSalon.textProperty().addListener((obs, o, n) -> {
            okButton.setDisable(!isHorarioDialogValid(comboDia, tfInicio, tfFin, tfSalon));
        });

        // Convertir resultado a Horario si OK
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okType) {
                try {
                    DayOfWeek dia = comboDia.getValue();
                    LocalTime inicio = LocalTime.parse(tfInicio.getText().trim());
                    LocalTime fin = LocalTime.parse(tfFin.getText().trim());
                    String salon = tfSalon.getText().trim();

                    if (fin.isBefore(inicio) || fin.equals(inicio)) {
                        showAlert(Alert.AlertType.ERROR, "Error de horario", "La hora fin debe ser posterior a la hora inicio.");
                        return null;
                    }

                    return new Horario(dia, inicio, fin, salon);
                } catch (DateTimeParseException ex) {
                    showAlert(Alert.AlertType.ERROR, "Formato inválido", "Las horas deben tener formato HH:mm (ej. 08:00).");
                    return null;
                }
            }
            return null;
        });

        Optional<Horario> resultado = dialog.showAndWait();
        resultado.ifPresent(h -> horariosObservable.add(h));
    }

    // Validación básica del dialog
    private boolean isHorarioDialogValid(ComboBox<DayOfWeek> dia, TextField inicio, TextField fin, TextField salon) {
        if (dia.getValue() == null) return false;
        if (salon.getText() == null || salon.getText().trim().isEmpty()) return false;
        String sIni = inicio.getText();
        String sFin = fin.getText();
        if (sIni == null || sIni.isBlank() || sFin == null || sFin.isBlank()) return false;
        // intentar parse rápido (no lanzar excepción aquí, solo validación simple)
        try {
            LocalTime.parse(sIni.trim());
            LocalTime.parse(sFin.trim());
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    // Formatear Horario para mostrar en la lista
    private String formatHorario(Horario h) {
        return String.format("%s  %s - %s  (%s)",
                h.getDia().toString(),
                h.getHoraInicio().toString(),
                h.getHoraFin().toString(),
                h.getSalon());
    }


    // Botón volver: cierra la ventana
    @FXML
    private void volver(ActionEvent event) throws IOException {
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

    private void showAlert(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
