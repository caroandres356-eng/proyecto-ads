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

    public void agregarClase(Clase clase) {
        clasesAsignadas.add(clase);
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
