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

public class Clase {
    private String identificador;
    private String semestre;
    private int cupoMaximo;
    private List<Horario> horarios;
    private Profesor profesor;
    private List<Estudiante> estudiantes;

    public Clase() {
        this.horarios = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public Clase(String identificador, String semestre, int cupoMaximo) {
        this.identificador = identificador;
        this.semestre = semestre;
        this.cupoMaximo = cupoMaximo;
        this.horarios = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void addHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public void removeHorario(Horario horario) {
        this.horarios.remove(horario);
    }

    public void addEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

    public void removeEstudiante(Estudiante estudiante) {
        this.estudiantes.remove(estudiante);
    }
}
