package com.example.proyectoads.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Universidad implements Serializable {

    private String nombre;
    private List<Departamento> departamentos;

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.departamentos = new ArrayList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }


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

    public String agregarClaseProf(String codDepto, String nombreP, String codClase) {
        Departamento depto = buscarDepartamentoPorCodigo(codDepto);
        if (depto != null) {
            // Delega la responsabilidad al departamento encontrado
            return depto.agregarClaseProf(nombreP, codClase);
        }
        return "Error: No se encontró el departamento con código " + codDepto;
    }

    public Map<Profesor, Double> crearNomina(String codDepto) {
        Departamento depto = buscarDepartamentoPorCodigo(codDepto);
        if (depto != null) {
            // Delega la responsabilidad al departamento encontrado
            return depto.crearNomina();
        }
        return null;
    }

}
