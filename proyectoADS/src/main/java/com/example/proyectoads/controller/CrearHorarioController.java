package com.example.proyectoads.controller;

import com.example.proyectoads.model.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CrearHorarioController extends ControllerAdministrativo {

    // --- PANEL IZQUIERDO (BÚSQUEDA) ---
    @FXML private ComboBox<String> departamentoComboBox;
    @FXML private TextField busquedaField;
    @FXML private VBox resultadosVBox;

    // --- PANEL CENTRAL (HORARIO) ---
    @FXML private GridPane horarioGrid;

    // --- PANEL DERECHO (GESTIÓN) ---
    @FXML private VBox inscritasVBox;
    @FXML private Label conflictoLabel;
    @FXML private Button retirarButton;
    @FXML private Label totalCreditosLabel;
    @FXML private Label totalAsignaturasLabel;

    // --- LÓGICA INTERNA DE LA VISTA ---
    private List<Clase> clasesInscritas = new ArrayList<>();
    private Clase claseSeleccionadaParaRetirar = null;
    private Node panelClaseSeleccionadaVisual = null;
    private final String[] DIAS = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
    private final String[] COLORES_CLASES = {"#ADD8E6", "#90EE90", "#FFB6C1", "#FFDAB9", "#E6E6FA", "#F0E68C"};

    public CrearHorarioController(Universidad universidad) {
        super(universidad);
    }

    @FXML
    public void initialize() {
        configurarGridHorario();

        departamentoComboBox.getItems().addAll(
                getUniversidad().getDepartamentos().stream()
                        .map(Departamento::getNombre)
                        .collect(Collectors.toList())
        );

        retirarButton.setDisable(true);
        conflictoLabel.setVisible(false);
        actualizarResumen();
    }

    private void configurarGridHorario() {
        for (int i = 0; i < DIAS.length; i++) {
            Label diaLabel = new Label(DIAS[i]);
            diaLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
            horarioGrid.add(diaLabel, i + 1, 0);
        }
        for (int i = 7; i <= 21; i++) {
            Label horaLabel = new Label(i + ":00");
            horarioGrid.add(horaLabel, 0, i - 6);
        }
    }

    @FXML
    private void handleBuscar() {
        resultadosVBox.getChildren().clear();

        String deptoSeleccionado = departamentoComboBox.getValue();
        if (deptoSeleccionado == null || deptoSeleccionado.trim().isEmpty()) {
            conflictoLabel.setText("Seleccione un departamento antes de buscar.");
            conflictoLabel.setVisible(true);
            return;
        }
        conflictoLabel.setVisible(false);

        Departamento depto = getUniversidad().getDepartamentos().stream()
                .filter(d -> d.getNombre() != null && d.getNombre().equalsIgnoreCase(deptoSeleccionado.trim()))
                .findFirst().orElse(null);

        if (depto == null) return;

        String terminoRaw = busquedaField.getText();
        String termino = terminoRaw == null ? "" : terminoRaw.trim().toLowerCase();

        boolean pareceCodigo = termino.matches(".*\\d.*") || termino.contains("-");

        int añadidos = 0;
        for (Asignatura asig : depto.getAsignaturas()) {
            if (asig.getClases() == null) continue;
            for (Clase clase : asig.getClases()) {
                boolean agregar = false;

                if (termino.isEmpty()) {
                    agregar = true;
                } else if (pareceCodigo) {
                    String codigoAsig = asig.getCodigo() == null ? "" : asig.getCodigo().toLowerCase();
                    String codigoClase = clase.getCodigo() == null ? "" : clase.getCodigo().toLowerCase();
                    if (codigoAsig.contains(termino) || codigoClase.contains(termino)) agregar = true;
                } else {
                    String nombreAsig = asig.getNombre() == null ? "" : asig.getNombre().toLowerCase();
                }

                if (agregar) {
                    resultadosVBox.getChildren().add(crearPanelResultadoBusqueda(asig, clase));
                    añadidos++;
                }
            }
        }

        if (añadidos == 0) {
            resultadosVBox.getChildren().add(new Label("No se encontraron resultados para la búsqueda."));
        }
    }

    private Node crearPanelResultadoBusqueda(Asignatura asignatura, Clase clase) {
        VBox panel = new VBox(5);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-background-radius: 5; -fx-border-radius: 5;");

        Label nombreClase = new Label(asignatura.getCodigo() + " - " + asignatura.getNombre());
        nombreClase.setFont(Font.font("System", FontWeight.BOLD, 13));

        StringBuilder horarioStr = new StringBuilder();
        for (Horario h : clase.getHorarios()) {
            horarioStr.append(h.getDia()).append(" ").append(h.getHoraInicio()).append(":00-").append(h.getHoraFin()).append(":00\n");
        }

        Button agregarButton = new Button("Agregar");
        agregarButton.setOnAction(e -> handleAgregarClase(clase, asignatura));

        panel.getChildren().addAll(nombreClase, new Label(horarioStr.toString().trim()), agregarButton);
        return panel;
    }

    private void handleAgregarClase(Clase clase, Asignatura asignatura) {
        if (clasesInscritas.contains(clase)) {
            mostrarConflicto("Error: Ya tienes esta clase inscrita.");
            return;
        }

        for (Clase inscrita : clasesInscritas) {
            if (hayCruce(inscrita, clase)) {
                mostrarConflicto("Cruce de horario con: " + inscrita.getCodigo());
                return;
            }
        }

        conflictoLabel.setVisible(false);
        clasesInscritas.add(clase);
        dibujarClaseEnHorario(clase, asignatura.getNombre());
        actualizarPanelGestion();
        actualizarResumen();
    }

    private void dibujarClaseEnHorario(Clase clase, String nombreAsignatura) {
        String color = COLORES_CLASES[new Random().nextInt(COLORES_CLASES.length)];
        for (Horario horario : clase.getHorarios()) {
            int col = diaToIndex(String.valueOf(horario.getDia()));
            int row = horario.getHoraInicio().getHour() - 6;
            int rowSpan = horario.getHoraFin().getHour() - horario.getHoraInicio().getHour();

            VBox bloqueClase = new VBox(2);
            bloqueClase.setStyle("-fx-background-color: " + color + "; -fx-border-color: gray; -fx-padding: 5; -fx-background-radius: 8;");
            bloqueClase.setUserData(clase);

            bloqueClase.getChildren().addAll(new Label(nombreAsignatura), new Label(horario.getSalon()));
            horarioGrid.add(bloqueClase, col, row, 1, rowSpan);
        }
    }

    private void actualizarPanelGestion() {
        inscritasVBox.getChildren().clear();
        for (Clase clase : clasesInscritas) {
            Label inscritaLabel = new Label(clase.getCodigo());
            inscritaLabel.setMaxWidth(Double.MAX_VALUE);
            inscritaLabel.setPadding(new Insets(5));

            inscritaLabel.setOnMouseClicked(e -> {
                claseSeleccionadaParaRetirar = clase;
                retirarButton.setDisable(false);
                if (panelClaseSeleccionadaVisual != null) panelClaseSeleccionadaVisual.setStyle("");
                inscritaLabel.setStyle("-fx-background-color: lightblue;");
                panelClaseSeleccionadaVisual = inscritaLabel;
            });
            inscritasVBox.getChildren().add(inscritaLabel);
        }
    }

    @FXML
    private void handleRetirarClase() {
        if (claseSeleccionadaParaRetirar != null) {
            horarioGrid.getChildren().removeIf(node -> claseSeleccionadaParaRetirar.equals(node.getUserData()));
            clasesInscritas.remove(claseSeleccionadaParaRetirar);
            actualizarPanelGestion();
            actualizarResumen();
            claseSeleccionadaParaRetirar = null;
            retirarButton.setDisable(true);
        }
    }

    private void actualizarResumen() {
        // En un sistema real, se buscaría la asignatura de cada clase para sumar los créditos.
        // Por simplicidad, asumimos 3 créditos por clase.
        int creditos = clasesInscritas.size() * 3;
        totalCreditosLabel.setText("Total créditos: " + creditos);
        totalAsignaturasLabel.setText("Asignaturas inscritas: " + clasesInscritas.size());
    }

    private void mostrarConflicto(String mensaje) {
        conflictoLabel.setText("! " + mensaje);
        conflictoLabel.setVisible(true);
    }

    private boolean hayCruce(Clase c1, Clase c2) {
        for (Horario h1 : c1.getHorarios()) {
            for (Horario h2 : c2.getHorarios()) {
                if (h1.getDia().equals(h2.getDia())) {
                    if (h2.getHoraInicio().getHour() < h1.getHoraFin().getHour() && h1.getHoraInicio().getHour() < h2.getHoraFin().getHour()) return true;
                }
            }
        }
        return false;
    }

    private int diaToIndex(String dia) {
        return java.util.Arrays.asList(DIAS).indexOf(dia) + 1;
    }
}