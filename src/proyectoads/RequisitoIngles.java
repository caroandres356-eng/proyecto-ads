package proyectoads;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Elvia
 */
public class RequisitoIngles {
    private String mecanismo;
    private String nivel;

    public RequisitoIngles() {
    }

    public RequisitoIngles(String mecanismo, String nivel) {
        this.mecanismo = mecanismo;
        this.nivel = nivel;
    }

    public String getMecanismo() {
        return mecanismo;
    }

    public void setMecanismo(String mecanismo) {
        this.mecanismo = mecanismo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
