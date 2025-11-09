package com.example.proyectoads.controller;

import com.example.proyectoads.model.*;

import java.util.List;
import java.util.Objects;

public class ControllerAcademico {

    private Universidad universidad;

    public ControllerAcademico(Universidad universidad) {
        this.universidad = universidad;
    }

    // --- Registro de Entidades ---
    public void registrarEstudiante(Estudiante e) {
        universidad.getEstudiantes().add(e);
    }

    public void registrarProfesor(Profesor p) {
        universidad.getProfesores().add(p);
    }

    public void registrarAsignatura(Asignatura a) {
        universidad.getAsignaturas().add(a);
    }

    public void crearClase(Clase c) {
        universidad.getClases().add(c);
    }

    // --- Consultas ---
    public Estudiante buscarEstudiante(String codigo) {
        for (Estudiante e : universidad.getEstudiantes()) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) return e;
        }
        return null;
    }

    public Profesor buscarProfesor(String id) {
        for (Profesor p : universidad.getProfesores()) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public Asignatura buscarAsignatura(String codigo) {
        for (Asignatura a : universidad.getAsignaturas()) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) return a;
        }
        return null;
    }

    public Clase buscarClase(String codigoClase) {
        for (Clase c : universidad.getClases()) {
            if (c.getCodigo().equalsIgnoreCase(codigoClase)) return c;
        }
        return null;
    }

    // --- Gestión de Clases ---


    // --- Reportes centrados en estudiante ---
    public void mostrarClasesDeEstudiante(String codigoEst) {
        Estudiante e = buscarEstudiante(codigoEst);
        if (e != null) {
            System.out.println("Clases inscritas de " + e.getNombre() + ":");
            e.getClasesInscritas().forEach(System.out::println);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }


    //Metodos de las funcionalidades
    //Crear Asignatura
    public String crearAsignatura(String codigoDepto, String codigoAsig, String nombre, int creditos,
                                      boolean requiereIngles, List<String> prerrequisitos, List<String> correquisitos) {
        //Delegar a metodo de universidad
        return universidad.crearAsignatura(codigoDepto, codigoAsig, nombre, creditos, requiereIngles, prerrequisitos, correquisitos);
    }

    //Consultar Prerrequisitos
    public String consultarPrerrequisitos(String codigoDepto, String codigoAsig) {
        return universidad.consultarPrerrequisitos(codigoDepto, codigoAsig);
    }

    //Consultar Correquisitos
    public String consultarCorrequisitos(String codigoDepto, String codigoAsig) {
        return universidad.consultarCorrequisitos(codigoDepto, codigoAsig);
    }

    //Crear clase
    public String crearClase(String codigoDepto, String codigoAsig, String codigoClase, String semestre, int cupoMaximo, List<Horario> horarios) {
        return universidad.crearClase(codigoDepto, codigoAsig, codigoClase, semestre, cupoMaximo, horarios);
    }

}
