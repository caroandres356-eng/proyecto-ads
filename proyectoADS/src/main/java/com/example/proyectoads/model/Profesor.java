package com.example.proyectoads.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Profesor {

    protected String id;
    protected String nombre;
    protected String email;
    protected Departamento departamento;
    protected List<Clase> clasesAsignadas;
    protected double salarioBase;

    public Profesor(String id, String nombre, String email, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.salarioBase = salarioBase;
        this.clasesAsignadas = new ArrayList<>();
    }

    public String agregarClase(Clase c) {
        if (!this.clasesAsignadas.contains(c)) {
            this.clasesAsignadas.add(c);
            return "Éxito: Clase " + c.getCodigo() + " agregada al profesor " + this.nombre;
        }
        return "Error: La clase ya está asignada a este profesor.";
    }

    public List<Clase> getClasesAsignadas() {
        return clasesAsignadas;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public abstract double calcularSalario();

    @Override
    public String toString() {
        return nombre + " (" + getClass().getSimpleName() + ")";
    }


}
