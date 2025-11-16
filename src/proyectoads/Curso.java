/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoads;

/**
 *
 * @author Elvia
 */
public class Curso {
    private String codigo;
    private String nombre;
    private int creditos;
    private String examenIngles;

    public Curso() {
    }

    public Curso(String codigo, String nombre, int creditos, String examenIngles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.examenIngles = examenIngles;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getExamenIngles() {
        return examenIngles;
    }

    public void setExamenIngles(String examenIngles) {
        this.examenIngles = examenIngles;
    }
}
