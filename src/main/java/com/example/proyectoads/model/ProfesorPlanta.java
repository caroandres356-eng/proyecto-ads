package com.example.proyectoads.model;

public class ProfesorPlanta extends Profesor {

    private double horasSemanales;

    public ProfesorPlanta(String id, String nombre, String email, double salarioBase) {
        super(id, nombre, email, salarioBase);
    }

    public void setHorasSemanales(double horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public double getHorasSemanales() {
        return horasSemanales;
    }

    @Override
    public double calcularSalario() {
        // Simplemente devuelve su salario base mensual (puedes ampliarlo luego)
        return salarioBase;
    }
}

