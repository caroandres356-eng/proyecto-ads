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


public class Departamento {
    private List<Curso> cursos;
    private List<Profesor> profesores;

    public Departamento() {
        this.cursos = new ArrayList<>();
        this.profesores = new ArrayList<>();
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public void removeCurso(Curso curso) {
        this.cursos.remove(curso);
    }

    public void addProfesor(Profesor profesor) {
        this.profesores.add(profesor);
    }

    public void removeProfesor(Profesor profesor) {
        this.profesores.remove(profesor);
    }
}
