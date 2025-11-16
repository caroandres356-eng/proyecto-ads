
package proyectoads;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Elvia
 */
public class ProfesorPlanta extends Profesor {
    private double salarioBase;

    public ProfesorPlanta() {
        super();
    }

    public ProfesorPlanta(String nombre, String email, double salarioBase) {
        super(nombre, email);
        this.salarioBase = salarioBase;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
       
        return salarioBase;
    }
}
