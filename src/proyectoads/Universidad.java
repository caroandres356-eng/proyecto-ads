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

public class Universidad {
    private List<Departamento> departamentos;

    public Universidad() {
        this.departamentos = new ArrayList<>();
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public void addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
    }

    public void removeDepartamento(Departamento departamento) {
        this.departamentos.remove(departamento);
    }
}
