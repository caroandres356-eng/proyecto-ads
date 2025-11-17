package com.example.proyectoads.model;

import java.util.ArrayList;
import java.util.List;

public class Clase {
    private String codigo;
    private String semestre;
    private int cupoMaximo;
    private List<Horario> horarios;

    public Clase(String codigo, String semestre, int cupoMaximo) {
        this.codigo = codigo;
        this.semestre = semestre;
        this.cupoMaximo = cupoMaximo;
        this.horarios = new ArrayList<>();
    }

    //Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "codigo='" + codigo + '\'' +
                ", horarios=" + horarios +
                ", cupoMaximo=" + cupoMaximo +
                ", semestre='" + semestre + '\'' +
                '}';
    }


    //Metodos auxiliares
    //Agregar un horario a la clase
    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    //Metodos de funcionalidades

}

