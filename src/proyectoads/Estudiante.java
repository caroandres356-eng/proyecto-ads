/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoads;

/**
 *
 * @author Elvia
 */
import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String codigo;
    private String nombre;
    private String email;
    private String carrera;
    private List<RequisitoIngles> requisitosIngles;

    public Estudiante() {
        this.requisitosIngles = new ArrayList<>();
    }

    public Estudiante(String codigo, String nombre, String email, String carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
        this.requisitosIngles = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public List<RequisitoIngles> getRequisitosIngles() {
        return requisitosIngles;
    }

    public void setRequisitosIngles(List<RequisitoIngles> requisitosIngles) {
        this.requisitosIngles = requisitosIngles;
    }

    public void addRequisitoIngles(RequisitoIngles requisitoIngles) {
        this.requisitosIngles.add(requisitoIngles);
    }

    public void removeRequisitoIngles(RequisitoIngles requisitoIngles) {
        this.requisitosIngles.remove(requisitoIngles);
    }
}
