package com.example.proyectoads;

import com.example.proyectoads.controller.*;
import com.example.proyectoads.model.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Crear la universidad
        Universidad universidad = new Universidad("Universidad del Caribe");

        // Crear el departamento
        Departamento depto = new Departamento("D001", "Ingeniería de Sistemas");
        universidad.agregarDepartamento(depto);

        // Crear asignaturas base
        Asignatura prog1 = new Asignatura("IS101", "Programación I", 4, false);
        Asignatura mat1 = new Asignatura("IS102", "Matemáticas I", 3, false);
        depto.agregarAsignatura(prog1);
        depto.agregarAsignatura(mat1);

        // Crear el controlador académico
        ControllerAcademico controller = new ControllerAcademico(universidad);

        // Crear listas de prerequisitos y correquisitos
        List<String> prerequisitos = new ArrayList<>();
        prerequisitos.add("IS101");

        List<String> correquisitos = new ArrayList<>();
        correquisitos.add("IS102");

        // Crear nueva asignatura por medio del controlador
        String resultado = controller.crearAsignatura(
                "D001",
                "IS201",
                "Estructuras de Datos",
                4,
                true,
                prerequisitos,
                correquisitos
        );

        // Mostrar resultado
        System.out.println(resultado);

        // Comprobar que se creó
        Departamento d = universidad.buscarDepartamentoPorCodigo("D001");
        Asignatura asig = d.buscarAsignaturaPorCodigo("IS201");

        if (asig == null) System.out.println(" La asignatura no se creó correctamente.");

        //Para comprobar consultar prerrequisitos y correquisitos
        System.out.println("\n--- CONSULTAS ---");
        System.out.println(controller.consultarPrerrequisitos("D001", "IS201"));
        System.out.println(controller.consultarCorrequisitos("D001", "IS201"));



        //Comprobar
        System.out.println("\n--- CREACIÓN DE CLASE ---");

        // Crear algunos horarios
        Horario h1 = new Horario(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), "Salón 301");
        Horario h2 = new Horario(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), "Salón 302");

        List<Horario> horarios = new ArrayList<>();
        horarios.add(h1);
        horarios.add(h2);

        // Crear la clase por medio del controlador
        String resultadoClase = controller.crearClase(
                "D001",      // Departamento
                "IS201",     // Asignatura
                "CL001",     // Código de clase
                "2025-1",    // Semestre
                35,          // Cupo máximo
                horarios     // Lista de horarios
        );

        // Mostrar resultado
        System.out.println(resultadoClase);

        // Verificar que la clase se guardó correctamente
        Clase claseCreada = asig.getClases().get(0);
        System.out.println("\n Clase creada:");
        System.out.println("Código: " + claseCreada.getCodigo());
        System.out.println("Semestre: " + claseCreada.getSemestre());
        System.out.println("Cupo máximo: " + claseCreada.getCupoMaximo());
        System.out.println("Horarios:");
        for (Horario h : claseCreada.getHorarios()) {
            System.out.println(" - " + h.getDia() + " de " + h.getHoraInicio() + " a " + h.getHoraFin() + " en " + h.getSalon());
        }
    }
}
