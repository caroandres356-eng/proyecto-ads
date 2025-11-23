package com.example.proyectoads.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario implements Serializable {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String salon;

    public Horario(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, String salon) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.salon = salon;
    }

    public DayOfWeek getDia() { return dia; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public String getSalon() { return salon; }


}

