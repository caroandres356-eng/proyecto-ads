/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoads;

/**
 *
 * @author Elvia
 */
public class ProfesorCatedra extends Profesor {
    private String empresa;
    private int horasSemanales;
    private double sobreSueldo;

    public ProfesorCatedra() {
        super();
    }

    public ProfesorCatedra(String nombre, String email, String empresa, int horasSemanales, double sobreSueldo) {
        super(nombre, email);
        this.empresa = empresa;
        this.horasSemanales = horasSemanales;
        this.sobreSueldo = sobreSueldo;
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

    public double getSobreSueldo() {
        return sobreSueldo;
    }

    public void setSobreSueldo(double sobreSueldo) {
        this.sobreSueldo = sobreSueldo;
    }

    @Override
    public double calcularSalario() {
       // no se como se calcula el sueldo xd
        return sobreSueldo;
    }
}