package com.example.proyectoads.model;

import java.time.LocalTime;

public class ProfesorCatedra extends Profesor {

    private String empresa;
    private int horasSemanales;
    private String categoria;  // A, B, C, D, E
    private boolean dictaEnLaNoche;

    public ProfesorCatedra(String id, String nombre, String email,
                           double salarioBase, String empresa,
                           int horasSemanales, boolean dictaEnLaNoche) {
        super(id, nombre, email, salarioBase);
        this.empresa = empresa;
        this.horasSemanales = horasSemanales;
        this.dictaEnLaNoche = dictaEnLaNoche;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
  public boolean isDictaEnLaNoche() {
    for (Clase c: this.getClasesAsignadas()){
        for (Horario h: c.getHorarios()){
            if(h.getHoraFin().isAfter(LocalTime.of(18, 0))){
                return true;
            }
        }
    }
    return false; 
}

    @Override
    public double calcularSalario() {
        double pagoPorHora = 
                switch 
                (categoria.toUpperCase()) {
            case "A" -> 15000;
            case "B" -> 18000;
            case "C" -> 21000;
            case "D" -> 25000;
            case "E" -> 30000;
            default -> 15000;
        };

        double total = horasSemanales * pagoPorHora;

        // Sobresueldo del 35% si dicta en la noche
        if (dictaEnLaNoche) total *= 1.35;

        // Si supera las 19 horas, se pagan $50.000 adicionales por hora
        if (horasSemanales > 19) {
            total += (horasSemanales - 19) * 50000;
        }
        total+= this.salarioBase;

        return total ;
    }
}
