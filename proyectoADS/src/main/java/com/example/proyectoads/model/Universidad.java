package com.example.proyectoads.model;

import com.example.proyectoads.HelloApplication;
import com.example.proyectoads.controller.ControllerAcademico;
import com.example.proyectoads.controller.ControllerAdministrativo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Universidad {

    private String nombre;
    private List<Departamento> departamentos;
    private List<Profesor> profesores;
    private List<Estudiante> estudiantes;
    private List<Asignatura> asignaturas;
    private List<Clase> clases;

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.departamentos = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        this.clases = new ArrayList<>();

        // Inicializa controladores pasando la referencia de la universidad
        this.controllerAcademico = new ControllerAcademico(this);
        this.controllerAdministrativo = new ControllerAdministrativo(this);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public void setControllerAcademico(ControllerAcademico controllerAcademico) {
        this.controllerAcademico = controllerAcademico;
    }

    public void setControllerAdministrativo(ControllerAdministrativo controllerAdministrativo) {
        this.controllerAdministrativo = controllerAdministrativo;
    }

    private ControllerAcademico controllerAcademico;
    private ControllerAdministrativo controllerAdministrativo;

    public ControllerAcademico getControllerAcademico() {
        return controllerAcademico;
    }

    public ControllerAdministrativo getControllerAdministrativo() {
        return controllerAdministrativo;
    }

    @Override
    public String toString() {
        return "Universidad: " + nombre
                + "\nDepartamentos: " + departamentos.size()
                + "\nProfesores: " + profesores.size()
                + "\nEstudiantes: " + estudiantes.size()
                + "\nAsignaturas: " + asignaturas.size()
                + "\nClases: " + clases.size();
    }
    //Metodos auxiliares

    //agregar un departamento a la lista de departamentos
    public void agregarDepartamento(Departamento depto) {
        departamentos.add(depto);
    }

    //Buscar departamento por codigo
    public Departamento buscarDepartamentoPorCodigo(String codigo) {
        for (Departamento d : departamentos) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                return d;
            }
        }
        return null;
    }

    //Metodos de las funcionalidades
    //Crear asignatura
    public String crearAsignatura(String codDepto, String codAsig, String nombre, int creditos,
            boolean examenIngles, List<String> listaCo, List<String> listaPre) {

        Departamento depto = buscarDepartamentoPorCodigo(codDepto); //Buscar el departamento

        //Verificar que exista el departamento
        if (depto == null) {
            return "No se encontró el departamento con código " + codDepto;
        }

        //Delegar a metodo de departamento
        return depto.crearAsignatura(codAsig, nombre, creditos, examenIngles, listaCo, listaPre);
    }

    //Consultar Prerrequisitos de unas asignatura
    public String consultarPrerrequisitos(String codigoDepto, String codigoAsig) {
        Departamento depto = buscarDepartamentoPorCodigo(codigoDepto);
        if (depto == null) {
            return "No existe el departamento con código " + codigoDepto;
        }
        return depto.consultarPrerrequisitos(codigoAsig);
    }

    //Consultar Correquisitos de una asignatura
    public String consultarCorrequisitos(String codigoDepto, String codigoAsig) {
        Departamento depto = buscarDepartamentoPorCodigo(codigoDepto);
        if (depto == null) {
            return "No existe el departamento con código " + codigoDepto;
        }
        return depto.consultarCorrequisitos(codigoAsig);
    }

    //Crear una clase
    public String crearClase(String codigoDepto, String codigoAsig, String codigoClase, String semestre, int cupoMaximo, List<Horario> horarios) {
        //Buscar depto
        Departamento depto = buscarDepartamentoPorCodigo(codigoDepto);

        //Verificar que exista
        if (depto != null) {
            //Delegar al metodo de departamento
            return depto.crearClase(codigoAsig, codigoClase, semestre, cupoMaximo, horarios);
        }
        return "Departamento no encontrado.";
    }

}
