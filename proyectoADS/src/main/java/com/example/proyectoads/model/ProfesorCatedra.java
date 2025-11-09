package com.example.proyectoads.model;

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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public boolean isDictaEnLaNoche() {
        return dictaEnLaNoche;
    }

    @Override
    public double calcularSalario() {
        double pagoPorHora = switch (categoria.toUpperCase()) {
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

        return total;
    }
}
