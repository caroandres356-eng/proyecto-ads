package com.example.proyectoads.controller;

import com.example.proyectoads.model.*;

import java.util.List;
import java.util.Map;

public class ControllerAdministrativo {

    private Universidad universidad;

    public ControllerAdministrativo(Universidad universidad) {
        this.universidad = universidad;
    }

    //1. Crear asignatura
    public String crearAsignatura(String codDepto, String codAsig, String nombre, int creditos,
            boolean examenIngles, List<String> listaCo, List<String> listaPre) {
        //Delega a universidad
        return universidad.crearAsignatura(codDepto, codAsig, nombre, creditos, examenIngles, listaCo, listaPre);
    }

    public Universidad getUniversidad() {
        return universidad;
    }
// crear nomina por departamento 
public Map<Profesor, Double> crearNomina(String codigoDepto) {
    Map<Profesor, Double> nomina = universidad.buscarDepartamentoPorCodigo(codigoDepto).crearNomina();
    return nomina;
}
}
