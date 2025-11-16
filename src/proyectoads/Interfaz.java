/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoads;

/**
 *
 * @author Elvia
 */
public class Interfaz {
    private ControladorAcademico controladorAcademico;
    private ControladorAdministrativo controladorAdministrativo;

    public Interfaz() {
    }

    public ControladorAcademico getControladorAcademico() {
        return controladorAcademico;
    }

    public void setControladorAcademico(ControladorAcademico controladorAcademico) {
        this.controladorAcademico = controladorAcademico;
    }

    public ControladorAdministrativo getControladorAdministrativo() {
        return controladorAdministrativo;
    }

    public void setControladorAdministrativo(ControladorAdministrativo controladorAdministrativo) {
        this.controladorAdministrativo = controladorAdministrativo;
    }
}
