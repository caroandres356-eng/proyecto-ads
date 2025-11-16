/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoads;

/**
 *
 * @author Elvia
 */
public class Horario {
    private String salon;
    private String dia;
    private String hora;

    public Horario() {
    }

    public Horario(String salon, String dia, String hora) {
        this.salon = salon;
        this.dia = dia;
        this.hora = hora;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
