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

public class Asignatura {
    private Curso correquisito;
    private List<Curso> prerequisitos;

    public Asignatura() {
        this.prerequisitos = new ArrayList<>();
    }

    public Curso getCorrequisito() {
        return correquisito;
    }

    public void setCorrequisito(Curso correquisito) {
        this.correquisito = correquisito;
    }

    public List<Curso> getPrerequisitos() {
        return prerequisitos;
    }

    public void setPrerequisitos(List<Curso> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    public void addPrerequisito(Curso prerequisito) {
        this.prerequisitos.add(prerequisito);
    }

    public void removePrerequisito(Curso prerequisito) {
        this.prerequisitos.remove(prerequisito);
    }
}
