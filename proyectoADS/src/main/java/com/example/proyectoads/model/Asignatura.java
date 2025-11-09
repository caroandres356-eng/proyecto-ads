package com.example.proyectoads.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Asignatura {
    private String codigo;
    private String nombre;
    private int creditos;
    private boolean requiereIngles;
    private List<Asignatura> prerrequisitos;
    private List<Asignatura> correquisitos;
    private List<Clase> clases = new ArrayList<>();
    //constructor
    public Asignatura(String codigo, String nombre, int creditos, boolean requiereIngles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.requiereIngles = requiereIngles;
        this.prerrequisitos = new ArrayList<>();
        this.correquisitos = new ArrayList<>();
        this.clases = new ArrayList<>();
    }

    public void agregarPrerrequisito(Asignatura a) { prerrequisitos.add(a); }
    public void agregarCorrequisito(Asignatura a) { correquisitos.add(a); }


    //getters and setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCreditos() { return creditos; }
    public boolean isRequiereIngles() { return requiereIngles; }
    public List<Asignatura> getPrerrequisitos() { return prerrequisitos; }
    public List<Asignatura> getCorrequisitos() { return correquisitos; }
    public List<Clase> getClases() { return clases; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
    public void setRequiereIngles(boolean requiereIngles) { this.requiereIngles = requiereIngles;}
    public void setPrerrequisitos(List<Asignatura> prerrequisitos) { this.prerrequisitos = prerrequisitos;}
    public void setCorrequisitos(List<Asignatura> correquisitos) { this.correquisitos = correquisitos; }
    public void setClases(List<Clase> clases) {this.clases = clases; }

    @Override
    public String toString() {
        return "Asignatura{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", requiereIngles=" + requiereIngles +
                ", prerrequisitos=" + prerrequisitos +
                ", correquisitos=" + correquisitos +
                '}';
    }


    //Metodos auxiliares

    //Metodos de las funcionalidades

    //Listar los prerrequisitos
    public String listarPrerrequisitos() {
        if (prerrequisitos.isEmpty()) return "No tiene prerrequisitos.";
        StringBuilder sb = new StringBuilder();
        for (Asignatura pre : prerrequisitos) {
            sb.append("- ").append(pre.getNombre())
                    .append(" (").append(pre.getCodigo()).append(")\n");
        }
        return sb.toString();
    }

    //Listar los correquisitos
    public String listarCorrequisitos() {
        if (correquisitos.isEmpty()) return "No tiene correquisitos.";
        StringBuilder sb = new StringBuilder();
        for (Asignatura co : correquisitos) {
            sb.append("- ").append(co.getNombre())
                    .append(" (").append(co.getCodigo()).append(")\n");
        }
        return sb.toString();
    }

    //Crear una clase y agregarla
    public String crearClase(String codigo, String semestre, int cupoMaximo, List<Horario> horarios) {
        //Crear el objeto clase
        Clase nueva = new Clase(codigo, semestre, cupoMaximo);
        //Añadir el horario que se paso
        for (Horario h : horarios) {
            nueva.agregarHorario(h);
        }
        //Agregar la clase a la asigantura
        clases.add(nueva);

        return "Clase " + codigo + " creada exitosamente para la asignatura " + nombre;
    }


}
