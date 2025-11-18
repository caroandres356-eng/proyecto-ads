package com.example.proyectoads.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Departamento {
    private String codigo;
    private String nombre;
    private List<Asignatura> asignaturas;
    private List<Profesor> profesores;

    public Departamento(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.asignaturas = new ArrayList<>();
        this.profesores = new ArrayList<>();
    }

    //get y setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public List<Asignatura> getAsignaturas() { return asignaturas; }
    public List<Profesor> getProfesores() { return profesores; }


    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }

    //Metodos auxiliares

    //Agregar una asignatura a la lista de las asignaturas
    public void agregarAsignatura(Asignatura a) {
        asignaturas.add(a);
    }

    //Buscar asignatura
    public Asignatura buscarAsignaturaPorCodigo(String codigo) {
        for (Asignatura a : asignaturas) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }


    //Metodos de funcionalidades

    //Crear asignatura
    public String crearAsignatura(String codAsig, String nombre, int creditos, boolean examenIngles, List<String> listaCo, List<String> listaPre) {

        //Verificar que no exista una asignatura con el mismo codigo
        if (buscarAsignaturaPorCodigo(codAsig) != null) {
            return "Ya existe una asignatura con el código " + codAsig;
        }
        //Verificar que los creditos sean validos
        if (creditos <= 0) {
            return "Los créditos deben ser mayores que 0";
        }

        //Crear objeto de asignatura
        Asignatura nueva = new Asignatura(codAsig, nombre, creditos, examenIngles);


        //La lista de prerrequisitos/correquisitos es de strings del codigo de las asignaturas
        //Agregar prerequisitos en el caso de que se haya pasado la lista
        if (listaPre != null) {
            for (String pre : listaPre) {
                Asignatura asigPre = buscarAsignaturaPorCodigo(pre); //buscar una asignatura con el codigo del prerrequisito
                if (asigPre == null) {
                    return "Prerrequisito no encontrado: " + pre;
                }
                nueva.agregarPrerrequisito(asigPre);
            }
        }

        //Agregar correquisitos en el caso de que se haya pasado la lista
        if (listaCo != null) {
            for (String co : listaCo) {
                Asignatura asigCo = buscarAsignaturaPorCodigo(co); //buscar una asignatura con el codigo del correquisitos
                if (asigCo == null) {
                    return "Correquisito no encontrado: " + co;
                }
                nueva.agregarCorrequisito(asigCo);
            }
        }

        asignaturas.add(nueva);
        //Devolver el toString de la asignatura creada para mostrar en consola
        return "Asignatura creada: " + nueva;
    }


    //Consultar Prerrequisitos de una asignatura
    public String consultarPrerrequisitos(String codigoAsig) {
        Asignatura asig = buscarAsignaturaPorCodigo(codigoAsig);
        if (asig == null) {
            return "No se encontró la asignatura con código " + codigoAsig;
        }
        return "Prerrequisitos de " + asig.getNombre() + ":\n" + asig.listarPrerrequisitos();
    }

    //Consultar Correquisitos de una asignatura
    public String consultarCorrequisitos(String codigoAsig) {
        Asignatura asig = buscarAsignaturaPorCodigo(codigoAsig);
        if (asig == null) {
            return "No se encontró la asignatura con código " + codigoAsig;
        }
        return "Correquisitos de " + asig.getNombre() + ":\n" + asig.listarCorrequisitos();
    }

    //Crear una clase
    public String crearClase(String codigoAsig, String codigoClase, String semestre, int cupoMaximo, List<Horario> horarios) {
        //Buscar la asignatura
        Asignatura asig = buscarAsignaturaPorCodigo(codigoAsig);

        //Verificar que exista
        if (asig != null) {
            //Delegar al metodo de asignatura
            return asig.crearClase(codigoClase, semestre, cupoMaximo, horarios);
        }
        return "Asignatura no encontrada en el departamento.";
    }

    public String agregarClaseProf(String nombreP, String codClase) {
        Profesor profesor = buscarProfesor(nombreP);
        if (profesor == null) {
            return "Error: No se encontró al profesor '" + nombreP + "'.";
        }

        Clase clase = buscarClase(codClase);
        if (clase == null) {
            return "Error: No se encontró la clase con código '" + codClase + "'.";
        }

        // Si ambos existen, delegamos la acción al profesor
        return profesor.agregarClase(clase);
    }

    public Profesor buscarProfesor(String nombreP) {
        for (Profesor prof : this.profesores) {
            // Usamos equalsIgnoreCase para que no importe si el nombre está en mayúsculas o minúsculas
            if (prof.getNombre().equalsIgnoreCase(nombreP)) {
                return prof;
            }
        }
        return null; // Retorna null si no se encuentra el profesor
    }

    public Clase buscarClase(String codClase) {
        // Para buscar una clase, debemos buscar dentro de cada asignatura
        for (Asignatura asig : this.asignaturas) {
            for (Clase clase : asig.getClases()) {
                if (clase.getCodigo().equalsIgnoreCase(codClase)) {
                    return clase;
                }
            }
        }
        return null; // Retorna null si no se encuentra la clase
    }

    public Map<Profesor, Double> crearNomina() {
        Map<Profesor, Double> nomina = new HashMap<>();
        for (Profesor prof : this.profesores) {
            double salarioCalculado = prof.calcularSalario();
            nomina.put(prof, salarioCalculado);
        }
        return nomina;
    }

}

