package com.example.proyectoads.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String codigo;
    private String nombre;
    private String email;
    private String carrera;
    private boolean requisitoIngles;
    private List<Clase> clasesInscritas;

    public Estudiante(String codigo, String nombre, String email, String carrera, boolean requisitoIngles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
        this.requisitoIngles = requisitoIngles;
        this.clasesInscritas = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getCarrera() { return carrera; }
    public boolean tieneRequisitoIngles() { return requisitoIngles; }
    public List<Clase> getClasesInscritas() { return clasesInscritas; }

    public void inscribirClase(Clase c) { clasesInscritas.add(c); }
    public void retirarClase(Clase c) { clasesInscritas.remove(c); }
    public void mostrarClasesInscritas(){
        System.out.println("CLASES INSCRITAS PARA EL ESTUDIANTE " +this.nombre );
        for (Clase c: clasesInscritas){
            System.out.println(" " +c.toString());
        }
    }
    
    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}
