/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.example.proyectoads.manejadorArchivos;

import java.io.*;

public class SerializadoDatos {
    
    /**
     * Serializa un objeto y lo guarda en un archivo
     * @param objeto
     */
    public static boolean serializar(Object objeto, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(rutaArchivo))) {
            oos.writeObject(objeto);
            System.out.println("Objeto serializado exitosamente en: " + rutaArchivo);
            return true;
        } catch (IOException e) {
            System.err.println("Error al serializar: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Deserializa un objeto desde un archivo
     * @param rutaArchivo
     */
    public static Object deserializar(String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(rutaArchivo))) {
            Object objeto = ois.readObject();
            System.out.println("Objeto deserializado exitosamente desde: " + rutaArchivo);
            return objeto;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Verifica si un archivo existe
     */
    public static boolean existeArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        return archivo.exists();
    }
    
   
   
}
