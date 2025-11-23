package com.example.proyectoads.controller; // Ajusta el paquete según tu estructura

import com.example.proyectoads.Main;
import com.example.proyectoads.manejadorArchivos.ManejadorCSV;
import com.example.proyectoads.manejadorArchivos.ManejadorJSON;
import com.example.proyectoads.manejadorArchivos.SerializadoDatos;
import com.example.proyectoads.model.Asignatura;
import com.example.proyectoads.model.Departamento;
import com.example.proyectoads.model.Universidad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCargarGuardar extends ControllerAdministrativo {

    // --- Columna 1 ---
    @FXML
    private Button btnCargarJSON;

    @FXML
    private Button btnCargarCSV;

    @FXML
    private Button btnCargarJAVA;

    // --- Columna 2 ---
    @FXML
    private Button btnGuardarJSON;

    @FXML
    private Button btnGuardarCSV;

    @FXML
    private Button btnGuardarJAVA;

    @FXML
    private Button btnVolver;

    public ControladorCargarGuardar(Universidad universidad) {
        super(universidad);
    }


    public void initialize() {
        btnCargarJAVA.setOnAction(event -> {
            setUniversidad((Universidad)SerializadoDatos.deserializar("Universidad.dat"));
        });
        btnGuardarJAVA.setOnAction(event -> {
            SerializadoDatos.serializar(getUniversidad(), "Universidad.dat");
        });
        //----------------------------
        btnCargarCSV.setOnAction(event -> {
            List <Universidad> temp = ManejadorCSV.csvAObjetos("Universidad.csv",Universidad.class);
            temp.getFirst().setDepartamentos(ManejadorCSV.csvAObjetos("Departamentos.csv", Departamento.class));
            super.setUniversidad(temp.getFirst());
        });
        btnGuardarCSV.setOnAction(event -> {
            List <Universidad> temp = new ArrayList<>();
            temp.addFirst(getUniversidad());
            ManejadorCSV.objetosACSV("Universidad.csv", temp, new String[]{"nombre","departamentos"});
            ManejadorCSV.objetosACSV("Departamentos.csv", temp.getFirst().getDepartamentos(), new String[]{"codigo","nombre", "asignaturas", "profesores" });
            for (Departamento departamento : temp.getFirst().getDepartamentos()) {
                ManejadorCSV.objetosACSV( departamento.getNombre().trim() +"_Asignaturas.csv", departamento.getAsignaturas(), new String[]{"codigo","nombre", "creditos", "requiereIngles","prerrequisitos","correquisitos","clases" });
                ManejadorCSV.objetosACSV( departamento.getNombre().trim() +"_Profesores.csv", departamento.getProfesores(), new String[]{"id;","nombre", "email", "departamento","clasesAsignadas","salarioBase"});
                for (Asignatura asignatura : departamento.getAsignaturas()) {
                    ManejadorCSV.objetosACSV( departamento.getNombre().trim() +"_" + asignatura.getNombre() + "_Correquisitos.csv", asignatura.getCorrequisitos(), new String[]{"codigo","nombre", "creditos", "requiereIngles","prerrequisitos","correquisitos","clases" });
                    ManejadorCSV.objetosACSV( departamento.getNombre().trim() +"_" + asignatura.getNombre() + "_Prerrequisitos.csv", asignatura.getPrerrequisitos(), new String[]{"codigo","nombre", "creditos", "requiereIngles","prerrequisitos","correquisitos","clases" });
                    ManejadorCSV.objetosACSV( departamento.getNombre().trim() +"_" + asignatura.getNombre() + "_Clases.csv", asignatura.getClases(), new String[]{"codigo","semestre", "cupoMaximo", "horarios"});
                }
            }
        });
        //-----------------------------
        btnCargarJSON.setOnAction(event -> {
            setUniversidad((Universidad) ManejadorJSON.leerJSON("UniversidadJ.json",Universidad.class));
        });
        btnGuardarJSON.setOnAction(event -> {
            ManejadorJSON.guardarJSON(getUniversidad().getDepartamentos(), "Universidad.json");
            ManejadorJSON.guardarJSON(getUniversidad().getDepartamentos(), "Departamentos.json");
            for (Departamento departamento : getUniversidad().getDepartamentos()) {
                ManejadorJSON.guardarJSON(departamento.getAsignaturas(), departamento.getNombre().trim() +"_Asignaturas.json");
                ManejadorJSON.guardarJSON(departamento.getProfesores(), departamento.getNombre().trim() +"_Profesores.json");
                for (Asignatura asignatura : departamento.getAsignaturas()) {
                    ManejadorJSON.guardarJSON(asignatura.getClases(), departamento.getNombre().trim() + "_" + asignatura.getNombre() + "Clases.json");
                    ManejadorJSON.guardarJSON(asignatura.getCorrequisitos(), departamento.getNombre().trim() + "_" + asignatura.getNombre() + "Correquisitos.json");
                    ManejadorJSON.guardarJSON(asignatura.getPrerrequisitos(), departamento.getNombre().trim() + "_" + asignatura.getNombre() + "Prerrequisitos.json");
                }
            }
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